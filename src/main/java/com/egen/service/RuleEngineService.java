package com.egen.service;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.rules.OverWeightRule;
import com.egen.rules.UnderWeightRule;

@Service
public class RuleEngineService {
	
	private static RulesEngine rulesEngine;
	
	@Autowired
	OverWeightRule overWightRule;
	
	@Autowired
	UnderWeightRule underWeightRule;
	
	
	public RulesEngine getRuleEngine()
	{
		if(null != rulesEngine) {
			RulesEngineBuilder ruleEngineBuilder = RulesEngineBuilder.aNewRulesEngine();
			rulesEngine = ruleEngineBuilder.build();
		}
		return rulesEngine;
	}
	
	public void executeRules(Double baseWeight, double newWeight)
	{
		//RulesEngine rulesEngine = this.getRuleEngine();
		RulesEngineBuilder ruleEngineBuilder = RulesEngineBuilder.aNewRulesEngine();
		rulesEngine = ruleEngineBuilder.build();
		rulesEngine.clearRules();
		
		overWightRule.setBaseWeight(baseWeight);
		overWightRule.setNewWeight(newWeight);
		
		underWeightRule.setBaseWeight(baseWeight);
		underWeightRule.setNewWeight(newWeight);
		
		
		rulesEngine.registerRule(overWightRule);
		rulesEngine.registerRule(underWeightRule);
		
		rulesEngine.fireRules();
	}
}
