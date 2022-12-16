package demo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.bean.RespBean;
import demo.model.Admin;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 2022/12/15 19:02
 **/
public class MyAuthenticationHandler {

    @Configuration
    public static class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            RespBean respBean = RespBean.error("登录失败!");
            if (exception instanceof LockedException) {
                respBean.setMsg("账户已锁定，请联系管理员！");
            } else if (exception instanceof CredentialsExpiredException) {
                respBean.setMsg("密码已过期，请联系管理员！");
            } else if (exception instanceof AccountExpiredException) {
                respBean.setMsg("账户已过期，请联系管理员！");
            } else if (exception instanceof DisabledException) {
                respBean.setMsg("账户被禁用，请联系管理员!");
            } else if (exception instanceof BadCredentialsException) {
                respBean.setMsg("用户名或密码错误，请重新输入！");
            }
            String str = new ObjectMapper().writeValueAsString(respBean);
            writer.write(str);
            writer.flush();
            writer.close();
        }
    }

    @Configuration
    public static class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            Admin admin = (Admin) authentication.getPrincipal();
            admin.setPassword(null);
            RespBean respBean = RespBean.ok("登录成功", admin);
            String str = new ObjectMapper().writeValueAsString(respBean);
            writer.write(str);
            writer.flush();
            writer.close();
        }
    }

    @Configuration
    public static class MyLogoutSuccessHandler implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(RespBean.ok("退出成功!")));
            writer.flush();
            writer.close();
        }
    }
}
