package com.pbt.IdgeneratorConfiguration;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserIDGenerator implements IdentifierGenerator {


	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {

	
		return UUID.randomUUID().toString();
	}



}
