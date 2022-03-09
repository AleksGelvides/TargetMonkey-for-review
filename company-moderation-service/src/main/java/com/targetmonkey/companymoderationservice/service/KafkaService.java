package com.targetmonkey.companymoderationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetmonkey.companymoderationservice.domain.Company;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaService {
    @Value(value = "${spring.kafka.producer.topicName}")
    private String topicName;

    @Autowired
    private CompanyApproveService companyApproveService;

    @Autowired
    private KafkaTemplate<String, Company> resultTemplate;

    @KafkaListener(topics = "companyForReview",
            groupId = "consumerModerationGroup",
            containerFactory = "companyConcurrentKafkaListenerContainerFactory")
    public void listenerGroupCompanyForModeration(ConsumerRecord<String, String> record) {
        log.info("Listener worked");
        Company company = new Company();
        try {
            company = getCompany(record.value());
            if (company.getId() == 0) {
                log.warn("No content");
            } else {
                if(companyApproveService.isApproved(company)) {
                    sendModerationResult(company.setModerationComment("Confirmed"));
                }
            }
        } catch (Exception e) {
            sendModerationResult(company.setModerationComment(e.getMessage()));
        }
    }

    private void sendModerationResult(Company company) {
        ListenableFuture<SendResult<String, Company>> future =
                resultTemplate.send(topicName, company);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Company>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Company> result) {
                log.info("The result of moderation was sent to the Kafka server");
            }
        });
    }

    private Company getCompany(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(json);
            return new Company()
                    .setId(jsonNode.get("id").asLong())
                    .setCompanyName(jsonNode.get("companyName").asText())
                    .setCompanyGId(jsonNode.get("companyGId").asText());
        } catch (JsonProcessingException e) {
            throw new Exception("The company has not been verified, contact the administrator");
        }
    }


}
