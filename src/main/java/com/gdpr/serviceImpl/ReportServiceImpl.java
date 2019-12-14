package com.gdpr.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdpr.dao.ReportDao;
import com.gdpr.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportDao reportDao;
	
	
	 @Override
	public Map<String, String>  getCycleDates() {
		return reportDao.getCycleDates();
	}
	 
	 
	 @Override
	public List<String> getGeneratedReportList(String cycleName) {
		return reportDao.getGeneratedReportList(cycleName);
	}
}


