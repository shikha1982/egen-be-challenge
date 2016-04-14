package com.egen.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egen.model.Metric;

@Repository
public class MetricDaoImpl implements MetricDao {

	@Autowired
	Datastore datastore;

	public void create(Metric metric) {
		datastore.save(metric);
	}

	public List<Metric> retreive() {
		final Query<Metric> query = datastore.createQuery(Metric.class);
		final List<Metric> metricList = query.asList();
		return metricList;
	}

	@Override
	public List<Metric> retreive(Date fromDate, Date toDate) {
		final MorphiaIterator<Metric, Metric> query = datastore.createQuery(Metric.class).field("dateCreated").lessThan(toDate)
				.field("dateCreated").greaterThan((fromDate)).fetch();
		List<Metric> resultList = new ArrayList<Metric>();
		Iterator<Metric> itrMetric = query.iterator();
		while (itrMetric.hasNext()) {
			Metric metric = itrMetric.next();
			resultList.add(metric);
		}
		return resultList;
	}
}