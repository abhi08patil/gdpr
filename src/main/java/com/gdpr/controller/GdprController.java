package com.gdpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gdpr.service.GdprService;

@Controller
@RequestMapping("/gdpr/v1")
public class GdprController {

	@Autowired
	GdprService gdprService;

	
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
	public ModelAndView bqview(@RequestParam(value = "msg" , required = false) String msg) {
		ModelAndView modelAndView = new ModelAndView("gdpr/bqview");
		modelAndView.addObject("msg", msg);
		modelAndView.addObject("lists", gdprService.findAllTables());
		return modelAndView;
	}

	@GetMapping("/getColumns")
	public @ResponseBody Object getColumns(@RequestParam("tableName") String selectedTable) {

		return gdprService.getColumns(selectedTable);
	}

	@PostMapping("/createBq")
	public ModelAndView createBq(@RequestParam("test") String[] checkboxValue,
			@RequestParam("moduleName") String moduleName, @RequestParam("retention") String retention,
			@RequestParam("tableName") String tableName ,ModelMap model) {
		String msg = null;
		ModelAndView modelAndView = new ModelAndView("redirect:/gdpr/v1/bqview");
		if (checkboxValue.length <= 1) {
			msg = "Please select 2 columns";
			modelAndView.addObject("error", msg);
		}
		else
		{
			msg = gdprService.createBaseQuery(checkboxValue, moduleName, retention, tableName);
			if(msg.contains("Failed"))
				modelAndView.addObject("error", msg);
			else
				modelAndView.addObject("msg", msg);
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping("/getConfigNames")
	public ModelAndView getConfigNames(@RequestParam(value = "msg" , required = false) String msg,@RequestParam(value = "error" , required = false) String error) {
		ModelAndView modelAndView = new ModelAndView("gdpr/bqview");
		modelAndView.addObject("msg", msg);
		modelAndView.addObject("error", error);
		modelAndView.addObject("lists", gdprService.findAllTables());
		return modelAndView;
	}

	@RequestMapping("/deleteproc")
	public ModelAndView deleteproc(@RequestParam(value = "msg" , required = false) String msg,@RequestParam(value = "error" , required = false) String error) {
		ModelAndView modelAndView = new ModelAndView("gdpr/deleteproc");
		modelAndView.addObject("msg", msg);
		modelAndView.addObject("error", error);
		modelAndView.addObject("map", gdprService.findAllConfigs());
		return modelAndView;
	}
	
	@PostMapping("/getTableHierarchy")
	public @ResponseBody Object getTableHierarchy(@RequestParam("tableName") String tableName ,ModelMap model) {
		return gdprService.findTableHierarchy(tableName);
	}
	
	@PostMapping("/generateDeleteProc")
	public ModelAndView generateDeleteProc(@RequestParam("test") String[] checkboxValue,
			@RequestParam("tableName") String tableName ,ModelMap model) {
		String msg = null;
		ModelAndView modelAndView = new ModelAndView("redirect:/gdpr/v1/deleteproc");
		if (checkboxValue.length == 0) {
			msg = "Please select at least 1 column";
			modelAndView.addObject("error", msg);
		}
		else
		{
			msg = gdprService.generateDeleteProc(checkboxValue, tableName);
			if(msg.contains("Failed"))
				modelAndView.addObject("error", msg);
			else
				modelAndView.addObject("msg", msg);
		}
		
		return modelAndView;
	}

}
