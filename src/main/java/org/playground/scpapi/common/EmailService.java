package org.playground.scpapi.common;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("foundation@demomailtrap.co");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
