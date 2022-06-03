package com.will.bookstoreapi.auth.email;

public interface EmailSender {
    void send(String to, String email);
}
