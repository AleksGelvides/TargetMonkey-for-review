package com.targetmonkey.companymoderationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.targetmonkey.companymoderationservice.domain.Company;
import com.targetmonkey.companymoderationservice.exceptions.CompanyValidationExceptions;
import com.targetmonkey.companymoderationservice.feign.FeignCompanyClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CompanyApproveService {
    @Autowired
    private FeignCompanyClient companyClient;

    public boolean isApproved(Company company) throws CompanyValidationExceptions {

        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode jsonNode = mapper.readTree(companyClient.getCompanyByInn(company.getCompanyGId()));

            ArrayNode arrayNodeResponse = (ArrayNode) jsonNode.get("items");
            log.info(arrayNodeResponse.toString());

            JsonNode ul = arrayNodeResponse.elements().next().get("ЮЛ");
            log.info(ul.toString());

            String companyNameFNC = ul.get("НаимСокрЮЛ").asText();

            if (!regexFind(companyNameFNC, company.getCompanyName())) {
                throw new CompanyValidationExceptions("The company name does not match");
            }
            return true;
        } catch (NoSuchElementException | IllegalStateException | NullPointerException | JsonProcessingException e) {
            log.error(e.getMessage());
            throw new CompanyValidationExceptions("The company does not exist");
        } catch (FeignException ex){
            log.error(ex.getMessage());
            throw new CompanyValidationExceptions("Unknown error. Contact your administrator");
        }
    }

    private boolean regexFind(String fullCompanyName, String companyName){
        Pattern pattern = Pattern.compile(companyName.toUpperCase());
        Matcher matcher = pattern.matcher(fullCompanyName);
        return matcher.find();
    }
}
