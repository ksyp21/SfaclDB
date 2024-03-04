package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.User;
import com.sfacl.magnus.entity.UserRecord;

public interface UserRecordService {
    void logUserLogin(User user);

    void logUserLogout(User user);

    void save(UserRecord userRecord);

}
