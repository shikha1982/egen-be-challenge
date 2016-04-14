package com.egen.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("metric")
public class Metric {
	
	public Metric(ObjectId id, Double weight, Date dateCreated) {
		super();
		this.id = id;
		this.weight = weight;
		this.dateCreated = dateCreated;
	}
	
	public Metric() {
	}
	
	@Id
	private ObjectId id;
	
	private Double weight;
	
	private Date dateCreated;
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date date) {
		this.dateCreated = date;
	}
}
