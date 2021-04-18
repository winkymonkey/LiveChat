package com.example.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.example.model.MyMessage;


/**
 * If decorated with @ServerEndpoint, 
 * the container ensures availability of the class as a WebSocket server listening to a specific URI space
 */
@ServerEndpoint(value="/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class )
public class ChatEndpoint {

	private Session session;
	private static Set<ChatEndpoint> endpointsSet = new CopyOnWriteArraySet<>();
	private static HashMap<String, String> usersMap = new HashMap<>();
	
	
	/**
	 * It is invoked by the container when a new WebSocket connection is initiated
	 * @param session
	 * @param username
	 * @throws IOException
	 * @throws EncodeException
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
		this.session = session;
		endpointsSet.add(this);
		usersMap.put(session.getId(), username);

		MyMessage message = new MyMessage();
		message.setFrom(username);
		message.setContent("Connected!");
		broadcast(message);
	}
	
	
	/**
	 * It receives the information from the WebSocket container when a message is sent to the endpoint
	 * @param session
	 * @param msg
	 * @throws IOException
	 * @throws EncodeException
	 */
	@OnMessage
	public void onMessage(Session session, MyMessage msg) throws IOException, EncodeException {
		msg.setFrom(usersMap.get(session.getId()));
		broadcast(msg);
	}
	
	
	/**
	 * It is invoked by the container when the WebSocket connection closes
	 * @param session
	 * @throws IOException
	 * @throws EncodeException
	 */
	@OnClose
	public void onClose(Session session) throws IOException, EncodeException {
		endpointsSet.remove(this);
		MyMessage message = new MyMessage();
		message.setFrom(usersMap.get(session.getId()));
		message.setContent("Disconnected!");
		broadcast(message);
	}
	
	
	/**
	 * It is invoked when there is a problem with the communication
	 * @param session
	 * @param throwable
	 */
	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
	}
	
	
	private static void broadcast(MyMessage msg) throws IOException, EncodeException {
		endpointsSet.forEach(endpoint -> {
			synchronized (endpoint) {
				try {
					endpoint.session.getBasicRemote().sendObject(msg);
				}
				catch (IOException | EncodeException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
