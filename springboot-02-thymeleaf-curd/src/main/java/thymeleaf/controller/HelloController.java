package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HelloController {


    @RequestMapping("/success")
    public String hello(){
        return "success";
    }

//    //首页 方式一
//    @RequestMapping({"/","index.html"})
//    public String index(){
//        return "login";
//    }

    @PostMapping("/user/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam("password") String password,
                        Map<Object,Object> map, HttpSession session) {
//@RequestParam  强调一定要有这个参数 没有会报错,可以在后面添加一个参数@RequestParam(value = "username",required = false),
        //required = false表示不需要的参数，没有则不会报错
        if(!StringUtils.isEmpty(username)&& "123".equals(password)){
            //登陆成功,防止表单重新提交，使用重定向，其中main.html资源又被SpringMvc管理
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            //登陆失败
            map.put("msg","用户名或密码错误");
            return "login";
        }

    }
}
