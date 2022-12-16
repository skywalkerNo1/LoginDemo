package demo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.bean.RespBean;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截登录请求, 验证输入的验证码是否正确
 * @author 2022/12/15 17:46
 **/
@Component
public class VerificationCodeFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 判断是否是登录请求, 是的话则拦截请求
        if ("POST".equals(request.getMethod()) && "/doLogin".equals(request.getServletPath())) {
            filterChain.doFilter(request, response);
            // 获取用户输入的验证码
            String code = request.getParameter("code");
            // 获取session中保存的验证码
            String verifyCode = String.valueOf(request.getSession().getAttribute("veriftCode"));
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            try {
                // 验证session中保存是否存在, 相同
                if (!code.toLowerCase().equals(verifyCode.toLowerCase())) {
                    writer.write(new ObjectMapper().writeValueAsString(RespBean.error("验证码错误!")));
                } else {
                    filterChain.doFilter(request, response);
                }

            } catch (NullPointerException e) {
                writer.write(new ObjectMapper().writeValueAsString(RespBean.error("请求异常, 请重试!")));
            } finally {
                writer.flush();
                writer.close();
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
