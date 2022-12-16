package demo.service.impl;

import demo.dao.AdminMapper;
import demo.model.Admin;
import demo.service.MyAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

/**
 * @author 2022/12/16 9:26
 **/
@Service
public class MyAdminDetailsServiceImpl implements MyAdminDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 根据用户名进行登录验证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.loadAdminByUsername(username);
        if (Objects.isNull(admin)) {
            throw new UsernameNotFoundException("账号不存在!");
        }
        return admin;
    }
}
