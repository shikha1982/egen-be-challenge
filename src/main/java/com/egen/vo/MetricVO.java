package com.egen.vo;

public class MetricVO {
	Long timeStamp;
	Double value;

	public MetricVO(Long timeStamp, Double value) {
		super();
		this.timeStamp = timeStamp;
		this.value = value;
	}

	public MetricVO() {
		super();
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
