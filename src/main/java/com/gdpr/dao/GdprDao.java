package com.gdpr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GdprDao {

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
	public String createBaseQuery( 
			String moduleId,
			String baseQuery,String retention,String moduleName, String tableName);
	
	/**
	 * @return as HashMap<String, String>
	 */
	public HashMap<String, String> findAllConfigs();
	
	
	/**
	 * @return as HashMap<String, String>
	 */
	public HashMap<String, String>  findTableHierarchy(String tableName);
	
	/**
	 * @param deleteProc as String
	 * @return
	 */
	public String generateDeleteProc(String deleteProc,String moduleId, String deleteProcName);
	
	/**
	 * @param tableName as String
	 * @return as String
	 */
	public String getModuleId(String tableName);
	
}
