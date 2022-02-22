package com.targetmonkey.companymoderationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.targetmonkey.companymoderationservice.domain.Company;
import com.targetmonkey.companymoderationservice.exceptions.CompanyValidationExceptions;
import com.targetmonkey.companymoderationservice.feign.FeignCompanyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompanyApproveService {
    @Autowired
    FeignCompanyClient companyClient;

    public boolean isApproved(Company company) throws CompanyValidationExceptions {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(companyClient.getCompanyByInn(company.getInn()));

            ArrayNode arrayNodeResponse = (ArrayNode) jsonNode.get("items");
            log.info(arrayNodeResponse.toString());

            JsonNode ul = arrayNodeResponse.elements().next().get("ЮЛ");
            log.info(ul.toString());

            String companyNameFNC = ul.get("НаимСокрЮЛ").asText();

            if(!companyNameFNC.contains(company.getCompanyName().toUpperCase())){
                throw new CompanyValidationExceptions("The company name does not match");
            }

            return true;

        }catch (IllegalStateException | NullPointerException | JsonProcessingException e){
            log.error(e.getMessage());
            throw new CompanyValidationExceptions("The company does not exist");
        }
    }
}
