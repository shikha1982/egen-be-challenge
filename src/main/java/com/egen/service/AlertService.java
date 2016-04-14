package com.egen.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.AlertDao;
import com.egen.model.Alert;

@Service
public class AlertService implements IAlertService{
	
	@Autowired
	private AlertDao alertDao;
	@Override
	public void createAlert(Alert alert)
	{
		alertDao.create(alert);
	}
	
	@Override
	public List<Alert> retrieve() {
		return alertDao.retreive();
	}

	@Override
	public List<Alert> retreive(Date fromDate, Date toDate) {
		return alertDao.retreive(fromDate, toDate);
	}
}
