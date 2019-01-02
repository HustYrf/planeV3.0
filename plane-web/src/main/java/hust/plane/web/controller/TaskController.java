package hust.plane.web.controller;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.*;
import hust.plane.service.interFace.*;
import hust.plane.utils.DateKit;
import hust.plane.utils.HttpClientUtil;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.PlaneUtils;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.TaskPojo;
import hust.plane.utils.pojo.JsonView;
import hust.plane.utils.pojo.TipException;
import hust.plane.web.controller.vo.*;
import hust.plane.web.controller.webUtils.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TaskController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    @Value(value = "${BASE_IMAGE_URL}")    //访问图片的地址
    private String BASE_IMAGE_URL;

    @Value(value = "${imgPath}")    //后台图片保存地址
    private String imgPath;

    @Value(value = "${IMAGE_SOURCE}")    //访问图片的地址
    private String IMAGE_SOURCE;

    @Value(value = "${IMAGE_ALARM}")    //后台图片保存地址
    private String IMAGE_ALARM;

    @Value(value = "${uploadLocalHost}")
    private String FILE_UPLOAD_HOST;

    @Value(value = "${DETECT_SERVER}")
    private String DETECT_SERVER;

    @Value(value="${SERVER_TYPE}")
    private String SERVER_TYPE;
    
    @Autowired
    private TaskService taskServiceImpl;
    @Autowired
    private FlyingPathService flyingPathServiceImpl;
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private UavService uavServiceImpl;
    @Autowired
    private AlarmService alarmserviceImpl;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping("/task")
    public String gettestTask() {
        return "taskList";
    }

    //主页显示   传输所有的数据
    @RequestMapping("/home")
    public String index(Model model, HttpServletRequest request) {

        User userCreator = PlaneUtils.getLoginUser(request);
        List<Integer> groupIdList = userGroupService.selectGroupIdWithUserId(userCreator.getId());
        Task task = new Task();
        if (groupIdList.contains(Integer.valueOf(1))) {
            task.setUsercreator(null);   //浏览者
        } else {
            task.setUsercreator(userCreator.getId());  //任务管理员
        }
        List<FlyingPathVO> flyingPathVOList = new ArrayList<FlyingPathVO>();   //初始化向前台传送的数据
        List<AlarmDetailVO> alarmDetailVOList = new ArrayList<AlarmDetailVO>();

        List<Task> taskList = taskServiceImpl.getAllTaskByRole(task);

        Iterator<Task> iterator = taskList.iterator();    //遍历所有数据
        while (iterator.hasNext()) {
            Task task1 = iterator.next();
            String userCreatorName = userServiceImpl.getNameByUserId(task1.getUsercreator());
            String userAName = userServiceImpl.getNameByUserId(task1.getUserA());
            String userZName = userServiceImpl.getNameByUserId(task1.getUserZ());
            Uav uav = new Uav();
            uav.setId(task1.getUavId());
            Uav uav1 = uavServiceImpl.getPlaneByPlane(uav);
            List<Alarm> alarmWithIdList = alarmService.getAlarmsByTaskId(task1.getId());

            FlyingPath flyingPath = flyingPathServiceImpl.selectByFlyingPathId(task1.getFlyingpathId());
            FlyingPathVO flyingPathVO = new FlyingPathVO(flyingPath);
            flyingPathVOList.add(flyingPathVO);           //添加数据

            for (int i = 0; i < alarmWithIdList.size(); i++) {

                AlarmDetailVO alarmDetailVO = new AlarmDetailVO(alarmWithIdList.get(i));
                alarmDetailVO.setUav(uav1);
                //设置来自图片服务器的数据
                //                     2113.123.12.12/   ImageTask/       YYMMDDXXXX/        ImageAlarm/      1.jpg
                alarmDetailVO.setImage(BASE_IMAGE_URL + imgPath + task1.getMissionId() + "/" + IMAGE_ALARM + alarmDetailVO.getImage());
                alarmDetailVO.setTaskName(task1.getName());
                alarmDetailVO.setFlyingPathName(flyingPath.getName());
                alarmDetailVO.setUserCreatorName(userCreatorName);
                alarmDetailVO.setUserAName(userAName);
                alarmDetailVO.setUserZName(userZName);

                alarmDetailVOList.add(alarmDetailVO);           //添加数据
            }
        }

        model.addAttribute("flyingPath", JsonUtils.objectToJson(flyingPathVOList));
        model.addAttribute("alarmList", JsonUtils.objectToJson(alarmDetailVOList));
        model.addAttribute("curNav", "home");
        return "home";
    }

    // 得到所有的任务
    @RequestMapping("/taskList")
    public String getALLTask(Model model) {
        List<TaskPojo> allTask = taskServiceImpl.getALLTask();
        model.addAttribute("taskList", allTask);
        model.addAttribute("curNav", "taskAllList");
        return "taskList";
    }

    // 跳转任务创建
    @RequestMapping("/toTaskCreate")
    public String toTaskCrate(Model model, Task task) {
        // 操作者
        // 放飞者
        // 回收者
        // 无人机编号
        // 飞行路线
        Task task2 = new Task();
        TaskVO taskVO = new TaskVO();
        FlyingPath flyingPath = new FlyingPath();

        List<Uav> uavs = uavServiceImpl.getAllPlane();
        List<FlyingPath> planePaths = flyingPathServiceImpl.findAllFlyingPath();

        model.addAttribute("uavs", uavs);
        model.addAttribute("planePaths", planePaths);

        taskVO.setPlanstarttime(DateKit.get2HoursLater());
        taskVO.setPlanendtime(DateKit.get4HoursLater());
        taskVO.setCreatetime(new Date());

        if (task.getFlyingpathId() != null && task.getFlyingpathId() != 0) {
            flyingPath = flyingPathServiceImpl.selectByFlyingPathIdWithoutData(task.getFlyingpathId());
        } else {
            if (task.getId() != null && task.getId() != 0) { // 判断对象是否为空
                task2 = taskServiceImpl.getTaskByTask(task);
                if (task2.getPlanstarttime() == null) {
                    task2.setPlanstarttime(DateKit.get2HoursLater());
                }
                if (task2.getPlanendtime() == null) {
                    task2.setPlanendtime(DateKit.get4HoursLater());
                }
                if (task2.getCreatetime() == null) {
                    task2.setCreatetime(new Date());
                }
                if (task2.getUserA() != null) {
                    taskVO.setUserAName(userServiceImpl.getNameByUserId(task2.getUserA()));
                }
                if (task2.getUserZ() != null) {
                    taskVO.setUserZName(userServiceImpl.getNameByUserId(task2.getUserZ()));
                }
                if (task2.getFlyingpathId() != null) {
                    taskVO.setFlyingpathName(flyingPathServiceImpl.getNameById(task2.getFlyingpathId()));
                }
                if (task2.getUavId() != null) {
                    taskVO.setUavName(uavServiceImpl.getNameById(task2.getUavId()));
                } else {
                    taskVO.setUavName("");
                }
                taskVO.setTaskVO(task2);

            }

        }
        model.addAttribute("flyingPath", flyingPath);
        model.addAttribute("taskvo", taskVO);
        model.addAttribute("curNav", "createTask");

        return "createTask";
    }

    // 提交任务
    @RequestMapping(value = "taskSubmit", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String taskSubmit(HttpServletRequest request, TaskVO taskVO) {

        User createUser = PlaneUtils.getLoginUser(request);
        Task task = new Task();
        User userA = null;
        User userZ = null;

        if (taskVO.getUserAName() != null && taskVO.getUserAName() != "") {
            userA = userServiceImpl.getUserByName(taskVO.getUserAName());
            if (userA == null) {
                return JsonView.render(1, "任务提交失败，起飞员不存在！");
            } else {
                task.setUserA(userA.getId());
            }
        }
        if (taskVO.getUserZName() != null && taskVO.getUserZName() != "") {

            userZ = userServiceImpl.getUserByName(taskVO.getUserZName());
            if (userZ == null) {
                return JsonView.render(1, "任务提交失败，降落员不存在！");
            } else {
                task.setUserZ(userZ.getId());
            }
        }
        if (taskVO.getUavId() != null && taskVO.getUavId() != 0) {
            task.setUavId(taskVO.getUavId());
        }
        if (taskVO.getFlyingpathId() != null && taskVO.getFlyingpathId() != 0) {
            task.setFlyingpathId(taskVO.getFlyingpathId());
        }
        if (taskVO.getPlanstarttime() != null) {
            task.setPlanstarttime(taskVO.getPlanstarttime());
        }
        if (taskVO.getPlanendtime() != null) {
            task.setPlanendtime(taskVO.getPlanendtime());
        }
        if (taskVO.getCreatetime() != null) {
            task.setCreatetime(taskVO.getCreatetime());
        }


        task.setName(taskVO.getName());
        //task.setCreatetime(new Date());
        task.setUsercreator(createUser.getId());
        // 初始状态为1提交
        task.setStatus(1);
        task.setFinishstatus(0);
        // 设置状态未完成
        // 提交的任务

        if (taskServiceImpl.saveTask(task) == true) {
            //目前服务器生成规则为YYMMDDXXXX。年份采用两位表示,XXXX代表序号从0001到9999
            Task task2 = taskServiceImpl.getTaskByName(taskVO.getName());
            String pre = DateKit.getMissionId(task2.getCreatetime());
            String suf = task2.getId() % 9999 + "";        //防止 序号满
            while (suf.length() < 4) {
                suf = "0" + suf;
            }
            task2.setMissionId(pre + suf);
            taskServiceImpl.saveTask(task2);

            return JsonView.render(1, "任务提交成功!");
        }
        return JsonView.render(1, "任务提交失败!");
    }


    // 创建任务
    @RequestMapping(value = "taskCreate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String taskCreate(HttpServletRequest request, TaskVO taskVO) {

        User createUser = PlaneUtils.getLoginUser(request);
        Task task = new Task();
        User userA = null;
        User userZ = null;

        if (taskVO.getUserAName() != null && taskVO.getUserAName() != "") {
            userA = userServiceImpl.getUserByName(taskVO.getUserAName());
            if (userA == null) {
                return JsonView.render(1, "任务创建失败，起飞员不存在！");
            } else {
                task.setUserA(userA.getId());
            }
        }
        if (taskVO.getUserZName() != null && taskVO.getUserZName() != "") {

            userZ = userServiceImpl.getUserByName(taskVO.getUserZName());
            if (userZ == null) {
                return JsonView.render(1, "任务创建失败，降落员不存在！");
            } else {
                task.setUserZ(userZ.getId());
            }
        }
        if (taskVO.getUavId() != null && taskVO.getUavId() != 0) {
            task.setUavId(taskVO.getUavId());
        }
        if (taskVO.getFlyingpathId() != null && taskVO.getFlyingpathId() != 0) {
            task.setFlyingpathId(taskVO.getFlyingpathId());
        }
        if (taskVO.getPlanstarttime() != null) {
            task.setPlanstarttime(taskVO.getPlanstarttime());
        }
        if (taskVO.getPlanendtime() != null) {
            task.setPlanendtime(taskVO.getPlanendtime());
        }
        if (taskVO.getCreatetime() != null) {
            task.setCreatetime(taskVO.getCreatetime());
        }

        task.setName(taskVO.getName());
        //task.setCreatetime(new Date());
        task.setUsercreator(createUser.getId());
        // 初始状态为0创建
        task.setStatus(0);
        task.setFinishstatus(0);

        // 设置状态未完成
        if (taskServiceImpl.saveTask(task) == true) {
            return JsonView.render(1, "任务创建成功!");
        }
        return JsonView.render(1, "任务创建失败!");
    }

    // 分页查询
    @RequestMapping("/taskPageList")
    public String queryPage(Task task, TailPage<TaskPojo> page, Model model, HttpServletRequest request) {

        User userCreator = PlaneUtils.getLoginUser(request);
        List<Integer> groupIdList = userGroupService.selectGroupIdWithUserId(userCreator.getId());
        if (groupIdList.contains(Integer.valueOf(1))) {
            task.setUsercreator(null);
        } else {
            task.setUsercreator(userCreator.getId());
        }
        if (task.getName() == null || task.getName() == "") {
            task.setName(null);
        } else {
            model.addAttribute("inputName", task.getName());
        }
        if (task.getFinishstatus() == null || task.getFinishstatus() == -1) {
            task.setFinishstatus(null);
        } else {
            model.addAttribute("selectStatus", task.getFinishstatus());
        }

        page = taskServiceImpl.queryPage(task, page);

        model.addAttribute("page", page);
        model.addAttribute("curNav", "taskAllList");
        return "taskList";
    }

    // 时间逆序查询
    @RequestMapping(value = "timeReverseView", method = RequestMethod.GET)
    public String timeReverseView(Task task, TailPage<TaskPojo> page, Model model, HttpServletRequest request) {

        User userCreator = PlaneUtils.getLoginUser(request);
        List<Integer> groupIdList = userGroupService.selectGroupIdWithUserId(userCreator.getId());
        if (groupIdList.contains(Integer.valueOf(1))) {
            task.setUsercreator(null);
        } else {
            task.setUsercreator(userCreator.getId());
        }
        if (task.getName() == "" || task.getName() == null) {
            task.setName(null);
        } else {
            model.addAttribute("inputName", task.getName());
        }
        if (task.getFinishstatus() == null || task.getFinishstatus() == -1) {
            task.setFinishstatus(null);
        } else {
            model.addAttribute("selectStatus", task.getFinishstatus());
        }

        page = taskServiceImpl.queryPage(task, page);

        model.addAttribute("page", page);
        model.addAttribute("curNav", "taskAllList");
        return "taskList";
    }

    // 确认放飞
    @RequestMapping(value = "onsureFly", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String onsureFly(Task task) {

        if (taskServiceImpl.setStatusTaskByTask(task, 8) == true) {
            return JsonView.render(1, "已确认，可以放飞");
        } else {
            return JsonView.render(1, "确认失败,请重试！");
        }

    }

    //取消任务
    @RequestMapping(value = "cancelTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String cancelTask(Task task) {

        if (taskServiceImpl.setTaskOver(task) == true) {
            Task task2 = taskServiceImpl.getTaskByTask(task);
            User userA = userServiceImpl.getUserById(task2.getUserA());
            User userZ = userServiceImpl.getUserById(task2.getUserZ());

            userServiceImpl.reduceTasknumByUser(userA); // 减少az任务数目
            userServiceImpl.reduceTasknumByUser(userZ);

            return JsonView.render(1, "任务已取消！");
        } else {
            return JsonView.render(1, "任务取消失败！");
        }

    }

    // 拒绝放飞
    @RequestMapping(value = "rejectFly", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String rejectFly(Task task) {

        if (taskServiceImpl.setStatusTaskByTask(task, 4) == true) {
            return JsonView.render(1, "已驳回，不可放飞");
        } else {
            return JsonView.render(1, "驳回失败,请重试!");
        }
    }

    // 重启任务
    @RequestMapping(value = "reStartTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String reStartTask(Task task) {

        if (taskServiceImpl.setStatusTaskByTask(task, 2) == true) {// 设置任务分派
            return JsonView.render(1, "任务已重启，已分派到指定人员！");
        } else {
            return JsonView.render(1, "任务重启失败，请重试!");
        }
    }

    // 归档任务
    @RequestMapping(value = "finishTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String finishTask(Task task) {

        if (taskServiceImpl.setTaskOver(task) == true) {
            Task task2 = taskServiceImpl.getTaskByTask(task);
            User userA = userServiceImpl.getUserById(task2.getUserA());
            User userZ = userServiceImpl.getUserById(task2.getUserZ());

            userServiceImpl.reduceTasknumByUser(userA); // 减少az任务数目
            userServiceImpl.reduceTasknumByUser(userZ);

            return JsonView.render(1, "任务已归档！");
        } else {
            return JsonView.render(1, "任务归档失败，请重试!");
        }
    }

    // 任务分派
    @RequestMapping(value = "assignTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String assignTask(Task task) throws InterruptedException {

        Task task2 = taskServiceImpl.getTaskByTask(task);
        if (task2.getUserA() == null || task2.getUserA() == 0) {
            return JsonView.render(1, "放飞员为空,任务分派失败!");
        }
        if (task2.getUserZ() == null || task2.getUserZ() == 0) {
            return JsonView.render(1, "接机员为空,任务分派失败!");
        }
        if (task2.getFlyingpathId() == null || task2.getFlyingpathId() == 0) {
            return JsonView.render(1, "未指定飞行路径,任务分派失败!");
        }

        //跨域请求创建文件夹
        String url = DETECT_SERVER + "makeTaskDir.action";
        Map<String, String> params = new HashMap<String, String>();
        params.put("missionId", "" + task2.getMissionId());
        String alarmlistString = HttpClientUtil.doPost(url, params);
        System.out.println(alarmlistString);

        if (alarmlistString.equals("success")) {
            User userA = userServiceImpl.getUserById(task2.getUserA());
            User userZ = userServiceImpl.getUserById(task2.getUserZ());
            if (taskServiceImpl.setStatusTaskByTask(task2, 2) == true) {// 设置任务分派

                taskServiceImpl.updataImgFolderByTask(task2);  //写入数据库

                userServiceImpl.updataTasknumByUser(userA); // 增加az任务数目
                userServiceImpl.updataTasknumByUser(userZ);

                return JsonView.render(1, "任务分派成功!");
            } else {
                return JsonView.render(1, "任务分派失败!");
            }
        } else {
            return JsonView.render(1, "任务分派失败!");
        }

    }

    // 确认任务完成，这个暂时不用
    @RequestMapping(value = "onsureTaskOver", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String onsureTaskOver(Task task, HttpServletRequest request) {

        Task task2 = taskServiceImpl.getTaskByTask(task);
        int status = task2.getStatus();

        User userA = userServiceImpl.getUserById(task2.getUserA());
        User userZ = userServiceImpl.getUserById(task2.getUserZ());

        if (status == 9) {
            taskServiceImpl.setStatusTaskByTask(task, 10);
            taskServiceImpl.setFinishStatusTaskByTask(task, 1);

            userServiceImpl.reduceTasknumByUser(userA); // 减少az任务数目
            userServiceImpl.reduceTasknumByUser(userZ);

            return JsonView.render(1, "巡视任务确认完成!");
        } else {

            return JsonView.render(1, "巡视任务确认失败!");
        }
    }

    // 删除处于创建状态的任务
    @RequestMapping(value = "deleteTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteTask(Task task) {

        if (task.getId() != null) {
            if (taskServiceImpl.deleteByTask(task) == true) {
                return new JsonView(0, "SUCCESS", "删除成功").toString();
            }
            return new JsonView(0, "SUCCESS", "删除失败").toString();
        }

        return new JsonView(0, "SUCCESS", "未传入飞行路径编号,删除失败").toString();
    }


    //跳转无人机（单个）页面  ，同时显示任务、飞行路径
    @RequestMapping("getTaskPlaneLocation")
    public String getTaskPlaneLocation(Model model, @RequestParam("uavid") Integer uavid, @RequestParam("taskid") Integer taskid, HttpServletRequest request) {
        String role = null;
        Uav uav = new Uav();
        uav.setId(uavid);
        Uav uav2 = uavServiceImpl.getPlaneByPlane(uav);
        UavVO uavVO = new UavVO(uav2);
        User user = PlaneUtils.getLoginUser(request);
        //判别是观察者和浏览者
        List<Integer> groupIdList = userGroupService.selectGroupIdWithUserId(user.getId());
        if (groupIdList.contains(Integer.valueOf(1))) {
            //是浏览者
            role = "1";

        } else {
            //是观察者
            role = "2";
        }

        Task task = new Task();
        task.setId(taskid);
        Task task1 = taskServiceImpl.getTaskByTask(task);

        TaskVO taskVO = new TaskVO();
        taskVO.setTaskVO(task1);

        taskVO.setUserAName(userServiceImpl.getNameByUserId(task1.getUserA()));
        taskVO.setUserZName(userServiceImpl.getNameByUserId(task1.getUserZ()));
        taskVO.setUserCreatorName(userServiceImpl.getNameByUserId(task1.getUsercreator()));

        FlyingPath flyingPath = flyingPathServiceImpl.selectByFlyingPathId(taskVO.getFlyingpathId());
        FlyingPathVO flyingPathVO = new FlyingPathVO(flyingPath);

        model.addAttribute("path", JsonUtils.objectToJson(flyingPathVO));
        model.addAttribute("uav", JsonUtils.objectToJson(uavVO));
        model.addAttribute("task", taskVO);
        model.addAttribute("role", role);
        model.addAttribute("uav2", uav2);

        return "plane";
    }

    // 打印任务报告
    @RequestMapping("taskReport")
    public void taskReport(Task task, HttpServletRequest request, HttpServletResponse response) {

        Task task2 = taskServiceImpl.getTaskByTask(task);

        User userCreator = userServiceImpl.getUserById(task2.getUsercreator());
        User userA = userServiceImpl.getUserById(task2.getUserA());
        User userZ = userServiceImpl.getUserById(task2.getUserZ());

        Uav uav = new Uav(); // 任务执行的无人机
        uav.setId(task2.getUavId());
        uav = uavServiceImpl.getPlaneByPlane(uav);

        List<Alarm> alarms = alarmserviceImpl.getAlarmsByTaskId(task2.getId());
        List<AlarmVO> alarmVos = new ArrayList<AlarmVO>();

//        String webappRoot = WordUtils.getRootPath();
        if (alarms.size() > 0) {
            for (int i = 0; i < alarms.size(); ++i) {
                AlarmVO alarmVo = new AlarmVO(alarms.get(i));

                //读取本机图片服务器数据
                //alarmVo.setImage(BASE_IMAGE_URL+ imgPath + task2.getId() + "/" +  IMAGE_ALARM + alarmVo.getImage());
                alarmVo.setImage(FILE_UPLOAD_HOST + imgPath + task2.getMissionId() + "/" + IMAGE_ALARM + alarmVo.getThumbnail());
                alarmVo.setBase();
                // alarmVo.setImgBaseCode();
                alarmVos.add(alarmVo);
            }
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("alarms", alarmVos);
        dataMap.put("uav", uav);
        dataMap.put("task", task2);
        dataMap.put("userA", userCreator);
        dataMap.put("userB", userA);
        dataMap.put("userC", userZ);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String filename = task2.getName() + "-" + sdf.format(date) + ".doc";

        try {
            WordUtils.exportMillCertificateWord(request, response, dataMap, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> groupIdList = userGroupService.selectGroupIdWithUserId(userCreator.getId());
        if (groupIdList.contains(Integer.valueOf(1))) {
            //是浏览者,不改变状态
        } else {
            //是观察者，改变状态
            if (task2.getStatus() == 12) {
                taskServiceImpl.setStatusTaskByTask(task, 13); // 设置打印报告完成
            }
        }
    }

    /**
     * 人员动态搜索提示
     */
    @RequestMapping(value = "searchFlyer", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String searchFlyerTips(@RequestParam(value = "queryString") String queryString) {
        List<String> userNameList = new ArrayList<>();
        try {
            List<User> bUserList = userServiceImpl.fuzzySearchWithUser(queryString);
            for (User user : bUserList) {
                userNameList.add(user.getName());
            }
        } catch (Exception e) {
            String msg = "用户模糊搜素失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT, userNameList);
    }

    // 获取任务的告警信息,同时显示告警信息
    @RequestMapping(value = "alarmWithId", method = RequestMethod.GET)
    public String getAlarmWithId(@RequestParam(value = "id") int id, Model model) {
        Task task = new Task();
        task.setId(id);
        Task task1 = taskServiceImpl.getTaskByTask(task);

        String flyingPathName = flyingPathServiceImpl.getNameById(task1.getFlyingpathId());

        Uav uav = new Uav();
        uav.setId(task1.getUavId());
        Uav uav1 = uavServiceImpl.getPlaneByPlane(uav);

        List<Alarm> alarmWitIdList = alarmService.getAlarmsByTaskId(Integer.valueOf(id));
        List<AlarmDetailVO> alarmDetailVOList = new ArrayList<AlarmDetailVO>();

        String userCreatorName = userServiceImpl.getNameByUserId(task1.getUsercreator());
        String userAName = userServiceImpl.getNameByUserId(task1.getUserA());
        String userZName = userServiceImpl.getNameByUserId(task1.getUserZ());
        Iterator<Alarm> iterator = alarmWitIdList.iterator();

        while (iterator.hasNext()) {
            Alarm alarm = iterator.next();
            AlarmDetailVO alarmDetailVO = new AlarmDetailVO(alarm);
            alarmDetailVO.setUav(uav1);
            //设置来自图片服务器的数据
            alarmDetailVO.setImage(BASE_IMAGE_URL + imgPath + task1.getMissionId() + "/" + IMAGE_ALARM + alarmDetailVO.getImage());

            alarmDetailVO.setTaskName(task1.getName());
            alarmDetailVO.setFlyingPathName(flyingPathName);
            alarmDetailVO.setUserCreatorName(userCreatorName);
            alarmDetailVO.setUserAName(userAName);
            alarmDetailVO.setUserZName(userZName);

            alarmDetailVOList.add(alarmDetailVO);
        }
        FlyingPath flyingPath = flyingPathServiceImpl.selectByFlyingPathId(task1.getFlyingpathId());
        FlyingPathVO flyingPathVO = new FlyingPathVO(flyingPath);

        model.addAttribute("flyingPath", JsonUtils.objectToJson(flyingPathVO));
        model.addAttribute("alarmList", JsonUtils.objectToJson(alarmDetailVOList));

        return "alarmListWithTaskId";
    }


    //查看上传的照片
    @RequestMapping(value = "imageWithId")
    public String getTaskImageWithId(@RequestParam(value = "id") int id, Model model) {
        if (Integer.valueOf(id) != null) {

            String fileROOT = BASE_IMAGE_URL + imgPath;
            String folder = IMAGE_SOURCE;

            Task task = new Task();
            task.setId(id);
            Task task1 = taskServiceImpl.getTaskByTask(task);
            List<String> picNameList = new ArrayList<>(

            );
            //跨域请求文件服务器上的图片文件名列表
            String url = DETECT_SERVER + "taskImages.action";
            Map<String,String> params = new HashMap<String, String>();
            params.put("missionId",""+ task1.getMissionId());
            String alarmlistString = HttpClientUtil.doPost(url,params);

            if(alarmlistString.equals("null")){
              model.addAttribute("picNameList",JsonView.render(0, "该任务上传的图片为空！"));
            }
            else{
              String[] pictures = alarmlistString.split(",");    //分割得到的字符串
              picNameList= Arrays.asList(pictures);                      //加入到list对象中
              model.addAttribute("picNameList",JsonView.render(1, "",JsonUtils.objectToJson(picNameList)));
            }

            model.addAttribute("baseImageUrl", fileROOT);
            model.addAttribute("folder", folder);

            if (picNameList.size() > 0)
                model.addAttribute("picNameList", JsonView.render(1, "", JsonUtils.objectToJson(picNameList)));
            else
                model.addAttribute("picNameList", JsonView.render(0, "该任务上传的图片为空！"));

            model.addAttribute("missionId", task1.getMissionId());
        }
        return "taskPicture";
    }

    //识别图片
    @RequestMapping(value = "recongize", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String recongizePicture(Task task) {

        //httpclient跨域请求进行识别
        String url = DETECT_SERVER + "createAlarm.action";
        Task task1 = taskServiceImpl.getTaskByTask(task);
        Map<String, String> params = new HashMap<String, String>();
        params.put("taskId", "" + task1.getId());
        params.put("missionId", "" + task1.getMissionId());
            
        String alarmlistString = HttpClientUtil.doPost(url, params);    //httpclient远程访问
       
        if (alarmlistString.equals("success")) {
            taskServiceImpl.setStatusTaskByTask(task, 12);
            return JsonView.render(0, "识别完成!");
        } else {
            return JsonView.render(0, "识别未完成！");
        }

    }

}
