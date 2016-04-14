package com.egen.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.egen.model.Alert;

@Repository
public interface AlertDao {
	public void create(Alert alert);
    public List<Alert> retreive();
    public List<Alert> retreive(Date fromDate, Date toDate);
}
