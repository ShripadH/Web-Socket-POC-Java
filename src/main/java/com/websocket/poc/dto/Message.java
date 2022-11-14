package com.websocket.poc.dto;

public class Message {
	
	@Override
	public String toString() {
		return "Message [payload=" + payload + "]";
	}

	private String payload;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
