package com.wordProcessorApplication.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileProcessingException extends RuntimeException {
	private static final long serialVersionUID = 6434654148768665881L;

	private final String message;
	private final String errorCode;

	public FileProcessingException(String errorCode, String message) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
}
