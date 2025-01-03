package com.isis.email_service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isis.email_service.adapters.EmailSenderGetway;
import com.isis.email_service.core.EmailSenderUseCase;
import com.isis.email_service.infra.MailtrapEmailSender;

@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGetway emailSenderGetway;

    @Autowired
    public EmailSenderService(EmailSenderGetway emailGetway) {
        this.emailSenderGetway = emailGetway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGetway.sendEmail(to, subject, body);
    }

    public EmailSenderGetway getEmailSenderGetway() {
        return this.emailSenderGetway;
    }
}
