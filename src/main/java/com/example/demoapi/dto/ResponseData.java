package com.example.demoapi.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<Data> {
	
	private boolean status;
	private List<String> messages = new ArrayList<String>();
	private Data payload;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Data getPayload() {
		return payload;
	}

	public void setPayload(Data payload) {
		this.payload = payload;
	}
}
