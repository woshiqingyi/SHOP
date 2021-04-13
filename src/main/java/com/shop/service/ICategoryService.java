package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.Category;
import java.util.List;

public interface ICategoryService {
   // ServerResponse addCategory (String categoryName,Integer categoryId);

   // ServerResponse updateCategory (String categoryName,Integer categoryId);

    ServerResponse addCategory (Category category);

    ServerResponse updateCategory (Category category);

    ServerResponse<List<Category>> getChildCategoryList(Integer categoryId);

    ServerResponse<String> deleteCategory(Integer id);

}
