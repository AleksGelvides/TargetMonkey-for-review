package com.targetmonkey.companymoderationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${spring.application.name}", url = "${request.fns.api-request}")
public interface FeignCompanyClient {

    @GetMapping("?req={inn}&key=${request.fns.api-key}")
    String getCompanyByInn(@PathVariable String inn);
}
