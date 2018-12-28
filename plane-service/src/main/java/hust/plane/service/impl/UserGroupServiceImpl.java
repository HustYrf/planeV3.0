package hust.plane.service.impl;

import hust.plane.mapper.mapper.UserMapper;
import hust.plane.mapper.mapper.User_has_GroupKeyMapper;
import hust.plane.service.interFace.UserGroupService;
import hust.plane.utils.pojo.TipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserGroupServiceImpl implements UserGroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupServiceImpl.class);
    @Resource
    private User_has_GroupKeyMapper userHasGroupKeyMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Integer> selectGroupIdWithUserId(int id) {
        if (Integer.valueOf(id) == null) {
            LOGGER.error("传入的用户ID为空");
        }
        List<Integer> GroupIdList = null;
        try {
            GroupIdList = userHasGroupKeyMapper.getGroupIdByUserId(id);
            if (GroupIdList == null || GroupIdList.size() == 0) {
                LOGGER.error("无法获取该用户用户组ID");
            }
        } catch (Exception e) {
            LOGGER.error("获取用户的用户组ID失败", e);
        }
        return GroupIdList;
    }

    @Override
    public int updateAuthorityWithUserId(Integer id, List<String> authorityList) {
        if (id == null || authorityList == null || authorityList.size() == 0) {
            throw new TipException("用户组设定异常");
        }
        int taskNum = userMapper.selectByPrimaryKey(id).getTasknum();
        if (taskNum != 0 && !authorityList.contains("ipqc")) {
            throw new TipException("该用户有未完成巡视任务，无法取消巡视员身份！");
        }
        List<Integer> groupList = userHasGroupKeyMapper.getGroupIdByUserId(id.intValue());
        if (groupList.size() > 0) {
            int deleteCount = userHasGroupKeyMapper.deleteGroupByUserId(id);
            if (deleteCount == 0) {
                throw new TipException("用户组删除异常");
            }
        }
        for (int i = 0; i < authorityList.size(); i++) {
            Integer GroupId = null;
            if (authorityList.get(i).equals("viewer")) {
                GroupId = Integer.valueOf(1);
            } else if (authorityList.get(i).equals("admin")) {
                GroupId = Integer.valueOf(2);
            } else {
                GroupId = Integer.valueOf(3);
            }
            int insertCount = userHasGroupKeyMapper.insertGroupByUserIdWithAuthority(id, GroupId);
            if (insertCount == 0) {
                throw new TipException("用户组插入异常");
            }
        }
        return 0;
    }
}
