package com.targetmonkey.companymoderationservice.facade;

import com.targetmonkey.companymoderationservice.service.CompanyApproveService;
import com.targetmonkey.companymoderationservice.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModerationFacade {
    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private CompanyApproveService approveService;

    public void checkCompany(){

    }
}
