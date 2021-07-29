package cn.conststar.wall.mapper;

import cn.conststar.wall.pojo.PojoUserPublic;
import cn.conststar.wall.pojo.PojoUser;
import org.apache.ibatis.annotations.Param;

public interface MapperUser {
    //获取用户
    PojoUser getUser(@Param("email") String email, @Param("password") String password) throws Exception;

    //获取公开用户信息
    PojoUserPublic getUserPublic(@Param("id") int id) throws Exception;


    //更新上次登录时间
    int updateLastTime(PojoUser pojoUser) throws Exception;

    //验证用户
    boolean verifyUser(PojoUser pojoUser) throws Exception;

    //添加用户
    int addUser(@Param("email") String email, @Param("password") String password, @Param("name") String name) throws Exception;

    //检查用户是否存在
    boolean findUser(@Param("email") String email);
}
