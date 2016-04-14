package com.egen.rules;

import java.util.Date;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import com.egen.AlertType;
import com.egen.model.Alert;
import com.egen.service.IAlertService;

@Rule(name = "overweightrule", description = "Rule to check weight is over weight")
public class OverWeightRule {
	
	@Autowired
	IAlertService alertService;
	
	private Double baseWeight;
	private Double newWeight;
	
	public OverWeightRule(Double baseWeight, Double newWeight) {
		super();
		this.baseWeight = baseWeight;
		this.newWeight = newWeight;
	}
	
	public OverWeightRule() {
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

	@Condition
	public boolean when() {
		if(newWeight <= baseWeight) {
			return false;
		} else {
			Double diff = newWeight - baseWeight;
			Double perGain = (diff / baseWeight)*100;
			if(perGain >= 10.0D) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Action(order = 1)
	public void then() throws Exception {
		Date dateCreated = new Date(System.currentTimeMillis());
		Alert alert = new Alert(null, dateCreated, baseWeight, newWeight, AlertType.OverWeight.toString());
		alertService.createAlert(alert);
	}

}
