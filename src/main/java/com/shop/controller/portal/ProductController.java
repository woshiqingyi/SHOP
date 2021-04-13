package com.shop.controller.portal;

import com.google.common.collect.Maps;
import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.pojo.Product;
import com.shop.pojo.User;
import com.shop.service.IFileService;
import com.shop.service.IProductService;
import com.shop.service.IUserService;
import com.shop.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.shop.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ProductController {
    @Resource
    private IProductService iProductService;

    @Resource
    private IUserService iUserService;

    @Resource
    private IFileService iFileService;

    @RequestMapping(value = "add_product", method = RequestMethod.POST)
    @ResponseBody

    public ServerResponse<String> addProductList(Product product, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if (!iUserService.checkIsAdmin(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户无权限");
        }
        return iProductService.addProduct(product);
    }

    @RequestMapping(value = "delete_product", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteProduct(Integer id, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if (!iUserService.checkIsAdmin(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户无权限");
        }
        return iProductService.deleteProduct(id);
    }

    @RequestMapping(value = "update_product", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateProduct(Product product, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if (!iUserService.checkIsAdmin(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户无权限");
        }
        return iProductService.updateProduct(product);
    }

    @RequestMapping(value = "get_product_list", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Product>> getProductList(Integer categoryId, HttpSession session) {
        // User user = (User) session.getAttribute(Const.CURRENT_USER);
        // if (user == null) {
        //     return ServerResponse.createByErrorMessage("用户未登录");
        // }
        //if (!iUserService.checkIsAdmin(user).isSuccess()) {
        //   return ServerResponse.createByErrorMessage("用户无权限");
        //}
        return iProductService.getProductList(categoryId);
    }

    @RequestMapping(value = "get_product_details", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Product> getProductDetail(Integer id, HttpSession session) {
        // User user = (User) session.getAttribute(Const.CURRENT_USER);
        // if (user == null) {
        //     return ServerResponse.createByErrorMessage("用户未登录");
        // }
        //if (!iUserService.checkIsAdmin(user).isSuccess()) {
        //   return ServerResponse.createByErrorMessage("用户无权限");
        //}
        return iProductService.getProductDetails(id);
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @RequestMapping("richtext_img_upload")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map resultMap = Maps.newHashMap();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,path);
            if(StringUtils.isBlank(targetFileName)){
                resultMap.put("success",false);
                resultMap.put("msg","上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
            resultMap.put("success",true);
            resultMap.put("msg","上传成功");
            resultMap.put("file_path",url);
            response.addHeader("Access-Control-Allow-Headers","X-File-Name");
            return resultMap;
    }
}
