package br.com.javapress.domain.dto;


public class SuccessMessageDto {

	private String message;
	private Object value;
	
	public SuccessMessageDto(){
		
	}

	public SuccessMessageDto(String message){
		this.message = message;
	}
	
	public SuccessMessageDto(String message, Object value){
		this.message = message;
		this.value = value;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
