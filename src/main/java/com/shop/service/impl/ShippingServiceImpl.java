package com.shop.service.impl;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.dao.ShippingMapper;
import com.shop.pojo.Shipping;
import com.shop.service.IShippingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ShippingServiceImpl implements IShippingService {
    @Resource
    private ShippingMapper shippingMapper;

    @Override
    public ServerResponse<List<Shipping>> getShippingList(Integer userId){
        List<Shipping> ShippingList = (List<Shipping>) shippingMapper.getShippingList(userId);
        if(ShippingList == null){
            return ServerResponse.createByErrorMessage("暂无数据");
        }
        return ServerResponse.createBySuccess(ShippingList);
    }

    @Override
    public ServerResponse<String> addShippingList(Shipping shipping){
        int resultCount = shippingMapper.insert(shipping);
        if(resultCount == 0){
           return ServerResponse.createBySuccessMessage("新增失败");
        }
        return ServerResponse.createBySuccessMessage("新增成功");
    }

    @Override
    public ServerResponse<String> updateShippingList(Shipping shipping){
        int resultCount = shippingMapper.updateByPrimaryKeySelective(shipping);
        if(resultCount > 0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createBySuccessMessage("更新失败");
    }

    @Override
    public ServerResponse<String> deleteShippingList(Integer id){
        int resultCount = shippingMapper.deleteByPrimaryKey(id);
        if(resultCount == 0){
            return ServerResponse.createBySuccessMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }








}
