package com.websocket.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.websocket.poc.dto.ResponseMessage;

@Service
public class WsService {

	
	private SimpMessagingTemplate messagingTemplae;

	@Autowired
	public WsService(SimpMessagingTemplate messagingTemplae) {
		super();
		this.messagingTemplae = messagingTemplae;
	}
	
	public void NotifyFrontEnd(String message) {
		 System.out.println("In Service");
		ResponseMessage response = new ResponseMessage(message);
		
		messagingTemplae.convertAndSend("/topic/messages",response);
		
	}
	
	public void Notifyuser(String id, String message) {
		 System.out.println("In Service");
		ResponseMessage response = new ResponseMessage(message);
		
		messagingTemplae.convertAndSendToUser(id,"/topic/private-messages",response);
		
	}
	
	
}
