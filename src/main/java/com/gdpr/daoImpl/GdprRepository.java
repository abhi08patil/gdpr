package com.gdpr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gdpr.dao.GdprDao;

@Repository
public class GdprRepository implements GdprDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<String> findAllTables() {

		List<String> result = jdbcTemplate.queryForList("SELECT TABLE_NAME FROM ALL_TABLES t1 WHERE OWNER = 'HR'\r\n"
				+ " and t1.table_name not in (\r\n" + 
				"select gdpr_module_config.base_query_view from GDPR_MODULE_CONFIG ) and exists (select 1 from USER_TAB_COLUMNS t2 where t1.table_name = t2.table_name and t2.data_type = 'DATE')",
				String.class);

		return result;

	}

	@Override
	public Map<String, String> getColumns(String selectedTable) {
		HashMap<String, String> results = new HashMap<>();

		jdbcTemplate.query("SELECT column_name, data_type\r\n" + "FROM USER_TAB_COLUMNS\r\n" + "WHERE table_name = ?",
				new Object[] { selectedTable }, (ResultSet rs) -> {
					while (rs.next()) {
						System.out.println(rs.getString("COLUMN_NAME"));
						results.put(rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE"));
					}
					return results;
				});
		return results;
	}

	
	@Override
	public String createBaseQuery(String moduleId, String baseQuery, String retention, String moduleName ,String tableName) {
		String msg =null;
		try {
		String insertQuery = "INSERT INTO GDPR_MODULE_CONFIG (MODULE_ID, MODULE_BASE_QUERY, BASE_QUERY_VIEW, MODULE_RET_YEAR, MODULE_RET_MONTH, "
				+ "MODULE_RET_DAYS, CREATED_DATE, ACTIVE_STATUS, MODULE_NAME) VALUES "
				+ "(?, ?, ?, '0', ?, '0', sysdate, 'Y', ?)";
		
		 Object[] params = new Object[] { moduleId, baseQuery, tableName, retention , moduleName };
		 
		 int row = jdbcTemplate.update(insertQuery, params);
		 
		 if (row >0)
			 msg ="Base Query created Sucessfully!";
		 else 
			 msg = "Failed Data not inserted into "+tableName;
		}
		catch (Exception e) {
			msg = "Failed "+e.getMessage();
		}
		
		return msg;
	}
	
	
	@Override
	public HashMap<String, String> findAllConfigs() {

		
		HashMap<String, String> results = new HashMap<>();

		jdbcTemplate.query("select MODULE_NAME,BASE_QUERY_VIEW from GDPR_MODULE_CONFIG where active_status='Y'", (ResultSet rs) -> {
					while (rs.next()) {
						results.put(rs.getString("BASE_QUERY_VIEW"), rs.getString("MODULE_NAME"));
					}
					return results;
				});

		return results;
	}
	
	
	@Override
	public LinkedHashMap<String, String> findTableHierarchy(String tableName) {
		
		LinkedHashMap<String, String> results = new LinkedHashMap<>();

		String query = "select src_cc.table_name as src_table, 'DELETE FROM '||src_cc.owner||'.'||src_cc.table_name||' WHERE '||src_cc.column_name||' = P_RECORD_ID;'||'' CMD from all_constraints c inner join all_cons_columns dest_cc on c.r_constraint_name = dest_cc.constraint_name and c.r_owner = dest_cc.owner inner join all_cons_columns src_cc on c.constraint_name = src_cc.constraint_name and c.owner = src_cc.owner where c.constraint_type = 'R' and dest_cc.owner = 'HR' and src_cc.table_name !='"
				+ tableName + "' and dest_cc.table_name = '" + tableName + "' union all select tab_con.table_name as src_table \r\n" + 
						",'DELETE FROM '||tab_con.owner||'.'||tab_con.table_name||' WHERE '||col_con.COLUMN_name||' = P_RECORD_ID;' CMD\r\n" + 
						"from all_constraints tab_con, ALL_CONS_COLUMNS col_con\r\n" + 
						"where \r\n" + 
						"tab_con.owner = col_con.owner\r\n" + 
						"and tab_con.table_name = col_con.table_name\r\n" + 
						"and tab_con.CONSTRAINT_NAME = col_con.CONSTRAINT_NAME\r\n" + 
						"and tab_con.owner = 'HR' \r\n" + 
						"and tab_con.table_name = '" + tableName + "' and tab_con.CONSTRAINT_TYPE = 'P'\r\n" + 
						"";

		jdbcTemplate.query(query, (ResultSet rs) -> {
			while (rs.next()) {
				results.put(rs.getString("SRC_TABLE"), rs.getString("CMD"));
			}
			return results;
		});

		return results;
	}
	
	@Override
	public String generateDeleteProc(String deleteProc,String moduleId, String deleteProcName) {
		String msg ="Delete procedure created sucessfully";
		
		String queryToUpdate="update gdpr_module_config set DELETION_PROC='"+deleteProcName+"' where module_id='"+moduleId+"'";
		System.out.println(queryToUpdate);
		try {
			
			jdbcTemplate.execute(deleteProc);
			jdbcTemplate.update(queryToUpdate);
		}
		catch (Exception e) {
			msg ="Failed "+e.getMessage();
		}
		return msg;
	}
	
	@Override
	public String getModuleId(String tableName)
	{
		String query ="select MODULE_ID from GDPR_MODULE_CONFIG where gdpr_module_config.base_query_view='"+tableName+"'";
		return jdbcTemplate.queryForObject(query, String.class);
	}
}
