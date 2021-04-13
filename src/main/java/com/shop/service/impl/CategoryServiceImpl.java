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

    @Override
    public ServerResponse addCategory(Category category) {
        int Count = categoryMapper.insert(category);
        if (Count > 0) {
            return ServerResponse.createBySuccessMessage("新增类目成功");
        }
        return ServerResponse.createByErrorMessage("新增类目失败");
    }

    @Override
    public ServerResponse updateCategory(Category category) {
        int Count = categoryMapper.updateByPrimaryKeySelective(category);
        if (Count > 0) {
            return ServerResponse.createBySuccessMessage("更新类目成功");
        }
        return ServerResponse.createByErrorMessage("更新类目失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildCategoryList(Integer categoryId) {
        List<Category> CategoryList = categoryMapper.getCategoryList(categoryId);
        if(CategoryList == null){
            return ServerResponse.createByErrorMessage("暂无数据");
        }
        return ServerResponse.createBySuccess(CategoryList);
    }

    @Override
    public ServerResponse<String> deleteCategory(Integer id) {
        int Count = categoryMapper.deleteByPrimaryKey(id);
        if(Count == 0){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }




}
