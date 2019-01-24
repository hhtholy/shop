package com.mini.foreController;

import com.mini.entity.User;
import com.mini.service.UserService;
import com.mini.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

/**
 * @author hht
 * @create 2019-01-21 10:53
 */
@RestController
public class ForeRESTcontroller {

    //注入UserService
    @Autowired
    private UserService userService;

    @PostMapping("/foreregister")
      public Object register(@RequestBody User user){

        //获取 用户注册输入的用户名
        String username = user.getName();
        //获取用户输入的密码
        String password = user.getPassword();

        //用户的名字进行一下转义

        /**
         *
         *String s = HtmlUtils.htmlEscape("<div>hello world</div><p>&nbsp;</p>");
         *System.out.println(s);
         *String s2 = HtmlUtils.htmlUnescape(s);
         *System.out.println(s2);
         * //转义后
         * &lt;div&gt;hello world&lt;/div&gt;&lt;p&gt;&amp;nbsp;&lt;/p&gt;
         * //转换回去
         * <div>hello world</div><p>&nbsp;</p>
         */
        //主要是名字 需要在前端显示  否则的话  不需要这样做
        // 显示的话 可能会出现 恶意 写 比如 alert('123')的情况 那这样的话 界面可能就会出问题
        HtmlUtils.htmlEscape(username);

        //检测 用户是不是已经注册过
        if(userService.isExist(user)){
            return Result.fail("用户名已经被使用，请使用其他的用户名");
        }

        //正常注册的话
        userService.addUser(user);

        return Result.success();
      }

    /**
     * 用户登录
     * @param user
     * @return
     */
      @PostMapping("/forelogin")
      public Object login(@RequestBody User user, HttpSession session){

          //对用户输入的名字进行下转义
          String name = user.getName();
          user.setName(HtmlUtils.htmlEscape(name));
          User result = userService.login(user);

          if(result == null){
              return Result.fail("账号或者密码错误");
          }
          //把用户数据放在session中
          session.setAttribute("user",result);
          return Result.success();
      }


}
