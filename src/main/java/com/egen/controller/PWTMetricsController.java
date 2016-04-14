package com.egen.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Metric;
import com.egen.service.IMetricService;
import com.egen.service.RuleEngineService;
import com.egen.vo.MetricVO;

@RestController
@RequestMapping("/metric")
public class PWTMetricsController {

	@Autowired
	RuleEngineService ruleEngineService;

	@Autowired
	IMetricService metricService;

	@RequestMapping(value = "/create/baseweight/{baseWeight}", method = RequestMethod.POST, produces = "application/json")
	public void create(@RequestBody MetricVO metricVO, @PathVariable("baseWeight") Double baseWeight) {
		Timestamp ts = new Timestamp(metricVO.getTimeStamp());
		Date date = new Date(ts.getTime());
		Metric metric = new Metric(null, metricVO.getValue(), date);
		metricService.create(metric);
		ruleEngineService.executeRules(baseWeight, metricVO.getValue());
	}

	@RequestMapping(value = "/retrieve", method = RequestMethod.GET, produces = "application/json")
	public List<Metric> retrieve() {
		List<Metric> metricList = metricService.retreive();
		return metricList;
	}

	@RequestMapping(value = "/retrieve/fromtime/{fromTime}/totime/{toTime}", method = RequestMethod.GET, produces = "application/json")
	public List<Metric> retrieveByTimeRange(@PathVariable("fromTime") String fromTime,
			@PathVariable("toTime") String toTime) {
		Timestamp fromTimestamp = new Timestamp(Long.valueOf(fromTime));
		Date fromDate = new Date(fromTimestamp.getTime());
		Timestamp toTimestamp = new Timestamp(Long.valueOf(toTime));
		Date toDate = new Date(toTimestamp.getTime());
		List<Metric> metricList = metricService.retreive(fromDate, toDate);
		return metricList;
	}
}
