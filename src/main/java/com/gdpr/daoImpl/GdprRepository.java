package com.gdpr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
				+ " and exists (select 1 from USER_TAB_COLUMNS t2 where t1.table_name = t2.table_name and t2.data_type = 'DATE')",
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
			 msg = "Somthing went wrong";
		}
		catch (Exception e) {
			msg = "BaseQuery already generated for "+tableName;
		}
		
		return msg;
	}
	
	
	@Override
	public HashMap<String, String> findAllConfigs() {

		
		HashMap<String, String> results = new HashMap<>();

		jdbcTemplate.query("select MODULE_NAME,BASE_QUERY_VIEW from GDPR_MODULE_CONFIG where active_status='Y'", (ResultSet rs) -> {
					while (rs.next()) {
						System.out.println(rs.getString("MODULE_NAME"));
						results.put(rs.getString("BASE_QUERY_VIEW"), rs.getString("MODULE_NAME"));
					}
					return results;
				});

		return results;
	}
}
