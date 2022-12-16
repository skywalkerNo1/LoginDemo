package demo.service.impl;

import demo.dao.UserMapper;
import demo.model.User;
import demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author admin
 * 2022/7/13 16:14
 **/
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("账号不存在!");
        }
        return user;
    }
}
