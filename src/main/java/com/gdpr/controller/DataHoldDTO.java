package com.gdpr.controller;

import java.util.Date;

public class DataHoldDTO {
	
	private String dataHoldType;
	
	private String dataHoldStartDt;
	
	private String dataHoldEndDt;
	
	private String dataHoldComments;
	private String dataHoldExtId;
	
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDataHoldExtId() {
		return dataHoldExtId;
	}

	public void setDataHoldExtId(String dataHoldExtId) {
		this.dataHoldExtId = dataHoldExtId;
	}

	public String getDataHoldType() {
		return dataHoldType;
	}

	public void setDataHoldType(String dataHoldType) {
		this.dataHoldType = dataHoldType;
	}

	public String getDataHoldStartDt() {
		return dataHoldStartDt;
	}

	public void setDataHoldStartDt(String dataHoldStartDt) {
		this.dataHoldStartDt = dataHoldStartDt;
	}

	public String getDataHoldEndDt() {
		return dataHoldEndDt;
	}

	public void setDataHoldEndDt(String dataHoldEndDt) {
		this.dataHoldEndDt = dataHoldEndDt;
	}

	public String getDataHoldComments() {
		return dataHoldComments;
	}

	public void setDataHoldComments(String dataHoldComments) {
		this.dataHoldComments = dataHoldComments;
	}
	

}
