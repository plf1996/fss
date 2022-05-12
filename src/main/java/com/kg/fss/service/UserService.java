package com.kg.fss.service;

import com.kg.fss.dao.UserMapper;
import com.kg.fss.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int login(String username,String pwd){
        User user = userMapper.selectByPrimaryKey(1);
        if(user.getUserName().equals(username)&&user.getPwd().equals(pwd)){
            return 1;
        }
        return 0;
    }
}
