package com.isis.email_service.core;

public record EmailRequest(String to, String subject, String body) {

}
