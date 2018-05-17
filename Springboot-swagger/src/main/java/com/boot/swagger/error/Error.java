package com.boot.swagger.error;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@ApiModel(description = "Error object Model")
public class Error {
	@ApiModelProperty(notes = "client provided error code")
	private String errorCode;
	@ApiModelProperty(notes = "Describing errir message")
	private String errorMessage;

	public Error(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
