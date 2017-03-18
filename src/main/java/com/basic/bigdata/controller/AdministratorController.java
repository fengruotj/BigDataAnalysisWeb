package com.basic.bigdata.controller;

import com.basic.bigdata.model.Administrator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell-pc on 2016/4/22.
 */

@Controller
@RequestMapping("/admin")
public class AdministratorController extends BaseController{

    /**
     * 后台登录的控制器
     * @param username 用户名
     * @param password 密码
     * @param modelAndView 模型控制器
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password,
                              HttpSession httpSession, ModelAndView modelAndView){
        modelAndView.setViewName("manage/login");
        if(!username.equals("")&!password.equals("")) {
            Administrator admin = tAdministratorService.findadminByusernameAndPass(username, password);
            if(admin!=null) {
                httpSession.setAttribute("admin",admin);
                modelAndView.setViewName("redirect:/manage__aindex");
            }
            else {
                modelAndView.addObject("error","用户名或者密码错误");
            }
        }
        return modelAndView;
    }

    /**
     *  用户退出登录并且从session对象中删除admin
     * @param modelAndView
     * @param httpSession
     * @return
     */
    @RequestMapping("/loginOut")
    public ModelAndView loginOut(ModelAndView modelAndView, HttpSession httpSession){
        httpSession.setAttribute("admin",null);
        modelAndView.setViewName("manage/login");
        return modelAndView;
    }

    @RequestMapping(value = "/administrator/queryUserByPage",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryUserByPage(Administrator administrator, @RequestParam Integer page,
                                  @RequestParam Integer rows){
        return tAdministratorService.queryTuserByPage(administrator.getName(),page,rows);
    }

    /**
     *  添加管理员用户
     * @param administrator
     * @return
     */
    @RequestMapping(value = "/administrator/save",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveAdministrator(Administrator administrator){
        Map map=new HashMap();
        try {
            tAdministratorService.save(administrator);
            map.put("success",true);
        }catch (Exception e){
            map.put("errorMsg",e.getMessage());
            e.printStackTrace();;
        }
        return gson.toJson(map);
    }

    /**
     *
     * @param administrator
     * @return
     */
    @RequestMapping(value = "/administrator/delete",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteAdministrator(Administrator administrator){
        Map map=new HashMap<>();
        try {
            tAdministratorService.delete(administrator.getId());
            map.put("success",true);
        }catch (Exception e){
            map.put("errorMsg",e.getMessage());
            e.printStackTrace();;
        }
        return gson.toJson(map);
    }

    /**
     *
     * @param administrator
     * @return
     */
    @RequestMapping(value = "/administrator/update",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateAdministrator(Administrator administrator){
        Map map=new HashMap<>();
        try {
            tAdministratorService.update(administrator);
            map.put("success",true);
        }catch (Exception e){
            map.put("errorMsg",e.getMessage());
            e.printStackTrace();;
        }
        return gson.toJson(map);
    }

}
