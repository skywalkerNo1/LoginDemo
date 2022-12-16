package demo.dao;

import demo.config.db.BaseMapper;
import demo.model.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends BaseMapper<Admin> {

    Admin loadAdminByUsername(@Param("username") String username);
}