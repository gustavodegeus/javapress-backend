package br.com.javapress.domain.dto;

public class SuccessMessageDto {

	private String message;
	private Object value;

	public SuccessMessageDto(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return value;
	}

	public void setObject(Object value) {
		this.value = value;
	}
}
