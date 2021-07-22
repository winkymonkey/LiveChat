package org.example.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.example.model.MyMessage;

import com.google.gson.Gson;


public class MessageDecoder implements Decoder.Text<MyMessage> {

    private static Gson gson = new Gson();

    @Override
    public MyMessage decode(String str) throws DecodeException {
        return gson.fromJson(str, MyMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
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
