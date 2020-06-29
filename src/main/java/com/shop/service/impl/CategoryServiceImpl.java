package com.shop.service.impl;

import com.shop.common.ServerResponse;
import com.shop.dao.CategoryMapper;
import com.shop.pojo.Category;
import com.shop.pojo.Shipping;
import com.shop.service.ICategoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int Count = categoryMapper.insert(category);
        if (Count > 0) {
            return ServerResponse.createBySuccessMessage("新增类目成功");
        }
        return ServerResponse.createByErrorMessage("新增类目失败");
    }

    public ServerResponse updateCategory(String categoryName, Integer categoryId) {
        Category category = new Category();
        category.setName(categoryName);
        category.setId(categoryId);

        int Count = categoryMapper.updateByPrimaryKeySelective(category);
        if (Count > 0) {
            return ServerResponse.createBySuccessMessage("更新类目成功");
        }
        return ServerResponse.createByErrorMessage("更新类目失败");
    }

    public ServerResponse<List<Category>> getChildCategoryList(Integer categoryId) {
        List<Category> CategoryList = categoryMapper.getCategoryList(categoryId);
        if(CategoryList == null){
            return ServerResponse.createByErrorMessage("暂无数据");
        }
        return ServerResponse.createBySuccess(CategoryList);
    }

    public ServerResponse<String> deleteCategory(Integer id) {
        int Count = categoryMapper.deleteByPrimaryKey(id);
        if(Count == 0){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }




}
