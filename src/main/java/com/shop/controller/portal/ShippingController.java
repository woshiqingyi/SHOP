package com.shop.controller.portal;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.pojo.Shipping;
import com.shop.pojo.User;
import com.shop.service.IShippingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class ShippingController {
    @Resource
    private IShippingService iShippingService;

    @RequestMapping(value = "get_shipping_list", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<List<Shipping>> getShippingList(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        ServerResponse<List<Shipping>> response  = iShippingService.getShippingList(user.getId());
        return response;
    }

    @RequestMapping(value = "add_shipping_list", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<String> addShippingList(Shipping shipping,HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        shipping.setUserId(user.getId());
        return iShippingService.addShippingList(shipping);
    }

    @RequestMapping(value = "update_shipping_list", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<String> updateShippingList(Shipping shipping,HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        shipping.setUserId(user.getId());
        return iShippingService.updateShippingList(shipping);
    }

    @RequestMapping(value = "delete_shipping_list", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<String> deleteShippingList(Integer id,HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iShippingService.deleteShippingList(id);
    }










}
