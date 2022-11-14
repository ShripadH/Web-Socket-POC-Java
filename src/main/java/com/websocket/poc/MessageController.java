package com.websocket.poc;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.websocket.poc.dto.Message;
import com.websocket.poc.dto.ResponseMessage;

@Controller
public class MessageController {
	
	
	 @MessageMapping("/message")
	 @SendTo("/topic/messages")   //who ever is subscribed to this topic will get message
	public ResponseMessage getMessage(Message msg) throws InterruptedException {
		 System.out.println("in Message Controller");
		 System.out.println(msg.toString());
		return new ResponseMessage(HtmlUtils.htmlEscape(msg.getPayload()));
	}
	 
	 @MessageMapping("/private-message")
	 @SendToUser("/topic/private-messages")   //who ever is subscribed to this topic will get message
	public ResponseMessage getPrivateMessage(Message msg, Principal principal) throws InterruptedException {
		 System.out.println("in Message Controller");
		 System.out.println(msg.toString());
		return new ResponseMessage(HtmlUtils.htmlEscape("Sending private message "+ principal.getName() + ": " + msg.getPayload()));
	}

}
