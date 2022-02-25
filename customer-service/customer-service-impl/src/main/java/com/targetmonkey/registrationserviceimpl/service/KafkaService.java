package com.targetmonkey.registrationserviceimpl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetmonkey.registrationserviceapi.dto.companies.CompanyAdminDto;
import com.targetmonkey.registrationserviceapi.enums.Status;
import com.targetmonkey.registrationserviceimpl.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
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
    private KafkaTemplate<String, CompanyAdminDto> kafkaCompanyTemplate;

    @Autowired
    private CompanyRepository repository;

    public void sendCompanyForReview(CompanyAdminDto companyAdminDto){
        ListenableFuture<SendResult<String, CompanyAdminDto>> future =
                kafkaCompanyTemplate.send(
                        topicName, String.valueOf(companyAdminDto.hashCode()), companyAdminDto);
        future.addCallback(new ListenableFutureCallback<SendResult<String, CompanyAdminDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, CompanyAdminDto> result) {
                log.info("Company: " + companyAdminDto.hashCode() + " was send to kafka server");
            }
        });
    }

    @KafkaListener(topics = "resultModeration",
            groupId = "consumerGroup",
    containerFactory = "responseModerationKafkaListenerContainerFactory")
    public void listenerGroupResultModeration(String response){
        changeStatus(toAdminCompanyDto(response));
    }

    private CompanyAdminDto toAdminCompanyDto(String responce){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responce);
            return new CompanyAdminDto()
                    .setId(jsonNode.get("id").asLong())
                    .setCompanyName(jsonNode.get("companyName").asText())
                    .setCompanyGId(jsonNode.get("companyGId").asText())
                    .setModerationComment(jsonNode.get("moderationComment").asText());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    private void changeStatus(CompanyAdminDto company){
        try {
            var changeCompany = repository.findById(company.getId()).get();
            if (company.getModerationComment().equals("Confirmed")) {
                changeCompany.setStatus(Status.ACTIVE)
                        .setModerationComment(company.getModerationComment());
            } else {
                changeCompany.setStatus(Status.DISABLE)
                        .setModerationComment(company.getModerationComment());
            }
            repository.save(changeCompany);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
