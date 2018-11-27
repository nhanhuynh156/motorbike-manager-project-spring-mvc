package com.nhan.service.user;

import com.nhan.dao.UsersRepository;
import com.nhan.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users getUserByUsername(String username) {
        return usersRepository.findOne(username);
    }
}
