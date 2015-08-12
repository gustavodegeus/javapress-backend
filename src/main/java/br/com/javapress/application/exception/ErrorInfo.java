package br.com.javapress.application.exception;

import java.io.Serializable;

public class ErrorInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489982056381209171L;
	
	private String url;
    private String message;
    private String error;
     
    public ErrorInfo(String url, String message, String error) {
        this.url = url;
        this.message = message;
        this.error = error;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
