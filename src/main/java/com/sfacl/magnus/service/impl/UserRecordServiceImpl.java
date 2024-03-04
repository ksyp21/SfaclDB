package com.sfacl.magnus.service.impl;

import com.sfacl.magnus.entity.User;
import com.sfacl.magnus.entity.UserRecord;
import com.sfacl.magnus.repository.UserRecordRepository;
import com.sfacl.magnus.service.UserRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.sfacl.magnus.entity.ActivityType.LOGIN;
import static com.sfacl.magnus.entity.ActivityType.LOGOUT;

@Service
@AllArgsConstructor
public class UserRecordServiceImpl implements UserRecordService {

    private UserRecordRepository userRecordRepository;

    @Override
    public void logUserLogin(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.setUser(user);
        userRecord.setLoginTime(LocalDateTime.now());
        userRecord.setActivityType(LOGIN);
        userRecordRepository.save(userRecord);
    }

    @Override
    public void logUserLogout(User user) {
        UserRecord userRecord = new UserRecord();
        userRecord.setUser(user);
        userRecord.setLogoutTime(LocalDateTime.now());
        userRecord.setActivityType(LOGOUT);
        userRecordRepository.save(userRecord);

    }
    @Override
    public void save(UserRecord userRecord) {
        userRecordRepository.save(userRecord);
    }

}
