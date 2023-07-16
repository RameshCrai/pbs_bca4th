package com.pbt.IdgeneratorConfiguration;

import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserIDconfiguration {
	
    @Bean(name = "userIdGenerator")
    public IdentifierGenerator getUserIdGenerator() {
        return new UserIDGenerator();
    }

}
