package thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import thymeleaf.compoent.MyLocaleResolver;
import thymeleaf.interceptor.LoginInterceptor;


@Configuration
public class SpringMvcConfig {
    //国际化区域信息解析
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //方法、1
//    @Bean
//    public MvcConfig mvcConfig(){
//        return new MvcConfig();
//    }
    //方法、2
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return  new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //首页 方式二（推荐）
                //@RequestMapping({"/","index.html"})
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
        public void addInterceptors(InterceptorRegistry registry) {
/*
静态资源、*.css *.js，
是因为spring boot 2.x依赖的spring 5.x版本，相对于spring boot 1.5.x依赖的spring 4.3.x版本而言，
针对资源的拦截器初始化时有区别，spring boot 2.0,每个静态资源的请求都会被自定义Interceptor拦截(不是自定义的拦截器均不会拦截静态资源)，
spring boot 1.5.x,对每个静态资源的请求均不会拦截（是自定义的拦截器均不会拦截静态资源）
*/
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login")
                        .excludePathPatterns("/asserts/**","/webjars/**");//springboot2.0要排除静态资源
            }
        };
    }
}
