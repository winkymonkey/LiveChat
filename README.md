## Hit the below URL to start a participant 
http://localhost:8080/LiveChat/


## Supported Message formats
The WebSocket specification supports below message formats:
- **Text:** Any textual data (java.lang.String, primitives or their equivalent wrapper classes)
- **Binary:** Binary data (e.g. audio, image etc.) represented by a java.nio.ByteBuffer or a byte[] (byte array)
- **Java objects:** The API makes it possible to work with native (Java object) representations in the code and use custom transformers (encoders/decoders) to convert them into compatible on-wire formats (text, binary) allowed by the WebSocket protocol
- **Ping-Pong:** A javax.websocket.PongMessage is an acknowledgment sent by a WebSocket peer in response to a health check (ping) request


**Encoder**

An encoder takes a Java object and produces a typical representation suitable for transmission as a message such as JSON, XML or binary representation. Encoders can be used by implementing the Encoder.Text<T> or Encoder.Binary<T> interfaces.


**Decoder**

A decoder is the opposite of an encoder and is used to transform data back into a Java object. Decoders can be implemented using the Decoder.Text<T> or Decoder.Binary<T> interfaces.

> **NOTE** We must set the Encoder and Decoder in Server Endpoint	
