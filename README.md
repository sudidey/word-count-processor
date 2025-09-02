# word-count-processor

# Word Processor - Count Words Assignment

This project is a simple **Word Processor / Indexing Service** built in Java with **Spring Boot**.  
It demonstrates how to process a text file and apply the following business rules:

- Count and return the **number of words** starting with `"M"` or `"m"`.
- Return a list of all words **longer than 5 characters**.
- Provide results in a structured way (`Map<String, Object>`).

## 🚀 Features
- File reading and processing
- Filtering long words
- Counting words starting with `M/m`
- Unit tests with JUnit 5 & AssertJ
- Integration tests with `@SpringBootTest`
- Configurable file input via `application.properties`

## 🛠️ Tech Stack
- Java 17+
- Spring Boot 3+
- Maven
- JUnit 5
- AssertJ

## 📂 Project Structure
src
├── main
│   ├── java/com/wordProcessorApplication/demo
│   │   ├── WordProcessorApplication.java      # Spring Boot entry point
│   │   ├── service
│   │   │   └── WordProcessorService.java      # Core word processing logic
│   │   ├── util
│   │   │   └── FileUtil.java                  # File reading helper
│   │   ├── config
│   │   │   └── ApplicationProperties.java     # Centralized application properties binding
│   │   ├── exception
│   │   │   └── FileProcessingException.java   # Custom exception for file errors
│   │   └── rule
│   │       ├── WordsRule.java                 # Common rule interface
│   │       ├── StartsWithRule.java            # Rule: words starting with specific letter
│   │       └── LongWordRule.java              # Rule: long word detection
│   │
│   └── resources
│       └── application.properties             # Spring Boot application configuration
│
└── test
    └── java/com/example/wordprocessor
        ├── WordProcessorServiceTest.java      # Unit tests


## 📦 Dependencies

The project uses the following Maven dependencies:

<dependencies>
    <!-- Spring Boot Starter (Core Framework) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- Spring Boot Test (JUnit 5, Mockito, Spring Context for integration tests) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- AssertJ (Fluent assertions for testing) -->
    <dependency>
        <groupId>org.assertj</groupId>
        <artifactId>assertj-core</artifactId>
        <version>3.26.3</version>
        <scope>test</scope>
    </dependency>
</dependencies>


## ⚙️ Configuration
Set the input file in `src/main/resources/application.properties`:

```properties
wordprocessor.input-file=classpath:sample.txt

## Run the Application
mvn spring-boot:run


## Example Output

Given the following sample.txt:
apple banana mango melon strawberry grapes papaya muskmelon blueberry orange mangoste

Result:

{
  "longWords": ["banana", "strawberry", "grapes", "papaya", "muskmelon", "blueberry", "orange", "mangosteen"],
  "wordsStartingWith_m": 4
}


## Running Tests
mvn test

## Example assertion in WordProcessorServiceTest.java:

assertThat(result).containsKeys("longWords", "wordsStartingWith_m");
assertThat((Long) result.get("wordsStartingWith_m")).isGreaterThan(0);
