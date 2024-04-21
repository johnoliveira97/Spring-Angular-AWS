package br.com.livrariamaua.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class ExceptionMessage implements Serializable {

	private static final long serialVersionUID = -7372591778006086580L;

	@JsonProperty("statusCode")
	private String statusCode = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("detail")
	private String detail = null;

	public ExceptionMessage(int value, String info, String string) {
		
	}

	public ExceptionMessage(String name, String info, String string) {
		this.statusCode=name;
		this.description=info;
		this.detail=string;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
