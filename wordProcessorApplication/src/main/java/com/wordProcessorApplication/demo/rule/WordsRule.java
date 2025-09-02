package com.wordProcessorApplication.demo.rule;

import java.util.List;
import java.util.Map;

/**
 * Interface for word processing rules.
 */
public interface WordsRule {
	
	 Map<String, Object> apply(List<String> words);

}
