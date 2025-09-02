package com.wordProcessorApplication.demo.rule;

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
		List<String> longWords = words.stream().filter(w -> w.length() > configuration.getWordsMinLength())
				.collect(Collectors.toList());

		Map<String, Object> result = new HashMap<>();
		result.put("longWords(minLength=" + configuration.getWordsMinLength() + ")", longWords);
		return result;
	}
}
