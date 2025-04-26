package com.example.SpannerIngetion;

import com.example.SpannerIngetion.service.JsonProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.SpannerIngetion.entity")
@EnableJpaRepositories("com.example.SpannerIngetion.repository")
public class SpannerIngetionApplication implements CommandLineRunner {

	@Autowired
	private  JsonProcessingService jsonProcessingService;

	public static void main(String[] args) {
		SpringApplication.run(SpannerIngetionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jsonProcessingService.processJsonToSpanner();
	}
}
