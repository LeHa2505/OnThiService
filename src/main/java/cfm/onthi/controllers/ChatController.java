package cfm.onthi.controllers;

import cfm.onthi.dtos.MessageDTO;
import cfm.onthi.exceptions.GlobalExceptionHandlerController;
import cfm.onthi.repositories.MessageRepository;
import cfm.onthi.services.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneOffset;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/service-onthi/api")
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @SendTo("/start/initial")
    @MessageMapping("/resume")
    public ResponseEntity<?> chat(@Payload MessageDTO messageDTO) {
        messagingTemplate.convertAndSend("/start/initial", messageDTO);

        return ResponseEntity.status(HttpStatus.OK).body((chatService.saveMessage(messageDTO)));
    }

    @GetMapping("/getMessages")
    public ResponseEntity<?> getMessages(@RequestParam(name = "idGroup", required = false) Long idGroup) {
        return ResponseEntity.status(HttpStatus.OK).body((chatService.getMessagesByGroupId(idGroup)));
    }

    @GetMapping("/groups/{idUser}")
    public ResponseEntity<?> getGroupsByUserId(@PathVariable("idUser") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body((chatService.getGroupsByUserId(userId)));
    }
}
