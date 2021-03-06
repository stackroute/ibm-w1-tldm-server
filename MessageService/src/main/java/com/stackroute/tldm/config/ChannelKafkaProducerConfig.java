package com.stackroute.tldm.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.stackroute.tldm.model.ChannelMessage;

// Producer Configuration for Channel-Messages
// For JSON object to be sent through kafka we need this configuration.

@Configuration
public class ChannelKafkaProducerConfig {

	@Value("${server.boot}")
	private String ipConfig;

	@Bean
	public ProducerFactory<String, ChannelMessage> channelProducerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ipConfig);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, ChannelMessage> channelKafkaTemplate() {
		return new KafkaTemplate<>(channelProducerFactory());
	}

}
