package com.asynclife.hello.risk;

import java.util.List;

import org.junit.Test;

import com.asynclife.hello.risk.Customer;
import com.asynclife.hello.risk.Risk;
import com.asynclife.hello.risk.RiskFactory;
import com.asynclife.hello.risk.RiskManager;

public class TestDriver {
	
	@Test
	public void testAddRiskAndSort() {
		
		RiskManager rm = RiskManager.getInstance();
		rm.addRisks(RiskFactory.createRisksWithOrderless());
		
		List<Risk> riskQueue = rm.getRiskQueue();
		
		for(Risk risk : riskQueue) {
			System.out.println(risk);
		}
		
	}
	
	@Test
	public void testEval() {
		RiskManager rm = RiskManager.getInstance();
		rm.addRisks(RiskFactory.createRisksWithOrderless());
		rm.evaluation(new Customer());
		
	}
	
}
