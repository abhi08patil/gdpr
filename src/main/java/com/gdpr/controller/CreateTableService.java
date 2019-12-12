package com.gdpr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTableService {
	
	@Autowired
	CreateTableDao createTableDao;
	
	public void createGdprTables() {
		createTableDao.createGdprTables();
	}
	
	public List<String> fetchGdprTableList(){
		List<String> listofTables = new ArrayList<String>();
		listofTables = createTableDao.fetchGdprTableList();
		return listofTables;
	}
	

}
