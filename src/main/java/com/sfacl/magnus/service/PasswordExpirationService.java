package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.User;

public interface PasswordExpirationService {
    boolean isPasswordExpired(User user);
}
