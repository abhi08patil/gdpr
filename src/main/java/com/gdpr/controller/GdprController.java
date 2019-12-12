package com.gdpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gdpr/v1")
public class GdprController {
	
	@Autowired
	CreateTableService createtableservice;
	
	@RequestMapping("/deletehistory")
	public String deletehistory() {
		return "gdpr/deletehistory";
	}
	
	
	@RequestMapping("/configuration")
	public String configuration() {
		return "gdpr/configuration";
	}
	
	@GetMapping("/createGdprTables")
	public String createGdprTables(ModelMap model) {
		createtableservice.createGdprTables();
		List<String> tblList = createtableservice.fetchGdprTableList(); 
		model.put("TableCreationMesg", "Below GDPR Tables and Sequences created successfully.");
		model.put("tblList",tblList);
		return "gdpr/configuration";
	}
	
	@RequestMapping("/bqview")
	public String bqview() {
		return "gdpr/bqview";
	}
	
	@RequestMapping("/deleteproc")
	public String deleteproc() {
		return "gdpr/deleteproc";
	}
	
	
	
	
	
	
}
