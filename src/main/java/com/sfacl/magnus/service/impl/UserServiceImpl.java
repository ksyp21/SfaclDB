package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.RequestPasswordChange;
import com.sfacl.magnus.entity.User;
import com.sfacl.magnus.repository.PasswordChangeRequestRepository;
import com.sfacl.magnus.repository.UserRepository;
import com.sfacl.magnus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;

    private PasswordChangeRequestRepository passwordChangeRequestRepository;

    private UserRepository userRepository;


    @Override
    public User findById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new IllegalArgumentException("Incorrect old password");
        }

        if(isPasswordInHistory(user,newPassword)){
            throw new IllegalArgumentException("Password cant be same as last 2 password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setLastPasswordChange(LocalDate.now());

        user.addPasswordToHistory(user.getPassword());

        userRepository.save(user);


    }
    private boolean isPasswordInHistory(User user, String newPassword) {
        List<String> lastPasswords = user.getPasswordHistory();
        return lastPasswords.contains(newPassword);
    }
}
