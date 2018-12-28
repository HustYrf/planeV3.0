package hust.plane.web.controller;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.*;
import hust.plane.service.interFace.*;
import hust.plane.utils.ImgUtils;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.PlaneUtils;
import hust.plane.utils.pojo.JsonView;
import hust.plane.utils.pojo.TipException;
import hust.plane.web.controller.vo.AlarmDetailVO;
import hust.plane.web.controller.vo.AlarmVO;
import hust.plane.web.controller.vo.RouteVO;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class AlarmController {
    private static Logger logger = LoggerFactory.getLogger(AlarmController.class);
    @Autowired
    private AlarmService alarmService;

    @Autowired
    private UavService uavServiceImpl;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private TaskService taskServiceImpl;

    @Autowired
    private FlyingPathService flyingPathServiceImpl;

    @Autowired
    private RouteService routeServiceImpl;

    @Value("${BASE_IMAGE_URL}") // 服务器地址
    private String BASE_IMAGE_URL;

    @Value("${TASK_DIR}")
    private String TASK_DIR;

    @Value("${IMAGE_ALARM}") // 文件夹
    private String ALARM_DIR;

    //返回所有的告警点
    @RequestMapping("/alarmList")
    public String alarmList(Model model) {
        List<Alarm> alarmAll = alarmService.getAllAlarm();
        List<AlarmVO> alarmList = new ArrayList<AlarmVO>();
        Iterator<Alarm> iterator = alarmAll.iterator();
        while (iterator.hasNext()) {
            Alarm alarm = iterator.next();
            AlarmVO alarmVo = new AlarmVO(alarm);
            alarmList.add(alarmVo);
        }
        //告警点路由显示
        List<Route> allRoute = routeServiceImpl.getAllRoute();
        List<RouteVO> routeList = new ArrayList<RouteVO>();
        for (int i = 0; i < allRoute.size(); i++) {
            RouteVO routeVo = new RouteVO(allRoute.get(i));
            routeList.add(routeVo);
        }

        model.addAttribute("routeList", JsonUtils.objectToJson(routeList));
        model.addAttribute("alarmList", JsonUtils.objectToJson(alarmList));
        model.addAttribute("curNav", "alarmList");
        return "alarmHistory";
    }

    /**
     * 返回相应的告警信息
     *
     * @return java.lang.String
     * @author rfYang
     * @date 2018/7/6 15:05
     */
    @RequestMapping(value = "alarmInfo", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String getAlarmInfo(@RequestParam(value = "id") int id) {

        Alarm alarm = alarmService.selectAlarmById(id);

        Task task = new Task();
        task.setId(alarm.getTaskId());
        Task task1 = taskServiceImpl.getTaskByTask(task);
        Uav uav = new Uav();
        uav.setId(task1.getUavId());
        Uav uav1 = uavServiceImpl.getPlaneByPlane(uav);
        String flyingPathName = flyingPathServiceImpl.getNameById(task1.getFlyingpathId());

        AlarmDetailVO alarmDetailVO = new AlarmDetailVO(alarm);
        //设置从文件服务器查看图片

        alarmDetailVO.setImage(BASE_IMAGE_URL + TASK_DIR + task1.getMissionId() + "/" + ALARM_DIR + alarmDetailVO.getImage());
        alarmDetailVO.setUav(uav1);

        alarmDetailVO.setTaskName(task1.getName());
        alarmDetailVO.setFlyingPathName(flyingPathName);
        alarmDetailVO.setUserCreatorName(userServiceImpl.getNameByUserId(task1.getUsercreator()));
        alarmDetailVO.setUserAName(userServiceImpl.getNameByUserId(task1.getUserA()));
        alarmDetailVO.setUserZName(userServiceImpl.getNameByUserId(task1.getUserZ()));

        return JsonView.render(0, WebConst.SUCCESS_RESULT, alarmDetailVO);
    }

    @RequestMapping(value = "alarmImport", method = RequestMethod.GET)
    public String toAlarmImport(Model model, HttpServletRequest request) {

        User aUser = PlaneUtils.getLoginUser(request);
        List<Task> tasklist = taskServiceImpl.getTasklistByAuser(aUser);
        List<Uav> uavlist = uavServiceImpl.getAllPlane();
        model.addAttribute("planelist", uavlist);
        model.addAttribute("tasklist", tasklist);
        model.addAttribute("curNav", "alarmImport");
        return "importAlarmImg";
    }


    @RequestMapping(value = "importAlarm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String doImportAlarm(@RequestParam(value = "planeId") int planeId, HttpServletRequest request) {
        try {
            alarmService.insertAlarmById(planeId);
        } catch (Exception e) {
            String msg = "插入告警点失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                logger.error(msg, e);
            }
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT);
    }


    //导入告警图片
    @RequestMapping(value = "importAlarmImg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String importAlarmImg(@RequestParam(value = "taskid") int taskid, @RequestParam(value = "planeid") int planeid, @RequestParam("AlarmImgs") MultipartFile[] AlarmImgs, HttpServletRequest request) {

        List<String> list = new ArrayList<String>();
        if (AlarmImgs != null && AlarmImgs.length > 0) {
            for (int i = 0; i < AlarmImgs.length; i++) {
                MultipartFile file = AlarmImgs[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            File newfile = new File(list.get(i));
            try {
                Alarm alarm = ImgUtils.printImageTags(newfile, taskid);
                alarmService.insertAlarmByAlarms(alarm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "告警图片导入成功！";
    }

    //保存图片
    private List<String> saveFile(HttpServletRequest request, MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中     
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator + file.getOriginalFilename();
                //System.out.println(filePath);  这个保存路径是个问题啊
                list.add(filePath);
                File savefile = new File(filePath);
                if (!savefile.getParentFile().exists())
                    savefile.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(savefile);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    @RequestMapping("/gxdxAlarmpic/{path}/{filename}")
    public void testpic(@PathVariable(value = "filename") String picName, @PathVariable(value = "path") String docPath, HttpServletResponse response) throws IOException {
        FileInputStream fis = null;
        File file = new File("D://" + docPath + "//" + picName + ".JPG");
        //File file = new File("home/images/test.png"); 服务器目录和本地图片的区别是图片路径
        fis = new FileInputStream(file);
        response.setContentType("image/jpg"); //设置返回的文件类型
        response.setHeader("Access-Control-Allow-Origin", "*");//设置该图片允许跨域访问
        IOUtils.copy(fis, response.getOutputStream());
    }

    //修改告警信息
    @RequestMapping(value = "modifyAlarmDes", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String doModify(@RequestParam(value = "id") int alarmid, @RequestParam(value = "description") String description) {
        try {
            boolean flag = alarmService.updateAlarmDesc(alarmid, description);
            if (flag == false) {
                throw new TipException("告警点描述信息插入失败");
            }
        } catch (Exception e) {
            String msg = "插入告警点失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                logger.error(msg, e);
            }
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT);
    }


}
