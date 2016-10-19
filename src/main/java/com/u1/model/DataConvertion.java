package com.u1.model;

import java.util.ArrayList;
import java.util.List;

public  class DataConvertion<M>{
	private List<M> result = new ArrayList<M>();
	private String errorMessage="";
	
	
	/**
	 * @return the result
	 */
	public List<M> getResult() {
		return result;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean hasError(){
		if(!errorMessage.isEmpty())
			return true;
		else
			return false;
	}
	
	public void addResult(M result0){
		result.add(result0);
	}
	
	public void addMessage(String message){
		errorMessage+=message;
	}
}
