package hust.plane.service.impl;

import hust.plane.constant.WebConst;
import hust.plane.mapper.mapper.GroupMapper;
import hust.plane.mapper.mapper.UserMapper;
import hust.plane.mapper.mapper.User_has_GroupKeyMapper;
import hust.plane.mapper.pojo.Group;
import hust.plane.mapper.pojo.User;
import hust.plane.mapper.pojo.UserExample;
import hust.plane.service.interFace.UserService;
import hust.plane.utils.DateKit;
import hust.plane.utils.PlaneUtils;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.UserPojo;
import hust.plane.utils.pojo.TipException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userDao;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private User_has_GroupKeyMapper user_has_groupKeyMapper;

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new TipException("用户名和密码不能为空");
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(username);
        int count = userDao.countByExample(example);
        if (count < 1) {
            throw new TipException("没有该用户");
        }
        String pwd = PlaneUtils.MD5encode(username + password);
        criteria.andPasswordEqualTo(pwd);
        // criteria.andRoleEqualTo("0");
        List<User> userList = userDao.selectByExample(example);
        if (userList.size() != 1) {
            throw new TipException("用户名密码错误或您没有操作权限");
        }
        return userList.get(0);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int register(String username, String password, String worknumber) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(worknumber)) {
            throw new TipException("用户名、密码和工号均不能为空");
        }
        int usernameCount = userDao.selectByUserName(username);
        if (usernameCount == 1) {
            throw new TipException("该用户名已经存在");
        }
        User user = new User();
        user.setName(username);
        user.setPassword(PlaneUtils.MD5encode(username + password));
        // 注册不能成为管理员
        // user.setRole("0");
        Date date = new Date();
        user.setCreatetime(date);
        user.setUpdatetime(date);
        user.setWorknumber(worknumber);
        user.setTasknum(0);

        int count = userDao.insertSelectiveIdInc(user);// 主键是int类型的值，无法自增
        // int count = userDao.insertSelective(user);
        return count;
    }

    /**
     * 根据用户id查询到用户
     *
     * @param uid
     * @return
     */
    @Override
    public User queryUserById(int uid) {
        User user = null;
        if (uid != 0) {
            user = userDao.selectByPrimaryKey(uid);
        }
        return user;
    }

    /**
     * 修改用户名密码
     *
     * @param request
     * @param oldpassword
     * @param password
     */
    @Override
    public boolean modifyPwd(HttpServletRequest request, String oldpassword, String password) {
        if (StringUtils.isBlank(oldpassword) || StringUtils.isBlank(password)) {
            throw new TipException("旧密码和新密码不能为空");
        }
        User user = PlaneUtils.getLoginUser(request);
        oldpassword = PlaneUtils.MD5encode(user.getName() + oldpassword);
        if (!oldpassword.equals(user.getPassword())) {
            throw new TipException("输入的原密码不正确");
        }
        password = PlaneUtils.MD5encode(user.getName() + password);
        if (oldpassword.equals(password)) {
            throw new TipException("新密码不能和原密码相同");
        }
        user.setPassword(password);
        user.setUpdatetime(DateKit.getNow());

        if (userDao.updateByPrimaryKeySelective(user) == 1)
            return true;
        else
            return false;
    }

    // @Override
    // public List<User> findByUserRole(User userExmple) {
    //
    // UserExample example = new UserExample();
    // UserExample.Criteria criteria = example.createCriteria();
    // criteria.andRoleEqualTo(userExmple.getRole());
    //
    // return userDao.selectByExample(example);
    // }

    @Override
    public TailPage<UserPojo> getAllUserWithPage(TailPage<UserPojo> page) {
        int count = userDao.selectUserCount();
        if (count < 1) {
            LOGGER.error("用户表为空");
        }
        page.setItemsTotalCount(count);
        List<User> userList = userDao.selectAllUser(page);
        List<UserPojo> userVoList = new ArrayList<>();
        if (userList.size() > 0) {
            Iterator<User> iterable = userList.iterator();
            while (iterable.hasNext()) {
                User user = iterable.next();
                UserPojo userPojo = new UserPojo(user);
                userVoList.add(userPojo);
            }
        }
        page.setItems(userVoList);
        return page;
    }

    @Override
    public int delUserById(Integer userid) {
        if (userid == null || userid <= 0) {
            throw new TipException("获取用户id错误");
        }
        // String Role = userDao.selectByPrimaryKey(userid).getRole();
        // if (StringUtils.isNotBlank(Role) && Role.equals("0")) {
        // throw new TipException("权限不足以删除管理员");
        // }
        int count = userDao.deleteByPrimaryKey(userid);
        if (count != 1) {
            throw new TipException("删除用户异常");
        }
        return count;
    }

    @Override
    public int addUserWithInfo(String addUsername, String addUserPaw, String addUserWorkNumber, String addUserNickname, String addUserEmail, String addUserPhone) {
        User user = new User();
        if (StringUtils.isBlank(addUsername)) {
            throw new TipException("新增用户名获取失败");
        } else {
            user.setName(addUsername);
        }
        if (StringUtils.isBlank(addUserPaw)) {
            throw new TipException("新增用户密码获取失败");
        } else {
            user.setPassword(PlaneUtils.MD5encode(addUsername + addUserPaw));
        }
        if (StringUtils.isBlank(addUserWorkNumber)) {
            throw new TipException("新增用户工号获取失败");
        } else {
            user.setWorknumber(addUserWorkNumber);
        }
        if (StringUtils.isNotBlank(addUserNickname)) {
            user.setNickname(addUserNickname);
        }
        if (StringUtils.isNotBlank(addUserEmail)) {
            user.setEmail(addUserEmail);
        }
        if (StringUtils.isNotBlank(addUserPhone)) {
            user.setPhoneone(addUserPhone);
        }
        user.setCreatetime(DateKit.getNowTime());
        user.setUpdatetime(DateKit.getNowTime());
        return userDao.insertSelectiveIdInc(user) == 1 ? 1 : 0;
    }

    @Override
    public boolean updataTasknumByUser(User user) {
        // TODO Auto-generated method stub
        if (userDao.userAddTasknum(user) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean reduceTasknumByUser(User user) {

        if (userDao.userReduceTasknum(user) == 1)
            return true;
        else
            return false;
    }

    @Override
    public User getUserById(Integer userid) {
        // TODO Auto-generated method stub
        return userDao.selectByPrimaryKey(userid);
    }

    @Override
    public List<User> fuzzySearchWithUser(String queryString) {
        if (StringUtils.isBlank(queryString)) {
            throw new TipException("用户输入的放飞员为空");
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + queryString + "%");
        List<User> bUserList = userDao.selectByExample(example);
        List<User> routingInspectionList = new ArrayList<>();
        Iterator<User> iterator = bUserList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            List<Integer> groupIdByUserId = user_has_groupKeyMapper.getGroupIdByUserId(user.getId());
            if (groupIdByUserId.contains(Integer.valueOf(3))) {
                routingInspectionList.add(user);
            }
        }
        return routingInspectionList;
    }

    @Override
    public int modifyUpdateTimeWithUserName(String name) {
        return userDao.updateLastTime(name);
    }

    @Override
    public int updateSelectiveWithUserId(Integer id, String nickName, String email, String phoneNumber) {
        User user = new User();
        if (id == null) {
            throw new TipException("修改用户id为空");
        } else {
            user.setId(id);
        }
        if (StringUtils.isBlank(nickName)) {
            throw new TipException("修改用户昵称获取失败");
        } else {
            user.setNickname(nickName);
        }
        if (StringUtils.isBlank(email)) {
            throw new TipException("修改用户邮箱获取失败");
        } else {
            user.setEmail(email);
        }
        if (StringUtils.isBlank(phoneNumber)) {
            throw new TipException("修改用户电话号码获取失败");
        } else {
            user.setPhoneone(phoneNumber);
        }
        return userDao.updateByPrimaryKeySelective(user) == 1 ? 1 : 0;
    }


    @Override
    public String getNameByUserId(Integer id) {

        return userDao.getNameByUserId(id);
    }


    @Override
    public TailPage<UserPojo> getUserByGroupIdOruserNameWithPage(Integer groupId, String userName, TailPage<UserPojo> page) {
        if (groupId == null && StringUtils.isBlank(userName)) {
            LOGGER.error("查询条件有误");
            throw new TipException("查询条件有误");
        }
        if (userName.equals(WebConst.SEARCH_NO_USERNAME)) {
            List<Integer> userIdList = new ArrayList<>();
            if (groupId == 0) {
                userIdList = user_has_groupKeyMapper.getAllGroup();
            } else {
                int count = user_has_groupKeyMapper.selectCountWithGroupId(groupId);
                userIdList = user_has_groupKeyMapper.getUserIdByGroupId(groupId, page);
                page.setItemsTotalCount(count);
            }
            List<User> userList = new ArrayList<>();
            if (userIdList != null && userIdList.size() != 0) {
                for (Integer userId : userIdList) {
                    User user = userDao.selectByPrimaryKey(userId);
                    userList.add(user);
                }
            } else {
                throw new TipException("该用户组没有用户");
            }
            List<UserPojo> userVoList = new ArrayList<>();
            if (userList.size() > 0) {
                Iterator<User> iterable = userList.iterator();
                while (iterable.hasNext()) {
                    User user = iterable.next();
                    UserPojo userPojo = new UserPojo(user);
                    userVoList.add(userPojo);
                }
            }
            page.setItems(userVoList);
        } else {
            List<User> userList = new ArrayList<>();
            if (userName.length() == 4) {
                User userByName = userDao.selectUserByUserName(userName);
                if (userByName == null) {
                    userName = userName.substring(0, 3);
                } else {
                    userList.add(userByName);
                    page.setItemsTotalCount(1);
                }
            }
            if (userName.length() == 3) {
                User userByName = userDao.selectUserByUserName(userName);
                if (userByName == null) {
                    userName = userName.substring(0, 2);
                } else {
                    userList.add(userByName);
                    page.setItemsTotalCount(1);
                }
            }
            if (userName.length() == 2) {
                User userByName = userDao.selectUserByUserName(userName);
                if (userByName == null) {
                    userName = userName.substring(0, 1);
                } else {
                    userList.add(userByName);
                    page.setItemsTotalCount(1);
                }
            }
            if (userName.length() == 1) {
                UserExample example = new UserExample();
                UserExample.Criteria criteria = example.createCriteria();
                criteria.andNameLike("%" + userName + "%");
                int count = userDao.selectCountByFuzzyName(userName);
                page.setItemsTotalCount(count);
                userList = userDao.selectByFuzzyNameWithPage(userName, page);
            }
            List<UserPojo> userVoList = new ArrayList<>();
            if (userList.size() > 0) {
                Iterator<User> iterable = userList.iterator();
                while (iterable.hasNext()) {
                    User user = iterable.next();
                    UserPojo userPojo = new UserPojo(user);
                    userVoList.add(userPojo);
                }
            }
            page.setItems(userVoList);
        }
        return page;
    }

    @Override
    public boolean updateByUser(User user) {
        if (userDao.updateByPrimaryKeySelective(user) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByName(String name) {
        User user = userDao.getUserByName(name);
        if (user.getId() != null && user.getId() != 0)
            return user;
        return null;
    }

    @Override
    public Integer getUserIdByName(String addUsername) {
        if (StringUtils.isNotBlank(addUsername)) {
            Integer userId = userDao.selectUserIdByUserName(addUsername);
            return userId == null ? userId : null;
        } else {
            throw new TipException("新增用户名为空");
        }
    }

    @Override
    public void addUserAuthorityWithUserName(String addUsername, String authority) {
        Integer userId = userDao.selectUserIdByUserName(addUsername);
        if (org.apache.commons.lang.StringUtils.isNotBlank(authority)) {
            List<String> stringList = Arrays.asList(authority.split(","));
            for (int i = 0; i < stringList.size(); i++) {
                Integer GroupId = null;
                if (stringList.get(i).equals("viewer")) {
                    GroupId = Integer.valueOf(1);
                } else if (stringList.get(i).equals("admin")) {
                    GroupId = Integer.valueOf(2);
                } else {
                    GroupId = Integer.valueOf(3);
                }
                int insertCount = user_has_groupKeyMapper.insertGroupByUserIdWithAuthority(userId, GroupId);
                if (insertCount == 0) {
                    throw new TipException("用户组插入异常");
                }
            }
        }
    }
}
