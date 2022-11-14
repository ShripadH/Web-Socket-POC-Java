package com.websocket.poc;

import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import com.sun.security.auth.UserPrincipal;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final String randomId = UUID.randomUUID().toString();
        System.out.println(request.toString());
        System.out.println("User with ID "+randomId+" opened the page");

        return new UserPrincipal(randomId);
    }
}