package demo.config.security;

import demo.service.MyAdminDetailsService;
import demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author 2022/12/15 17:37
 **/
@EnableWebSecurity
public class MyltiHttpSecurityConfig {

    //密码加密方案
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        MyAdminDetailsService adminDetailsService;
        @Autowired
        VerificationCodeFilter verificationCodeFilter;
        @Autowired
        MyAuthenticationHandler.MyAuthenticationFailureHandler failureHandler;
        @Autowired
        MyAuthenticationHandler.MyAuthenticationSuccessHandler successHandler;
        @Autowired
        MyAuthenticationHandler.MyLogoutSuccessHandler logoutSuccessHandler;

        //用户名和密码验证服务
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminDetailsService);
        }

        //忽略"/login","/verifyCode"请求，该请求不需要进入Security的拦截器
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/favicon.ico", "/index.html", "/admin/login", "/admin/mailVerifyCode");
        }

        // http请求验证和处理规则, 响应处理的配置
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 将验证码过滤器添加在用户名密码过滤器前面, 采用前置通知
            http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/admin/login")  //自定义登录页面设置
                    .loginProcessingUrl("/admin/doLogin") // 点击登录后要访问的路径
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .permitAll()  //返回值
                    .and()
                    .logout() // 登出处理
                    .logoutUrl("/admin/logout")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .permitAll()
                    .and()
                    .csrf().disable()  // 关闭csrf防御方便调试
                    // 没有认证是, 在这里处理结果, 不进行重定向到login页面
                    .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) -> response.setStatus(401));
        }
    }

    @Configuration
    @Order(2)
    public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        MyUserDetailsService myUserDetailsService;
        @Autowired
        VerificationCodeFilter verificationCodeFilter;
        @Autowired
        MyAuthenticationHandler.MyAuthenticationFailureHandler failureHandler;
        @Autowired
        MyAuthenticationHandler.MyAuthenticationSuccessHandler successHandler;
        @Autowired
        MyAuthenticationHandler.MyLogoutSuccessHandler logoutSuccessHandler;

        //用户名和密码验证服务
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(myUserDetailsService);
        }

        //忽略"/login","/verifyCode"请求，该请求不需要进入Security的拦截器
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/login", "/verifyCode", "/file", "/user/register", "/user/checkUsername", "/user/checkNickname");
        }

        // http请求验证和处理规则, 响应处理的配置
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 将验证码过滤器添加在用户名密码过滤器前面, aop前置通知方式
            http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/login")  //自定义登录页面设置
                    .loginProcessingUrl("/doLogin") // 点击登录后要访问的路径
                    .successHandler(successHandler) // 成功处理
                    .failureHandler(failureHandler) // 失败处理
                    .permitAll()  //返回值
                    .and()
                    .logout() // 登出处理
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(logoutSuccessHandler) // 登出处理
                    .permitAll()
                    .and()
                    .csrf().disable()  // 关闭csrf防御方便调试
                    // 没有认证是, 在这里处理结果, 不进行重定向到login页面
                    .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) -> response.setStatus(401));
        }
    }
}
