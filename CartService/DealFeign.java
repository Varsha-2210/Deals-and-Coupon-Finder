package com.cartservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("deal-service")
public interface DealFeign {
	@GetMapping("/findById/{id}")
    public Deal getDealById(@PathVariable Long id) ;
        
}
