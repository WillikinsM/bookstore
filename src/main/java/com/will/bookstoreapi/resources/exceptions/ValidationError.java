package com.will.bookstoreapi.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldException> errors = new ArrayList<>();

	public ValidationError() {
		super();

	}

	public ValidationError(long timestamp, Integer status, String error) {
		super(timestamp, status, error);

	}

	@SuppressWarnings("unused")
	private List<FieldException> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldException(fieldName, message));
	}

}
