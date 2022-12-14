package com.example.dsproject.websocket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  private final SimpMessagingTemplate template;


  public WebSocketController(SimpMessagingTemplate template) {
    this.template = template;
  }

  @MessageMapping("/send/message")
  public void onReceivedMessage(String message){
    this.template.convertAndSend("/chat", message);
  }
}
