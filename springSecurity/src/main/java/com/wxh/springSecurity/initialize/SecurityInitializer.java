package com.wxh.springSecurity.initialize;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;

/**
 * @Auther: WXH
 * @Date: 2022/9/7 - 09 - 07 - 15:27
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        servletContext.addFilter("characterEncodingFilter",new CharacterEncodingFilter("UTF-8",true))
                .addMappingForUrlPatterns(null,false,"/*");

    }
}
