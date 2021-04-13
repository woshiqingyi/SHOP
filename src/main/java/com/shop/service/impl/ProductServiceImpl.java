package com.shop.service.impl;

import com.shop.common.ServerResponse;
import com.shop.dao.ProductMapper;
import com.shop.pojo.Product;
import com.shop.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
     @Resource
     private ProductMapper productMapper;

     @Override
     public ServerResponse<String> addProduct(Product product){
         int Count = productMapper.insert(product);
         if(Count == 0){
             return ServerResponse.createByErrorMessage("新增失败");
         }
         return ServerResponse.createBySuccessMessage("新增成功");
     }

     @Override
     public ServerResponse<String> deleteProduct(Integer id){
         int Count = productMapper.deleteByPrimaryKey(id);
         if(Count == 0){
             return ServerResponse.createByErrorMessage("删除失败");
         }
         return ServerResponse.createBySuccessMessage("删除成功");
     }

    @Override
    public ServerResponse<String> updateProduct(Product product){
        int Count = productMapper.updateByPrimaryKeySelective(product);
        if(Count == 0){
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServerResponse<List<Product>> getProductList(Integer categoryId){
        List<Product> ProductList = productMapper.getProductList(categoryId);
        if(ProductList == null){
            return ServerResponse.createByErrorMessage("暂无数据");
        }
        return ServerResponse.createBySuccess(ProductList);
    }

    @Override
    public ServerResponse<Product> getProductDetails(Integer id){
        Product product = productMapper.selectByPrimaryKey(id);
        if(product == null){
            return ServerResponse.createByErrorMessage("暂无数据");
        }
        return ServerResponse.createBySuccess(product);
    }
}
