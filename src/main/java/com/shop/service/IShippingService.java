package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.Shipping;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IShippingService {
    ServerResponse<List<Shipping>> getShippingList(Integer userId);

    ServerResponse<String> addShippingList(Shipping shipping);

    ServerResponse<String> updateShippingList(Shipping shipping);

    ServerResponse<String> deleteShippingList(Integer id);
}
