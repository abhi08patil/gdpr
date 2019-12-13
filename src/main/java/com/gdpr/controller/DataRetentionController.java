package com.gdpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/gdpr/v1")
public class DataRetentionController {

	
	@Autowired
	CreateTableService createtableservice;
	
	@RequestMapping("/datahold")
	public String datahold() {
		return "datahold/datahold";
	}
	
	/*@GetMapping("/datahold")
	public String datahold(ModelMap model) {
		List<String> tblList = createtableservice.fetchGdprTableList(); 
		model.put("tblList",tblList);
		return "datahold/datahold";
	}*/
	
	@RequestMapping(value = "/createDataHold", method = RequestMethod.POST)
	public String createDataHold(ModelMap model,@ModelAttribute("dataholdDto") DataHoldDTO dataholdDto) {
		int rowInsrt;
		rowInsrt = createtableservice.saveDataHold(dataholdDto);
		if(rowInsrt == 1) {
			model.put("success", "Data Hold Added Successfully.");
		} else {
			model.put("error", "Something went wrong while applying data hold.");
		}
		return "datahold/datahold";
	}
	
	
	@RequestMapping("/dataholdmapping")
	public String dataHoldMapping(ModelMap model) {
		List<String> tblList = createtableservice.fetchGdprTableList(); 
		model.put("tblList",tblList);
		return "datahold/dataholdmapping";
	}
}
