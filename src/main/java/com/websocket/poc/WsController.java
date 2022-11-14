package com.websocket.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.poc.dto.Message;

@RestController
public class WsController {
	
	@Autowired 
	private WsService service;
	
	@PostMapping("/send-message")
	public void sendMessage(@RequestBody Message message){
		System.out.println("in REST Controller");
		service.NotifyFrontEnd(message.getPayload());		
	}
	
	@PostMapping("/send-private-message/{id}")
	public void sendPrivateMessage(@PathVariable String id, @RequestBody Message message){
		System.out.println("in REST Controller");
		service.Notifyuser(id, message.getPayload());
		
	}

}
