package com.egen;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.egen.controller.PWTAlertsController;
import com.egen.controller.PWTMetricsController;
import com.egen.model.Alert;
import com.egen.model.Metric;
import com.egen.vo.MetricVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersonalWeightTrackerApplication.class)
public class PersonalWeightTrackerApplicationTests {
	
	@Autowired
	PWTMetricsController pwtMetricsController;
	
	@Autowired
	PWTAlertsController pwtAlertController;
	
	@Test
	public void testCreateMetricWithNoAlerts() {
		Double baseWeight = 150D;
		Double newWeight = 155D;
		MetricVO metricVO = new MetricVO(1460597286446L, 155D);
		pwtMetricsController.create(metricVO, baseWeight);
		List<Metric> metricList = pwtMetricsController.retrieveByTimeRange("1460597286445", "1460597286447");
		List<Alert> alertList = pwtAlertController.retrieve();
		Boolean flag = Boolean.TRUE;
		for(Alert alert : alertList) {
			if(alert.getBaseWeight() == baseWeight && alert.getNewWeight() == newWeight) {
				flag = Boolean.FALSE;
			}
		}
		Assert.assertTrue(metricList.size() > 0);
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testCreateMetricWithOverWeightAlert() {
		Double baseWeight = 150D;
		Double newWeight = 170D;
		MetricVO metricVO = new MetricVO(1460597286446L, 155D);
		pwtMetricsController.create(metricVO, baseWeight);
		List<Metric> metricList = pwtMetricsController.retrieveByTimeRange("1460597286445", "1460597286447");
		List<Alert> alertList = pwtAlertController.retrieve();
		Boolean flag = Boolean.TRUE;
		for(Alert alert : alertList) {
			if(alert.getBaseWeight() == baseWeight && alert.getNewWeight() == newWeight && alert.getAlertType() == AlertType.OverWeight.toString()) {
				flag = Boolean.FALSE;
				break;
			}
		}
		Assert.assertTrue(metricList.size() > 0);
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testCreateMetricWithUnderWeightAlert() {
		Double baseWeight = 150D;
		Double newWeight = 130D;
		MetricVO metricVO = new MetricVO(1460597286446L, 155D);
		pwtMetricsController.create(metricVO, baseWeight);
		List<Metric> metricList = pwtMetricsController.retrieveByTimeRange("1460597286445", "1460597286447");
		List<Alert> alertList = pwtAlertController.retrieve();
		Boolean flag = Boolean.TRUE;
		for(Alert alert : alertList) {
			if(alert.getBaseWeight() == baseWeight && alert.getNewWeight() == newWeight && alert.getAlertType() == AlertType.UnderWeight.toString()) {
				flag = Boolean.FALSE;
				break;
			}
		}
		Assert.assertTrue(metricList.size() > 0);
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testRetreiveMetrics() {
		List<Metric> metricList = pwtMetricsController.retrieve();
		Assert.assertTrue(metricList.size() > 0);
	}
	
	@Test
	public void testRetreiveMetricsByTimeRange() {
		List<Metric> metricList = pwtMetricsController.retrieveByTimeRange("1460597286445", "1460597286447");
		Assert.assertTrue(metricList.size() > 0);
	}
	
	@Test
	public void testRetreiveAlerts() {
		List<Alert> alertList = pwtAlertController.retrieve();
		Assert.assertTrue(alertList.size() > 0);
	}
	
	@Test
	public void testRetreiveAlertByTimeRange() {
		List<Alert> alertList = pwtAlertController.retrieveByTimeRange("1460597286447", "1460597286445");
		Assert.assertTrue(alertList.size() == 0);
	}
}
