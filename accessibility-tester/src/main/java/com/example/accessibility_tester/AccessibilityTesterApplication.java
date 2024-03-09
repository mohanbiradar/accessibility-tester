package com.example.accessibility_tester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AccessibilityTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessibilityTesterApplication.class, args);
	}

}
