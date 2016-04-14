package com.egen.configuration;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.egen.rules.OverWeightRule;
import com.egen.rules.UnderWeightRule;
import com.mongodb.MongoClient;

@Configuration
public class MorphiaAutoConfiguration {

    @Autowired
    private MongoClient mongoClient; // created from MongoAutoConfiguration

    @Bean
    public Datastore datastore() {
        Morphia morphia = new Morphia();

        // map entities, there is maybe a better way to find and map all entities
        ClassPathScanningCandidateComponentProvider entityScanner = new ClassPathScanningCandidateComponentProvider(
                true);
        entityScanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        for (BeanDefinition candidate : entityScanner.findCandidateComponents("com.test.model")) {
            try {
                morphia.map(Class.forName(candidate.getBeanClassName()));
            }
            catch (ClassNotFoundException e) {
                System.out.println("exception");
            }
        }

        return morphia.createDatastore(mongoClient, "iot-db"); // "dataStoreInstanceId" may come from
                                                                 // properties?
    }
    
    @Bean 
	public OverWeightRule createOverWeightRuleBean() {
		OverWeightRule overWeightRule = new OverWeightRule();
        return overWeightRule;
    }
	
	@Bean 
	public UnderWeightRule createUnderWeightRuleBean() {
		UnderWeightRule underWeightRule = new UnderWeightRule();
        return underWeightRule;
    }
}