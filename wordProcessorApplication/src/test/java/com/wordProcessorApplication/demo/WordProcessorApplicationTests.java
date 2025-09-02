package com.wordProcessorApplication.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wordProcessorApplication.demo.service.WordProcessorService;

/**
 * Integration tests for {@link WordProcessorService}.
 *
 * <p>This test class verifies that the WordProcessor correctly applies
 * the business rules defined in the "Count Words" assignment:
 * <ul>
 *   <li>Count the number of words starting with 'M' or 'm'.</li>
 *   <li>Return all words longer than 5 characters.</li>
 * </ul>
 */

@SpringBootTest
class WordProcessorApplicationTests {

    @Autowired
    private WordProcessorService wordProcessor;
    
    /**
     * Ensures that processing a sample input file produces
     * both "longWords" and "wordsStartingWith_m" results
     * according to the requirements.
     */

    @Test
    void testProcessFile_withSampleFile() throws Exception {
        // given a temporary file with words
        Path tempFile = Files.createTempFile("words", ".txt");
        Files.writeString(tempFile, String.join(System.lineSeparator(),
                "apple",
                "mango",
                "banana",
                "melon",
                "strawberry",
                "grapes",
                "papaya",
                "muskmelon",
                "blueberry",
                "orange",
                "mangosteen",
                "manngosteen"
        ));

        Map<String, Object> result = wordProcessor.processFile(tempFile);

     // Assert
        assertThat(result)
            .as("Result map should contain both required keys")
            .containsKeys("longWords", "wordsStartingWith_m");

        assertThat((Long) result.get("wordsStartingWith_m"))
            .as("Number of words starting with 'M/m' should be greater than zero")
            .isGreaterThan(0);

        assertThat((Iterable<String>) result.get("longWords"))
            .as("List of long words should not be empty")
            .isNotEmpty();
    }
}
