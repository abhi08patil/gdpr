package com.gdpr.controller;

import java.util.ArrayList;
import java.util.Date;
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
	
	public int saveDataHold(DataHoldDTO datahold) {
		//DataHoldDTO datahold= new DataHoldDTO();
		datahold.setCreatedDate(new Date());
		datahold.setModifiedDate(new Date());
		int rowVal;
		rowVal = createTableDao.saveDataHold(datahold);
		return rowVal;
	}
	

}
