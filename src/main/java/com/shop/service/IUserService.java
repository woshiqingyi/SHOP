package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.User;

import javax.xml.ws.Service;


public interface IUserService {
    ServerResponse<User> login(String username,String password);

    ServerResponse<String> register(User user);

}
