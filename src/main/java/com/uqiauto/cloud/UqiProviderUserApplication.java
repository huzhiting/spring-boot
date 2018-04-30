package com.uqiauto.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.uqiauto.cloud"})
public class UqiProviderUserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UqiProviderUserApplication.class, args);
	}
}
