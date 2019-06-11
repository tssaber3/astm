package first.demo.mapper;

import first.demo.pojo.Role;
import first.demo.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User selUserByUsername(String username);

    @Select("select * from user where username=#{username} and password=#{password}")
    User selUserByUsernameAndPassword(String username,String password);

    @Select("select * from role " +
            "where id IN" +
            "(select role_id from user " +
            "where id=#{id})")
    Role selRoleByUserId(int id);

    @Select("select * from user " +
            "where id=#{id}")
    User selUserById(int id);

    //删除学会
    @Delete("delete from user where id=#{id}")
    void delUserById(int id);

    //增加用户
    @Insert("insert into user " +
            "values(default,#{deparment},#{grade},#{nickname},#{password},#{score},#{type},#{username})")
    User insUser(String deparment,int grade,String nickname,String password,int score,String type,String username);

    //更新用户信息
    @Update("update user " +
            "set username=#{username},nickname=#{nickname},deparment=#{deparment},score=#{score},grade=#{grade} " +
            "where id=#{id}")
    void updUser(String username,String nickname,String deparment,int score,int grade,int id);

    @Update("update user " +
            "set password=#{password} " +
            "where id=#{id}")
    void updUserPassword(String password);
}
