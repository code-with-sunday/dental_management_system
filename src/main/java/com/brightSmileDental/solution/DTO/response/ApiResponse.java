package com.brightSmileDental.solution.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private boolean success;
	private String responseCode;
	private String message = "Successful";
	private T data;
	private String clientUpdateToken;

	public ApiResponse(String responseCode, String message) {
		this.responseCode = responseCode;
		this.message = message;
	}

	public ApiResponse(T data) {
		this.success = true;
		this.data = data;
	}

	public ApiResponse(boolean success, String responseCode, String message, T data, String clientUpdateToken) {
		this.success = success;
		this.responseCode = responseCode;
		this.message = message;
		this.data = data;
		this.clientUpdateToken = clientUpdateToken;
	}

	public ApiResponse(boolean success, String responseCode, String message, T data) {
		this.success = success;
		this.responseCode = responseCode;
		this.message = message;
		this.data = data;
	}

	public ApiResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public ApiResponse(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public ApiResponse(String message, T data) {
		this.success = true;
		this.message = message;
		this.data = data;

	}
}