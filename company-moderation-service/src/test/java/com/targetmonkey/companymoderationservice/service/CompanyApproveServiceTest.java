package com.targetmonkey.companymoderationservice.service;

import com.targetmonkey.companymoderationservice.domain.Company;
import com.targetmonkey.companymoderationservice.exceptions.CompanyValidationExceptions;
import com.targetmonkey.companymoderationservice.feign.FeignCompanyClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class CompanyApproveServiceTest {

    @Value(value = "${request.fns.path-to-json}")
    String path;

    @MockBean
    FeignCompanyClient feignCompanyClient;

    @InjectMocks
    CompanyApproveService service;


    @Test
    public void isApprovedTestMustNothingReturned() throws CompanyValidationExceptions {
        var company = new Company()
                .setCompanyName("VVVVV")
                .setCompanyGId("123123")
                .setId(1L);

        when(feignCompanyClient.getCompanyByInn(any())).thenReturn(readJson
                ("src/test/resources/json-from-feign-client/response.json"));
        service.isApproved(company);
    }

    private String readJson(String path) {
        StringBuilder jsonToString = new StringBuilder();
        try {
            FileReader reader = new FileReader(path);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                jsonToString.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        return jsonToString.toString();
    }

}
