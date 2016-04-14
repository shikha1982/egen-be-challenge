package com.egen.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.egen.model.Metric;

@Repository
public interface MetricDao {
	public void create(Metric metric);
    public List<Metric> retreive();
    public List<Metric> retreive(Date fromDate, Date toDate);
}
