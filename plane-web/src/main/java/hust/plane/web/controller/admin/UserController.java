package hust.plane.web.controller.admin;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.User;
import hust.plane.service.interFace.DepartmentService;
import hust.plane.service.interFace.UserGroupService;
import hust.plane.service.interFace.UserService;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.UserPojo;
import hust.plane.utils.pojo.JsonView;
import hust.plane.utils.pojo.TipException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private UserGroupService userGroupService;
    @Resource
    private DepartmentService departmentService;

//    @RequestMapping(value = "searchUser", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String doSearchUser(@RequestParam Integer GroupId, @RequestParam(required = false) String userName,
//                               TailPage<User> page) {
//        if (StringUtils.isBlank(userName)) {
//            userName = WebConst.SEARCH_NO_USERNAME;
//        }
//        page = userService.getUserByGroupIdOruserNameWithPage(GroupId, userName, page);
//         pageList.add(page);
//        return JsonView.render(0,WebConst.SUCCESS_RESULT);
//    }


    @RequestMapping(value = "toUserCreate", method = RequestMethod.GET)
    public String userModify(TailPage<UserPojo> page, Model model, @RequestParam(required = false) Integer GroupId,
                             @RequestParam(required = false) String userName, @RequestParam(required = false) String pageNum) {
        if (GroupId == null || GroupId == 0) {
            GroupId = null;
        }
        if (GroupId != null || StringUtils.isNotBlank(userName)) {
            if (StringUtils.isBlank(userName)) {
                userName = WebConst.SEARCH_NO_USERNAME;
            }
            page = userService.getUserByGroupIdOruserNameWithPage(GroupId, userName, page, pageNum);
        } else {
            page = userService.getAllUserWithPage(page);
        }
        List<UserPojo> pojoList = page.getItems();
        Iterator<UserPojo> iterator = pojoList.iterator();
        while (iterator.hasNext()) {
            UserPojo userPojo = iterator.next();
            List<Integer> userGroupList = userGroupService.selectGroupIdWithUserId(userPojo.getId());
            StringBuilder userPosition = new StringBuilder();
            if (userGroupList.size() > 0 && userGroupList.contains(Integer.valueOf(1))) {
                userPosition.append("浏览者 ");
//                userPojo.setPosition(");
            }
            if (userGroupList.size() > 0 && userGroupList.contains(Integer.valueOf(2))) {
                userPosition.append("任务管理员 ");
//                userPojo.setPosition("");
            }
            if (userGroupList.size() > 0 && userGroupList.contains(Integer.valueOf(3))) {
                userPosition.append("巡检员");
//                userPojo.setPosition("巡检员");
            }
            userPojo.setPosition(userPosition.toString());
        }
        page.setItems(pojoList);
        if (StringUtils.isNotBlank(userName) && !userName.equals(WebConst.SEARCH_NO_USERNAME)) {
            model.addAttribute("inputname", userName);
        }
        model.addAttribute("selectStatus", GroupId);
        model.addAttribute("page", page);
        model.addAttribute("curNav", "usersEdit");
        return "userModify";
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String doDelUser(@RequestParam Integer userid) {
        try {
            userService.delUserById(userid);
        } catch (Exception e) {
            String msg = "删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT);
    }

    @RequestMapping(value = "modifyUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deModifyUser(@RequestParam(value = "id") Integer id, @RequestParam(value = "nickName") String nickName,
                               @RequestParam(value = "email") String email, @RequestParam(value = "phone") String phoneNumber,
                               @RequestParam(value = "departmentId") String departmentId) {
        try {
            int updateCount = userService.updateSelectiveWithUserId(id, nickName, email, phoneNumber,departmentId);
            if (updateCount == 0) {
                throw new TipException("用户修改异常");
            }
        } catch (Exception e) {
            String msg = "修改失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String doAddUser(@RequestParam String addUsername, @RequestParam String addUserPaw,
                            @RequestParam String addUserWorkNumber,@RequestParam String addUserDepartment,
                            @RequestParam(required = false) String addUserNickname,
                            @RequestParam(required = false) String addUserEmail, @RequestParam(required = false) String addUserPhone,
                            @RequestParam(required = false) String authority) {
        try {
            int userCount = userService.addUserWithInfo(addUsername, addUserPaw, addUserWorkNumber,addUserDepartment,addUserNickname, addUserEmail, addUserPhone);
            if (userCount == 1 && StringUtils.isNotBlank(authority)) {
                userService.addUserAuthorityWithUserName(addUsername, authority);
            }
        } catch (Exception e) {
            String msg = "添加失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT);
    }

    @RequestMapping(value = "userAuthority", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userAuthority(@RequestParam(value = "id") Integer id, @RequestParam(value = "authorityArrays") List<String> authorityList) {
        try {
            userGroupService.updateAuthorityWithUserId(id, authorityList);
        } catch (Exception e) {
            String msg = "用户组添加失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT);
    }


}
