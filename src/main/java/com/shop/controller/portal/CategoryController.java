package com.shop.controller.portal;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.pojo.Category;
import com.shop.pojo.User;
import com.shop.service.ICategoryService;
import com.shop.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.annotation.Resource;

@RestController
public class CategoryController {
    @Resource
    private IUserService iUserService;

    @Resource
    private ICategoryService iCategoryService;

    @RequestMapping(value = "add_category", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse addCategory(Category category, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        if (!iUserService.checkIsAdmin(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户无权限");
        }

        category.setStatus(true);
        if(category.getParentId() == null){
            category.setParentId(0);
            return iCategoryService.addCategory(category);
        }

        return iCategoryService.addCategory(category);
    }

    @RequestMapping(value = "update_category", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse updateCategory(Category category, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        if (!iUserService.checkIsAdmin(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户无权限");
        }

        return iCategoryService.updateCategory(category);
    }

    @RequestMapping(value = "get_category_list", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse getCategoryList(Integer categoryId, HttpSession session) {
       // User user = (User) session.getAttribute(Const.CURRENT_USER);
       // if (user == null) {
       //     return ServerResponse.createByErrorMessage("用户未登录");
       // }

        //if (!iUserService.checkIsAdmin(user).isSuccess()) {
        //   return ServerResponse.createByErrorMessage("用户无权限");
        //}

        return iCategoryService.getChildCategoryList(categoryId);
    }

    @RequestMapping(value = "delete_category", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse deleteCategory(Integer id, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        if (!iUserService.checkIsAdmin(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户无权限");
        }
        return iCategoryService.deleteCategory(id);
    }



}
