package com.sfacl.magnus.service;

import com.sfacl.magnus.entity.User;

public interface UserService {
     void changePassword(User user,String oldPassword,String newPassword);

    User findById(Integer userId);


}
