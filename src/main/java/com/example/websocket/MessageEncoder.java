package com.example.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.example.model.MyMessage;
import com.google.gson.Gson;


public class MessageEncoder implements Encoder.Text<MyMessage> {

	private static Gson gson = new Gson();

	@Override
	public String encode(MyMessage message) throws EncodeException {
		return gson.toJson(message);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
		// Custom initialization logic
	}

	@Override
	public void destroy() {
		// Close resources
	}
}
