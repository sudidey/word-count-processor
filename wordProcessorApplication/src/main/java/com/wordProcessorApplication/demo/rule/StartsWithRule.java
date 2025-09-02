package com.wordProcessorApplication.demo.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wordProcessorApplication.demo.config.ApplicationProperties;

/**
 * Rule: Count words starting with a configurable prefix.
 */
@Component
public class StartsWithRule implements WordsRule {

	private final String prefix;

	@Autowired
	public StartsWithRule(ApplicationProperties configuration) {
		this.prefix = configuration.getWordStartWith().trim().toLowerCase();
	}

	@Override
	public Map<String, Object> apply(List<String> words) {
		long count = words.stream().filter(w -> w != null && w.trim().toLowerCase().startsWith(prefix)).count();

		Map<String, Object> result = new HashMap<>();
		result.put("wordsStartingWith_" + prefix, count);
		return result;
	}
}
