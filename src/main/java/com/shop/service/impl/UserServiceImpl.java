package com.shop.service.impl;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.dao.UserMapper;
import com.shop.pojo.Shipping;
import com.shop.pojo.User;
import com.shop.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int Count = userMapper.checkUsername(username);
        if (Count == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        User response = userMapper.selectLogin(username, password);
        if (response == null) {
            return ServerResponse.createByErrorMessage("登录密码错误");
        }
        return ServerResponse.createBySuccess("登录成功", response);
    }

    @Override
    public ServerResponse<String> register(User user) {
        int Count = userMapper.checkUsername(user.getUsername());
        if (Count != 0) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        int RegisterCount = userMapper.insert(user);
        if (RegisterCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse checkIsAdmin(User user) {
        if (user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
             return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
