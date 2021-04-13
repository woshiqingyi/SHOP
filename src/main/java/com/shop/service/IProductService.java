package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.Product;

import java.util.List;

public interface IProductService {
    ServerResponse<String> addProduct(Product product);

    ServerResponse<String> deleteProduct(Integer id);

    ServerResponse<String> updateProduct(Product product);

    ServerResponse<List<Product>> getProductList(Integer categoryId);

    ServerResponse<Product> getProductDetails(Integer id);
}
