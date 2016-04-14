package com.egen.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Alert;
import com.egen.service.IAlertService;

@RestController
@RequestMapping("/alert")
public class PWTAlertsController {
	@Autowired
	IAlertService alertService;
		
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET, produces = "application/json")
	public List<Alert> retrieve()
	{
		List<Alert> alertList = alertService.retrieve();
		return alertList;
	}
	
	@RequestMapping(value = "/retrieve/fromtime/{fromTime}/totime/{toTime}", method = RequestMethod.GET, produces = "application/json")
	public List<Alert> retrieveByTimeRange(@PathVariable String fromTime, @PathVariable String toTime)
	{
		Timestamp fromTimestamp = new Timestamp(Long.valueOf(fromTime));
		Date fromDate = new Date(fromTimestamp.getTime());
		Timestamp toTimestamp = new Timestamp(Long.valueOf(toTime));
		Date toDate = new Date(toTimestamp.getTime());
		List<Alert> alertList = alertService.retreive(fromDate, toDate);
		return alertList;
	}
}
