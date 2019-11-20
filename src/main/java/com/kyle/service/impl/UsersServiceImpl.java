package com.kyle.service.impl;

import com.kyle.domain.User;
import com.kyle.mapper.UsersRepository;
import com.kyle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> findUserAll() {
        List<User> all = usersRepository.findAll();
        return all;
    }

}
