package com.gdpr.dao;

import java.util.List;
import java.util.Map;

public interface ReportDao {

	/**
	 * @return as Map<String, String> 
	 */
	public Map<String, String>  getCycleDates();
	
	/**
	 * @param cycleName
	 * @return as List<String>
	 */
	public List<String> getGeneratedReportList(String cycleName); 
	
}
