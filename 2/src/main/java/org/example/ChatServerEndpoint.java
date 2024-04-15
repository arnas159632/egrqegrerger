package org.example;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.IOException;

@WebSocket
public class ChatServerEndpoint {

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        System.out.println("Connected: " + user.getRemoteAddress().getAddress());
        user.getRemote().sendString("Hello Websocket");
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        System.out.println("Closed: " + user.getRemoteAddress().getAddress());
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        System.out.println("Message: " + message);
        try {
            user.getRemote().sendString(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
