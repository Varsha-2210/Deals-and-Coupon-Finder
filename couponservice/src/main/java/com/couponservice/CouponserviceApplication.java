package com.couponservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.Bean;






@SpringBootApplication
@EnableDiscoveryClient


public class CouponserviceApplication {
	
	   //This method acts as main method from where the execution 
	   //          of the microservice begins.
	public static void main(String[] args) {
		
		SpringApplication.run(CouponserviceApplication.class, args);
		System.out.println("Coupon running successfully....");
	}
	
	
	  
	
}

