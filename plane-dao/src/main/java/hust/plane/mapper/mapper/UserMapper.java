package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.User;
import hust.plane.mapper.pojo.UserExample;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int countByWorkNumber(String worknumber);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //Id自增
    int insertSelectiveWithIdInc(User record);

    //根据该用户名查出用户的数量
    int selectByUserName(String name);

//    int selectByUserNameAndRole(@Param("username") String username, @Param("role") String role);

    int selectUserCount();

    List<User> selectAllUser(@Param("page") TailPage<UserPojo> page);

//    int selectCountWithRole(@Param("Role") String searchUserStatus);

//    List<User> selectUserByRole(@Param("page") TailPage<User> page, @Param("role") String searchUserStatus);

    int userAddTasknum(User user);

    int userReduceTasknum(User user);

    int updateLastTime(String name);

    int insertSelectiveIdInc(User user);

    String getNameByUserId(Integer id);

    User selectUserByUserName(String userName);

    int selectCountByFuzzyName(@Param("Name") String userName);

    List<User> selectByFuzzyNameWithPage(@Param("Name") String userName, TailPage<UserPojo> page);

    User getUserByName(String name);

    Integer selectUserIdByUserName(String addUsername);

    Integer selectByUserWorkNumber(String addUserWorkNumber);
}