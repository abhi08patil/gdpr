package com.gdpr.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CreateTableDao  {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void createGdprTables() {
		// TODO Auto-generated method stub
		try {
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_DELETION_REPORT_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_DELETION_REPORT_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_PRE_DELETION_REPORT_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_PRE_DELETION_REPORT_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_DELETION_APPROVE_REPORT_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_DELETION_APPROVE_REPORT_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_PRE_DELETION_WF_MSTR_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_PRE_DELETION_WF_MSTR_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_DATA_HOLD_MSTR_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_DATA_HOLD_MSTR_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_DATA_HOLD_MAPPING_MSTR_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_DATA_HOLD_MAPPING_MSTR_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_MODULE_CONFIG_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_MODULE_CONFIG_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_PROCESS_REQUEST_MSTR_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_PROCESS_REQUEST_MSTR_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_MODULE_LVL_RETPEROID_MSTR_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_MODULE_LVL_RETPEROID_MSTR_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_STG_KEY_LIST_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_STG_KEY_LIST_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_REPORT_CYCLE_CONFIG_TABLE);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_REPORT_CYCLE_CONFIG_TABLE);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_MODULE_LVL_RP_MSTR_SEQ);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_MODULE_LVL_RP_MSTR_SEQ);
			
			jdbcTemplate.execute(GdprConstant.DROP_GDPR_PROCESS_REQUEST_MSTR_SEQ);
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_PROCESS_REQUEST_MSTR_SEQ);
			
			//new added constants 13-dec
			jdbcTemplate.execute(GdprConstant.CREATE_GDPR_DATA_HOLD_MSTR_SEQ);
			jdbcTemplate.execute(GdprConstant.CREATE_VIEW_gdpr_legal_hold_view);
			
		} catch(DataAccessException e) {
			System.out.println("Exception in dao "+e);
			System.out.println("Exception in dao "+e.getMostSpecificCause());
		}
		
	}
	
	public List<String> fetchGdprTableList(){
		List<String> listOfTables = null;
		try {
			listOfTables = new ArrayList<String>();
			listOfTables = jdbcTemplate.query(GdprConstant.SELECT_GDPR_TABLE_LIST,new RowMapper<String>() {
										public String mapRow(ResultSet rs, int rowNum) throws SQLException {
											return rs.getString(1);
											
										}
			});
			
		} catch (DataAccessException e) {
			System.out.println("Exception in dao.fetchGdprTableList() "+e);
		}
		System.out.println("In dao list of tables "+listOfTables);
		return listOfTables;
	}
	
	public int saveDataHold(DataHoldDTO dataholdDto) {
		int rowInsrt=0;
		String sql = GdprConstant.SAVE_LEGAL_HOLD_INFO;
		
		try {
			rowInsrt = jdbcTemplate.update(sql,new Object[] {dataholdDto.getDataHoldExtId(),dataholdDto.getDataHoldType(),dataholdDto.getDataHoldStartDt(),
					dataholdDto.getDataHoldEndDt(),dataholdDto.getDataHoldComments(),dataholdDto.getCreatedBy(),dataholdDto.getCreatedDate(),dataholdDto.getModifiedBy(),
					dataholdDto.getModifiedDate()
					
			});
		} catch(DataAccessException e) {
			System.out.println("Exception in dao.saveDataHold() "+e);
		}
		
		return rowInsrt;
	}
	
	
	
	

}
