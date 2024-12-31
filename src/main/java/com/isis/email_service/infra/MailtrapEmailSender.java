package com.isis.email_service.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.isis.email_service.adapters.EmailSenderGetway;
import com.isis.email_service.core.EmailRequest;
import com.isis.email_service.core.exceptions.EmailServiceException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailtrapEmailSender implements EmailSenderGetway {

    private final JavaMailSender mailSender;

    @Autowired
    public MailtrapEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // true indica que o corpo do email pode conter HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailServiceException("Falha ao enviar email", e);
        }
    }

    public void sendSimpleEmail(EmailRequest email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplay@email.com");
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());
        mailSender.send(message);
    }
}