package com.api.auth.api.authenticator.records;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
