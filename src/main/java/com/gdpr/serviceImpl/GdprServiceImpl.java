package com.gdpr.serviceImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdpr.dao.GdprDao;
import com.gdpr.service.GdprService;

@Service
public class GdprServiceImpl implements GdprService {

	@Autowired
	GdprDao gdprDao;
	
	
	/**
	 * This method is responsible to fetch list of all tables.
	 * 
	 * @return as List<String>
	 */
	@Override
	public List<String> findAllTables() {
		return gdprDao.findAllTables();
	}
	
@Override
	public Map<String, String> getColumns(String selectedTable) {
		// TODO Auto-generated method stub
		return gdprDao.getColumns(selectedTable);
	}

	@Override
	public String createBaseQuery(String[] checkboxValue, String moduleName, String retention, String tableName) {
		// Select 1 MODULE_id,employee_id record_id , last_working_date as
		// date_tobe_considered from employee;
		String recordId = null;
		String dateColumn = null;

		for (int i = 0; i < checkboxValue.length; i++) {
			String data[] = checkboxValue[i].split("-");

			if (data[1].equalsIgnoreCase("DATE") || data[1].equalsIgnoreCase("TIMESTAMP")) {
				dateColumn = data[0];
			} else {
				recordId = data[0];
			}

		}
		Random rand = new Random();
		String moduleId = tableName + rand.nextInt(100);
		String baseQuery = "SELECT '" + moduleId + "' as module_id ," + recordId + " as record_id," + dateColumn
				+ " as date_tobe_considered FROM " + tableName;

		return gdprDao.createBaseQuery(moduleId, baseQuery, retention, moduleName, tableName);
	}
	
	@Override
	public HashMap<String, String>  findAllConfigs() {
		return gdprDao.findAllConfigs();
	}
	
	@Override
	public LinkedHashMap<String, String> findTableHierarchy(String tableName) {
		return (LinkedHashMap<String, String>) gdprDao.findTableHierarchy(tableName);
	}
	
	@Override
	public String generateDeleteProc(String[] checkboxValue, String tableName) {
		
		String moduleId = gdprDao.getModuleId(tableName);
		StringBuilder sb = new StringBuilder();
		String deleteProcName= "GDPR_DATA_DEL_"+moduleId;
		if(deleteProcName.length()>30)
		{
			deleteProcName = deleteProcName.substring(0, 29);
		}
		
		sb.append("CREATE OR REPLACE PROCEDURE "+deleteProcName+" (P_RECORD_ID VARCHAR2) IS  BEGIN SAVEPOINT POINT1;");
		for (int i = 0; i < checkboxValue.length; i++) {
			String data[] = checkboxValue[i].split("-");
			sb.append(data[1]);
		}
		sb.append("EXCEPTION WHEN OTHERS THEN ROLLBACK TO POINT1; RAISE; END;");
		System.out.println(sb.toString());
		
		return gdprDao.generateDeleteProc(sb.toString(),moduleId,deleteProcName);
	}
}
