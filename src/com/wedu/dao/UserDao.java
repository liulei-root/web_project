package com.wedu.dao;

import com.wedu.domain.User;
import com.wedu.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDao  {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template =  new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据,没有查询到，返回null
     */
    public User loginUser(User loginUser){
        try {
            //sql
            String sql = "select * from loginuser where name = ? and password = ? ";
            //调用query方法
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getName(), loginUser.getPassword());
            return user;
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
