package com.stackroute.tldm.config;

import com.stackroute.tldm.model.ChannelMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

// Consumer configuration class for Channel-Messages
// Fetching group-id from cloud configurations.
// For JSON object to be sent through kafka we need this configuration.

@Configuration
@EnableKafka
public class ChannelKafkaConsumerConfig {

    @Value("${server.boot}")
    private String ipConfig;

    @Bean
    public ConsumerFactory<String, ChannelMessage> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ipConfig);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "${groupId2.boot}");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(ChannelMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChannelMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChannelMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}