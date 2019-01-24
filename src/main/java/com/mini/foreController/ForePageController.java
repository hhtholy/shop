package com.mini.foreController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author hht
 * @create 2019-01-20 16:37
 *
 *
 * 前端界面的页面跳转
 */
@Controller
public class ForePageController {

    /**
     * 跳转到主界面
     * @return
     */
    @GetMapping("/")
      public String index(){
          return "redirect:home";
      }
    /**
     * 跳转主界面
     * @return
     */
    @GetMapping("/home")
      public String toHome(){
        return "fore/home";
    }

    /**
     * 跳转到 注册界面
     * @return
     */
    @GetMapping("/register")
    public String toregister(){
        return "fore/register";
    }

    /**
     * 注册成功的话 跳转到的界面
     * @return
     */
    @GetMapping("/registsuccess")
    public String registSuccess(){
        return "fore/registSuccess";
    }

    /**
     * 跳转到登录界面
     * @return
     */
    @GetMapping("/login")
    public String tologin(){
        return "fore/login";
    }


    /**
     * 注销
     * @return
     */
    @GetMapping("/foreloginout")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:home";
    }

}
