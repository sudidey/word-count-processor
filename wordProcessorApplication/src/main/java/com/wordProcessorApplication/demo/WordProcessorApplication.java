package com.wordProcessorApplication.demo;

import java.nio.file.Path;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wordProcessorApplication.demo.config.ApplicationProperties;
import com.wordProcessorApplication.demo.service.WordProcessorService;

@SpringBootApplication
public class WordProcessorApplication implements CommandLineRunner {

	private final WordProcessorService service;
	
	@Autowired
	private ApplicationProperties configuration;

	public WordProcessorApplication(WordProcessorService processor) {
		this.service = processor;
	}

	public static void main(String[] args) {
		SpringApplication.run(WordProcessorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Path filePath = Path.of(configuration.getFilePath()); 
		Map<String, Object> results = service.processFile(filePath);
		System.out.println("Results: " + results);
	}

}
