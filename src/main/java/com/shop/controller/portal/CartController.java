package com.shop.controller.portal;


import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.pojo.Cart;
import com.shop.pojo.User;
import com.shop.service.ICartService;
import com.shop.vo.CartVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CartController {
    @Resource
    private ICartService iCartService;

    @RequestMapping(value = "add_cart", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<CartVo> addCart(Integer productId, Integer count, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        return iCartService.addCart(user.getId(),productId,count);
    }

    @RequestMapping(value = "get_cart_list", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<CartVo> getCartList(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iCartService.getCartList(user.getId());
    }

    @RequestMapping(value = "update_cart", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<CartVo> updateCart(Integer productId, Integer count, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(count < 1){
            return ServerResponse.createByErrorMessage("产品数量必须为大于等于1");
        }
        return iCartService.updateCart(user.getId(),productId,count);
    }

    @RequestMapping(value = "delete_cart", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<CartVo> deleteCart(Integer id,HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iCartService.deleteCart(user.getId(),id);
    }








}


