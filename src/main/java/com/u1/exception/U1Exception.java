package com.u1.exception;

import java.util.List;

import javax.validation.ConstraintViolation;

import com.u1.util.Constants;

public class U1Exception extends RuntimeException {
	public U1Exception(String message){
		super(message);
	}
}
