package thymeleaf.compoent;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //首页 方式二（推荐）
       // @RequestMapping({"/","index.html"})
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        //首页登陆成功后，重定向：/main.html，这里做资源映射
        registry.addViewController("/main.html").setViewName("dashboard");
    }
}
