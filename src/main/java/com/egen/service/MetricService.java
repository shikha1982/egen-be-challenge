package com.egen.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.MetricDao;
import com.egen.model.Metric;

@Service
public class MetricService implements IMetricService {

	@Autowired
	private MetricDao metricDao;
	
	public void create(Metric metric) {
		metricDao.create(metric);
	}

	public List<Metric> retreive() {
		return metricDao.retreive();
	}

	public List<Metric> retreive(Date fromDate, Date toDate) {
		return metricDao.retreive(fromDate, toDate);
	}

}
