package com.tcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tcs","com.tcs.controllers","com.tcs.config"})
public class GstRestserviceConsumeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GstRestserviceConsumeApplication.class, args);
	}
	
}
