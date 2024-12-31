package com.isis.email_service.controllers;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isis.email_service.application.EmailSenderService;
import com.isis.email_service.core.EmailRequest;
import com.isis.email_service.core.exceptions.EmailServiceException;
import com.isis.email_service.infra.MailtrapEmailSender;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailService) {
        this.emailSenderService = emailService;
    }

    @PostMapping("/simple")
    public ResponseEntity<String> sendSimpleEmail(@RequestBody EmailRequest request) {
        try {
            ((MailtrapEmailSender) emailSenderService.getEmailSenderGetway()).sendSimpleEmail(request);
            return ResponseEntity.ok("Email simples enviado com sucesso");
        } catch (EmailServiceException ex) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Erro ao enviar email simples");
        }
    }
}
