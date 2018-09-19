package com.stackroute.tldm.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

@Configuration
public class CassandraConfig {
	
	public static final String KEYSPACE = "message_keyspace";

	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);

		return Arrays.asList(specification);
	}

//	    @Override
//	    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//	        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
//	    }

	protected String getKeyspaceName() {
		return KEYSPACE;
	}

	public String[] getEntityBasePackages() {
		return new String[] { "com.stackroute.tldm" };
	}

}
