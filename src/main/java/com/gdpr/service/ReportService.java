package com.gdpr.service;

import java.util.List;
import java.util.Map;

public interface ReportService {

	 
	/**
	 * @return as List<String>
	 */
	public Map<String, String>  getCycleDates();
	
	/**
	 * @param cycleName
	 * @return as List<String>
	 */
	public List<String> getGeneratedReportList(String cycleName); 

}
