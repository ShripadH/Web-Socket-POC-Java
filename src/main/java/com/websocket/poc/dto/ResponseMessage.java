package com.websocket.poc.dto;

public class ResponseMessage {

	public ResponseMessage(String payload) {
		super();
		this.payload = payload;
	}

	private String payload;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
	
}
