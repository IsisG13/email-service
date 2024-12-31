package com.isis.email_service.adapters;

public interface EmailSenderGetway {
    void sendEmail(String to, String subject, String body);
}
