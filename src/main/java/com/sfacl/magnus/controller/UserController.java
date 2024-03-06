package com.sfacl.magnus.controller;

import com.sfacl.magnus.entity.RequestPasswordChange;
import com.sfacl.magnus.entity.User;
import com.sfacl.magnus.service.PasswordExpirationService;
import com.sfacl.magnus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private PasswordExpirationService passwordExpirationService;

    @PutMapping("/users/{userId}/password")
    public ResponseEntity<?> changePassword(
            @AuthenticationPrincipal User userPrincipal,
            @PathVariable("userId") Integer userId,
            @RequestBody RequestPasswordChange requestPasswordChange
            ) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            userService.changePassword(user, requestPasswordChange.getOldPassword(), requestPasswordChange.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
