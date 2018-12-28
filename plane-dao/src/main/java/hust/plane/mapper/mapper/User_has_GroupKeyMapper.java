package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.User_has_GroupKey;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface User_has_GroupKeyMapper {

    List<User_has_GroupKey> getAllGroupByUserId(int UserId);

    List<Integer> getGroupIdByUserId(int id);

    int deleteGroupByUserId(Integer id);

    int insertGroupByUserIdWithAuthority(Integer id, Integer groupId);

    List<Integer> getUserIdByGroupId(Integer groupId, TailPage<UserPojo> page);

    List<Integer> getAllGroup();

    int selectCountWithGroupId(@Param(value = "Group_id") Integer groupId);
}
