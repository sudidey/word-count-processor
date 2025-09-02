package com.wordProcessorApplication.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationProperties {

	@Value("${app.file-path}")
	private String filePath;

	@Value("${wordprocessor.minLength}")
	private int wordsMinLength;
	
	@Value("${wordprocessor.startswith}")
	private String wordStartWith;

}