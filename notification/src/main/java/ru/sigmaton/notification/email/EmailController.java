package ru.sigmaton.notification.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/emails")
public class EmailController {
    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/text")
    public ResponseEntity<String> sendTextEmail(@RequestParam("email") String email,
                                                @RequestParam("name") String name) {
        System.out.printf("got email %s and name %s\n", email, name);
        emailService.sendTextEmail(email, name);
        return ResponseEntity.ok("Success");
    }
}
