package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.Category;
import java.util.List;

public interface ICategoryService {
    ServerResponse addCategory (String categoryName,Integer parentId);

    ServerResponse updateCategory (String categoryName,Integer categoryId);

    ServerResponse<List<Category>> getChildCategoryList(Integer categoryId);

    ServerResponse<String> deleteCategory(Integer id);

}
