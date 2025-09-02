package com.wordProcessorApplication.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wordProcessorApplication.demo.ErrorConstants;
import com.wordProcessorApplication.demo.exception.FileProcessingException;
import com.wordProcessorApplication.demo.rule.WordsRule;

/**
 * Service responsible for analyzing words from input files. Applies business
 * rules defined in the assignment.
 */
@Service
public class WordProcessorService {
	private final List<WordsRule> rules;

	public WordProcessorService(List<WordsRule> rules) {
		this.rules = rules;
	}

	/**
	 * Processes the given file and returns analysis results.
	 *
	 * @param filePath Path to the input file containing words
	 * @return Map with keys "longWords" and "wordsStartingWith_m"
	 */

	public Map<String, Object> processFile(Path filePath) {
		try {
			List<String> words = Files.lines(filePath).flatMap(line -> Arrays.stream(line.split("\\W+")))
					.filter(word -> !word.isBlank()).toList();

			Map<String, Object> results = new HashMap<>();
			for (WordsRule rule : rules) {
				results.putAll(rule.apply(words));
			}
			return results;
		} catch (IOException e) {
			throw new FileProcessingException(ErrorConstants.FAILED_TO_PROCESS_FILE,
					"Failed to process file: " + filePath);
		}
	}

}
