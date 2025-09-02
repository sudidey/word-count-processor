package com.wordProcessorApplication.demo.rule;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wordProcessorApplication.demo.config.ApplicationProperties;

/**
 * Rule: Return words longer than a configurable min length.
 */
@Component
public class LongWordRule implements WordsRule {

	@Autowired
	private ApplicationProperties configuration;

	@Override
	public Map<String, Object> apply(List<String> words) {
		 Map<String, Object> result = new HashMap<>();

	        if (words == null || words.isEmpty()) {
	            result.put("longWords", Collections.emptyList());
	            return result;
	        }

	        // Ensure a valid min length
	        int minLength = configuration.getWordsMinLength();
	        if (minLength <= 0) {
	            minLength = 1; // default to 1 if misconfigured
	        }
		
		
		List<String> longWords = words.stream().filter(w -> w.length() > configuration.getWordsMinLength())
				.collect(Collectors.toList());

		 result = new HashMap<>();
		result.put("longWords(minLength=" + configuration.getWordsMinLength() + ")", longWords);
		return result;
	}
}
