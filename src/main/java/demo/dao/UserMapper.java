package demo.dao;

import demo.config.db.BaseMapper;
import demo.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User loadUserByUsername(@Param("username") String username);
}