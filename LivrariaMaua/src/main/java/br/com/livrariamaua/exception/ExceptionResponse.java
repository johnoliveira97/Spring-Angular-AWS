package br.com.livrariamaua.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 79862659623717425L;

	@JsonProperty("responseStatus")
	@NonNull
	private String responseStatus;

	@JsonProperty("messages")
	private List<ExceptionMessage> messages = new ArrayList<>();

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public List<ExceptionMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ExceptionMessage> messages) {
		this.messages = messages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
