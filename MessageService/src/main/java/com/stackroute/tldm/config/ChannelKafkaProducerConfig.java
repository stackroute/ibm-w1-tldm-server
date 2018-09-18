package com.stackroute.tldm.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.stackroute.tldm.model.ChannelMessage;

@Configuration
public class ChannelKafkaProducerConfig {
	@Bean
	public ProducerFactory<String, ChannelMessage> channelProducerFactory() {
		Map<String, Object> factoryConfig = new HashMap<>();

		factoryConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.122:9092");
		factoryConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		factoryConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(factoryConfig);
	}

	@Bean
	public KafkaTemplate<String, ChannelMessage> channelKafkaTemplate() {
		return new KafkaTemplate<>(channelProducerFactory());
	}

}
