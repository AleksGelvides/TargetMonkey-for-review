package com.targetmonkey.companymoderationservice.service;

import com.targetmonkey.companymoderationservice.domain.Company;
import com.targetmonkey.companymoderationservice.exceptions.CompanyValidationExceptions;
import com.targetmonkey.companymoderationservice.feign.FeignCompanyClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.shaded.org.bouncycastle.util.encoders.UTF8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class CompanyApproveServiceTest {

    @Mock
    FeignCompanyClient feignCompanyClient;

    @InjectMocks
    CompanyApproveService service;


    @Test
    public void isApprovedTestMustReturnedTrue() throws CompanyValidationExceptions {
        var company = new Company()
                .setCompanyName("мокрый нос")
                .setCompanyGId("3805733849")
                .setId(1L);

        when(feignCompanyClient.getCompanyByInn(any())).thenReturn(readJson
                ("src/test/resources/json-from-feign-client/response.json"));
        boolean isApproved = service.isApproved(company);
        assertTrue(isApproved);
    }

    @Test
    public void isApprovedTestMustReturnedCompanyNotExist() {
        var company = new Company()
                .setCompanyName("Сухой нос")
                .setCompanyGId("Точно неправильный ИНН")
                .setId(1L);
        when(feignCompanyClient.getCompanyByInn(any())).thenReturn("Компания не обнаружена");
        assertThrows(CompanyValidationExceptions.class, () -> {
            service.isApproved(company);
        });
        try {
            service.isApproved(company);
        } catch (CompanyValidationExceptions e) {
            assertEquals("The company does not exist", e.getMessage());
        }
    }

    @Test
    public void isApprovedTestMustReturnedCompanyNameNotMath() {
        var company = new Company()
                .setCompanyName("Сухой нос")
                .setCompanyGId("3805733849")
                .setId(1L);
        when(feignCompanyClient.getCompanyByInn(any())).thenReturn(readJson
                ("src/test/resources/json-from-feign-client/response.json"));
        assertThrows(CompanyValidationExceptions.class, () -> {
            service.isApproved(company);
        });
        try {
            service.isApproved(company);
        } catch (CompanyValidationExceptions e) {
            assertEquals("The company name does not match", e.getMessage());
        }
    }

    @Test
    public void isApprovedTestMustReturnedFeignException() {
        var company = new Company()
                .setCompanyName("Сухой нос")
                .setCompanyGId("3805733849")
                .setId(1L);
        when(feignCompanyClient.getCompanyByInn(any())).thenThrow(FeignException.class);
        assertThrows(CompanyValidationExceptions.class, () -> {
            service.isApproved(company);
        });
        try {
            service.isApproved(company);
        } catch (CompanyValidationExceptions e) {
            assertEquals("Unknown error. Contact your administrator", e.getMessage());
        }
    }


    private String readJson(String path) {
        StringBuilder jsonToString = new StringBuilder();
        try {
            FileReader reader = new FileReader(path, Charset.forName("UTF-8"));
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                jsonToString.append(scanner.nextLine());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return jsonToString.toString();
    }

}
