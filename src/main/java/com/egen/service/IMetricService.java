package com.egen.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.egen.model.Metric;

@Service
public interface IMetricService {
	public void create(Metric metric);
	public List<Metric> retreive();
	public List<Metric> retreive(Date fromDate, Date toDate);
}
