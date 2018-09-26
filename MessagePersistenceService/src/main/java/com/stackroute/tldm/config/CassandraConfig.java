package com.stackroute.tldm.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    // message_key_space is a namespace used in replication on nodes
    public static final String KEYSPACE = "message_key_space";

    // Enum identifying any schema actions to take at startup.
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    // Object to configure a CREATE KEYSPACE specification
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE)
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication();
        return Arrays.asList(specification);
    }

    // returns the keyspace used
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    // returns the specific package used
    public String[] getEntityBasePackages() {
        return new String[]{"com.stackroute.tldm"};
    }

}
