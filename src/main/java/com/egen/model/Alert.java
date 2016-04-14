package com.egen.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("alert")
public class Alert {
	
	public Alert(ObjectId id, Date dateCreated, Double baseWeight, Double newWeight, String alertType) {
		super();
		this.id = id;
		this.dateCreated = dateCreated;
		this.baseWeight = baseWeight;
		this.newWeight = newWeight;
		this.alertType = alertType;
	}
	
	public Alert() {
		super();
	}

	@Id
	private ObjectId id;
	
	private Date dateCreated;
	
	private Double baseWeight;
	
	private Double newWeight;
	
	private String alertType;
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date date) {
		this.dateCreated = date;
	}
	
	public String getAlertType() {
		return alertType;
	}
	
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public Double getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(Double baseWeight) {
		this.baseWeight = baseWeight;
	}

	public Double getNewWeight() {
		return newWeight;
	}

	public void setNewWeight(Double newWeight) {
		this.newWeight = newWeight;
	}
}
