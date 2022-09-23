package com.wxh.springSecurity.config;

import com.wxh.springSecurity.entity.AuthUser;
import com.wxh.springSecurity.mapper.UserMapper;
import com.wxh.springSecurity.service.impl.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Auther: WXH
 * @Date: 2022/9/7 - 09 - 07 - 15:28
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @PostConstruct
    public void init() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
    @Resource
    UserAuthService service;
    @Resource
    PersistentTokenRepository repository;
    @Resource
    UserMapper mapper;

    @Bean
    public PersistentTokenRepository jdbcRepository(@Autowired DataSource dataSource){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();  //使用基于JDBC的实现
        repository.setDataSource(dataSource);   //配置数据源
        //repository.setCreateTableOnStartup(true);   //启动时自动创建用于存储Token的表（建议第一次启动之后删除该行）
        return repository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
                .antMatchers("/static/**","/page/auth/**","/api/auth/**").permitAll()//静态资源，使用permitAll来运行任何人访问（注意一定要放在前面）
                .antMatchers("/page/user/**","/api/user/**").hasRole("user")//所有请求必须登陆并且是user角色才可以访问（不包含上面的静态资源）
                .antMatchers("/page/admin/**","/api/admin/**").hasRole("admin")
                // .antMatchers("/index").hasAnyRole("user","admin")//基于角色的授权 表示user 和 admin都可以访问index这个页面
                //.anyRequest().hasRole("admin")//基于角色的授权 表示只有admin可以访问除index之外的页面
                .anyRequest().hasAnyRole("user","admin")
                .and()
                .formLogin()//配置Form表单登陆
                .loginPage("/page/auth/login")//登陆页面地址（GET）
                .loginProcessingUrl("/api/auth/doLogin")//form表单提交地址（POST）
                .successHandler(this::onAuthenticationSuccess)
                //.defaultSuccessUrl("/index",true)//登陆成功后跳转的页面，也可以通过Handler实现高度自定义,如不加true跳转可能会报404
                //.permitAll()//登陆页面也需要允许所有人访问
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")//退出登陆的请求地址
                .logoutSuccessUrl("/login")//退出后重定向的地址
                .and()
                .csrf().disable()
                .rememberMe()   //开启记住我功能
                .rememberMeParameter("remember")  //登陆请求表单中需要携带的参数，如果携带，那么本次登陆会被记住
                //.tokenRepository(new InMemoryTokenRepositoryImpl())  //这里使用的是直接在内存中保存的TokenRepository实现
                //TokenRepository有很多种实现，InMemoryTokenRepositoryImpl直接基于Map实现的，缺点就是占内存、服务器重启后记住我功能将失效
                //后面我们还会讲解如何使用数据库来持久化保存Token信息
                .tokenRepository(repository)
                .tokenValiditySeconds(60*60*24*14);//设置保留天数
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        AuthUser user = mapper.getUserPasswordByUsername(authentication.getName());
        session.setAttribute("user",user);
        if (user.getRole().equals("admin")){
            httpServletResponse.sendRedirect("/springSecurity/page/admin/index");//因为是重定向所以要加/springSecurity
        }else {
            httpServletResponse.sendRedirect("/springSecurity/page/user/index");//因为是重定向所以要加/springSecurity
        }

    }
}
