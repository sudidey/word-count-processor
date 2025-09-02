package com.wordProcessorApplication.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wordProcessorApplication.demo.service.WordProcessorService;

/**
 * Integration tests for {@link WordProcessorService}.
 *
 * <p>
 * This test class verifies that the WordProcessor correctly applies the
 * business rules defined in the "Count Words" assignment:
 * <ul>
 * <li>Count the number of words starting with 'M' or 'm'.</li>
 * <li>Return all words longer than 5 characters.</li>
 * </ul>
 */

@SpringBootTest
class WordProcessorApplicationTests {
	@Autowired
	private WordProcessorService wordProcessor;

	/**
	 * Ensures that processing a sample input file produces both "longWords" and
	 * "wordsStartingWith_m" results according to the requirements.
	 */
	@Test
	void testProcessFile_withSampleFile() throws Exception {
		// given a temporary file with words
		Path tempFile = Files.createTempFile("words", ".txt");
		Files.writeString(tempFile, String.join(System.lineSeparator(), "apple", "mango", "banana", "melon",
				"strawberry", "grapes", "papaya", "muskmelon", "blueberry", "orange", "mangosteen", "manngosteen"));

		// when
		Map<String, Object> result = wordProcessor.processFile(tempFile);

		// then
		assertThat(result).as("Result map should contain both required keys").containsKeys("longWords",
				"wordsStartingWith_m");

		assertThat((Long) result.get("wordsStartingWith_m")).as(
				"Number of words starting with 'M/m' should be 6 (mango, melon, muskmelon, mangosteen, manngosteen)")
				.isEqualTo(5);

		@SuppressWarnings("unchecked")
		List<String> longWords = (List<String>) result.get("longWords");

		assertThat(longWords).as("List of long words should contain expected words")
				.contains("strawberry", "muskmelon", "mangosteen", "manngosteen").doesNotContain("apple", "mango"); // short
																													// words
	}
}
