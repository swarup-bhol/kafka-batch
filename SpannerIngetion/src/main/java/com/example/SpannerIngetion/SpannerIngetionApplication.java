package com.example.SpannerIngetion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.SpannerIngetion.entity")
public class SpannerIngetionApplication {



	public static void main(String[] args) {
		SpringApplication.run(SpannerIngetionApplication.class, args);
	}


}
