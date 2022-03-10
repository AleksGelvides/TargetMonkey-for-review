package com.targetmonkey.registrationserviceimpl.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfigure {
    @Value(value = "${spring.kafka.server.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.cunsumer.groupId}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, String> responseConsumerFactory(){
        Map<String, Object> prop = new HashMap<>();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(prop);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
        responseModerationKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(responseConsumerFactory());
        return factory;
    }

}
