package com.wxh.springSecurity.initialize;

import com.wxh.springSecurity.config.MvcConfiguration;
import com.wxh.springSecurity.config.RootConfiguration;
import com.wxh.springSecurity.config.SecurityConfiguration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @Auther: WXH
 * @Date: 2022/9/7 - 09 - 07 - 13:11
 */
public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfiguration.class, SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
