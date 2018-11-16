package thymeleaf.compoent;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/*
* 国际化区域信息：可以在连接上携带区域信息
*/
public class MyLocaleResolver  implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("L");
        System.out.println(l+"123");
        Locale locale = Locale.getDefault();//如果没有传递参数过来，则传递系统默认的国际化区域信息（en_US/zh_CN）
        if(!StringUtils.isEmpty(l)){
            String[] s = l.split("_");
            locale = new Locale(s[0],s[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
