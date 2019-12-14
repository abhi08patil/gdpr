package com.gdpr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

public interface GdprService {

	/**
	 * This method is responsible to fetch list of all tables.
	 * 
	 * @return as List<String>
	 */
	public List<String> findAllTables();
	
	
	/**
	 * @return as Map<String, String>
	 */
	public Map<String, String> getColumns(String selectedTable);
	
	/**
	 * @return as String
	 */
	public String createBaseQuery(String[] checkboxValue,
			 String moduleName,
			String retention,
			String tableName);
	
	/**
	 * @return as HashMap<String, String>
	 */
	public HashMap<String, String>  findAllConfigs();
	
	/**
	 * @return as HashMap<String, String>
	 */
	public HashMap<String, String>  findTableHierarchy(String tableName);
	
	/**
	 * @param checkboxValue as String[]
	 * @param tableName as String
	 * @return
	 */
	public String generateDeleteProc(String[] checkboxValue, 
			String tableName);
	

}
