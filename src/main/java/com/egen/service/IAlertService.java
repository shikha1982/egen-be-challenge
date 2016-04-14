package com.egen.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.egen.model.Alert;

@Service
public interface IAlertService {
	public void createAlert(Alert alert);
	public List<Alert> retrieve();
	public List<Alert> retreive(Date fromDate, Date toDate);
}
