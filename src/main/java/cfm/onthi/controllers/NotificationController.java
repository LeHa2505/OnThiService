package cfm.onthi.controllers;

import cfm.onthi.dtos.NotificationDTO;
import cfm.onthi.exceptions.GlobalExceptionHandlerController;
import cfm.onthi.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/service-onthi/api")
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendNotification")
    public void sendNotification(NotificationDTO notificationDTO) {
        // Gửi thông báo đến tất cả các người dùng đã đăng ký
        messagingTemplate.convertAndSend("/send/notifications", notificationDTO);
    }

    @MessageMapping("/notification")
    public void getNotification(NotificationDTO notificationDTO) {
        // Gửi thông báo đến tất cả các người dùng đã đăng ký
        messagingTemplate.convertAndSend("/send/notifications", "okokokokokok");
    }

    @GetMapping("/notifications/{idUser}")
    public ResponseEntity<?> getNotification(@PathVariable("idUser") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getListNotification(userId));
    }

    @PostMapping("/admin/create/notification")
    public ResponseEntity<?> createNotification(@RequestBody NotificationDTO notificationDTO) {
        // Sau khi tạo thông báo, gửi thông báo qua WebSocket
        messagingTemplate.convertAndSend("/topic/notifications", notificationDTO);
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.createNotification(notificationDTO));
    }

    @PostMapping("/admin/update/notification")
    public ResponseEntity<?> updateNotification(@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.updateNotification(notificationDTO));
    }
}
