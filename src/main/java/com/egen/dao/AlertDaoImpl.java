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

import com.egen.model.Alert;

@Repository
public class AlertDaoImpl implements AlertDao {

    @Autowired
    Datastore datastore;

    public void create(Alert alert) { 
        datastore.save(alert);
    }

    public List<Alert> retreive() {
        final Query<Alert> query = datastore.createQuery(Alert.class);
        
        final List<Alert> alertList = query.asList();
        return alertList;
    }

	@Override
	public List<Alert> retreive(Date fromDate, Date toDate) {
		final MorphiaIterator<Alert, Alert> query = datastore.createQuery(Alert.class).field("dateCreated").lessThan(toDate).field("dateCreated").greaterThan((fromDate)).fetch();
		List<Alert> resultList=new ArrayList<Alert>();
		Iterator<Alert> itrAlert = query.iterator();
		while(itrAlert.hasNext()){
			Alert alert = itrAlert.next();
			resultList.add(alert);
		}
		return resultList;
	}
}