package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.Cart;
import com.shop.vo.CartVo;
import java.util.List;

public interface ICartService {
    ServerResponse <CartVo> addCart(Integer userId, Integer productId, Integer count);

    ServerResponse <CartVo> getCartList(Integer userId);

    ServerResponse <CartVo> updateCart(Integer userId,Integer productId,Integer count);

    ServerResponse <CartVo> deleteCart(Integer userId,Integer id);
}
