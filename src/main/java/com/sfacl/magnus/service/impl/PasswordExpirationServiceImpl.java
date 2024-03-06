package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.User;
import com.sfacl.magnus.service.PasswordExpirationService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PasswordExpirationServiceImpl implements PasswordExpirationService {

    @Override
    public boolean isPasswordExpired(User user) {
        LocalDate lastPasswordChange = user.getLastPasswordChange();
        LocalDate currentDate = LocalDate.now();
        long daysSinceLastChange = ChronoUnit.DAYS.between(lastPasswordChange, currentDate);
        return daysSinceLastChange > 90;
    }
}
