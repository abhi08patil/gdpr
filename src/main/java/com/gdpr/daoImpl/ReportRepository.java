package com.gdpr.daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.gdpr.dao.ReportDao;

@Repository
public class ReportRepository implements ReportDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, String> getCycleDates() {
		String query = "select LABEL_VALUE, to_char(KEY_VALUE,'DD-MON-YYYY') KEY_VALUE from gdpr_report_cycle_config where key_value > sysdate";
		HashMap<String, String> results = new HashMap<>();

		jdbcTemplate.query(query, (ResultSet rs) -> {
			while (rs.next()) {
				System.out.println(rs.getString("LABEL_VALUE"));
				results.put(rs.getString("LABEL_VALUE"), rs.getString("KEY_VALUE"));
			}
			return results;
		});
		return results;
	}

	@Override
	public List<String> getGeneratedReportList(String cycleName) {
		List<String> result = jdbcTemplate.queryForList(
				"select module_base_query bq from gdpr_module_config where active_status='Y'", String.class);
		List<String> resultSet = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(result)) {
			String deleteQuery = "delete from gdpr_pre_deletion_report where trunc(DEL_EXEC_DATE )= to_date('"
					+ cycleName + "','DD-MON-YYYY')";
			jdbcTemplate.update(deleteQuery);
			for (String baseQuery : result) {
				String insertQuery = "Insert into gdpr_pre_deletion_report( MODULE_ID, RECORD_ID,  DATE_TOBE_CONSIDERED, EXPECTED_DEL_DATE, DEL_EXEC_DATE, "
						+ "DATA_HOLD, MAPPED_DATA_HOLD, MAX_DATA_HOLD_DATE,  STATUS, REASON) "
						+ "select * from ( select RET_MSTR.module_name, BASE_VIEW. RECORD_ID, BASE_VIEW. DATE_TOBE_CONSIDERED,"
						+ " ADD_MONTHS(trunc(BASE_VIEW. DATE_TOBE_CONSIDERED)+NVL(RET_MSTR.MODULE_RET_DAYS,0),nvl(RET_MSTR.MODULE_RET_MONTH,0) "
						+ "+(NVL(RET_MSTR.MODULE_RET_YEAR,0)*12)) EXP_DEL_DATE, to_date('" + cycleName
						+ "','DD-MON-YYYY') DEL_EXE_DATE, nvl2(LEGAL.data_hold_id,'Y','N') data_hold, "
						+ "LEGAL. data_hold_id MAPPED_LGL_HOLDS, LEGAL.data_HOLD_END_DATE, NVL2(legal.data_hold_id,'VALIDATION FAILED',"
						+ "'COMPLETE') status , nvl2(LEGAL.data_hold_id,'Data Hold Exists','Exceeded Retention Period')  REASON from "
						+ "( " + baseQuery
						+ " ) BASE_VIEW left outer join gdpr_module_config RET_MSTR on (RET_MSTR.MODULE_ID = BASE_VIEW. MODULE_ID) "
						+ "left outer join GDPR_LEGAL_HOLD_VIEW LEGAL on ( BASE_VIEW. RECORD_ID = LEGAL. RECORD_ID and BASE_VIEW.module_id = legal.module_id "
						+ "AND  to_date('" + cycleName
						+ "','DD-MON-YYYY') BETWEEN legal.data_hold_start_date AND legal.data_hold_end_date )) where EXP_DEL_DATE <  DEL_EXE_DATE";

				jdbcTemplate.update(insertQuery);
			}

		}

		String getreportQuery = "select MODULE_ID, RECORD_ID, TO_CHAR(DATE_TOBE_CONSIDERED, 'DD-MON-YYYY') DATE_TOBE_CONSIDERED,TO_CHAR(EXPECTED_DEL_DATE, 'DD-MON-YYYY') EXPECTED_DEL_DATE,TO_CHAR(DEL_EXEC_DATE, 'DD-MON-YYYY') DEL_EXEC_DATE, DATA_HOLD, MAPPED_DATA_HOLD,TO_CHAR(MAX_DATA_HOLD_DATE, 'DD-MON-YYYY') MAX_DATA_HOLD_DATE, SOFT_DELETE, STATUS, REASON from gdpr_pre_deletion_report where trunc(DEL_EXEC_DATE )= to_date('"
				+ cycleName + "','DD-MON-YYYY')";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(getreportQuery);
		for (Map row : rows) {
			String data = row.get("MODULE_ID") + "," + row.get("RECORD_ID") + "," + row.get("DATE_TOBE_CONSIDERED")
					+ "," + row.get("EXPECTED_DEL_DATE") + "," + row.get("DEL_EXEC_DATE") + "," + row.get("DATA_HOLD")
					+ "," + row.get("MAPPED_DATA_HOLD") + "," + row.get("MAX_DATA_HOLD_DATE") + ","
					+ row.get("SOFT_DELETE") + "," + row.get("STATUS") + "," + row.get("REASON");
			resultSet.add(data);
		}
		return resultSet;
	}
}
