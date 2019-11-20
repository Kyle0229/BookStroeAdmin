package com.kyle.controller;

import com.kyle.domain.User;
import com.kyle.mapper.UsersRepository;

import com.kyle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UsersService usersService;
    @Resource
    private UsersRepository usersRepository;

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(@RequestBody User user){
        Integer uid = user.getUid();
        usersRepository.deleteById(uid);
        return "删除成功";
    }

    @RequestMapping("/findUserAll")
    public List<User> findUserAll(){
        List<User> userAll = usersService.findUserAll();
        return userAll;
    }
}
