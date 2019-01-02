package hust.plane.web.controller;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.mapper.pojo.Task;
import hust.plane.mapper.pojo.Uav;
import hust.plane.mapper.pojo.User;
import hust.plane.service.interFace.*;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.PlaneUtils;
import hust.plane.utils.PointUtil;
import hust.plane.web.controller.vo.FlyingPathVO;
import hust.plane.web.controller.vo.TaskVO;
import hust.plane.web.controller.vo.UavVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UavController {

    private static final Logger logger = LoggerFactory.getLogger(FlyingPathController.class);

    @Autowired
    public UavService uavServiceimpl;

    @Autowired
    public TaskService taskService;

    @Autowired
    private FlyingPathService flyingPathServiceImpl;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/plane")
    //获取飞机信息
    //实例解决经纬度路径
    public String getAllPlane(Model model) {
        List<Uav> allPlane = uavServiceimpl.getAllPlane();
        List<Double> p = PointUtil.StringPointToList(allPlane.get(0).getPosition());
        String pp = JsonUtils.objectToJson(p);
        model.addAttribute("pp", pp);
        return "plane";
    }

    @RequestMapping("/uavList")
    //获取飞机列表信息
    public String getPlaneList(Model model, HttpServletRequest request) {

        User user = PlaneUtils.getLoginUser(request);
        String role = null;
        //判别是观察者和浏览者
        List<Integer> groupIdList = userGroupService.selectGroupIdWithUserId(user.getId());
        if (groupIdList.contains(Integer.valueOf(1))) {
            //是浏览者
            role = "1";
        } else {
            //是观察者
            role = "2";
        }
        List<Task> taskList  = taskService.getTaskByCreatorAndStatus(user, 9);
      
        List<FlyingPathVO> flyingPathVOList = new ArrayList<FlyingPathVO>();   //初始化向前台传送的数据
        List<UavVO> planeVOList = new ArrayList<>();
        List<TaskVO> taskVOList = new ArrayList<>();

        if(taskList!=null) {
        	for (int i = 0; i < taskList.size(); i++) {

                FlyingPath flyingPath = flyingPathServiceImpl.selectByFlyingPathId(taskList.get(i).getFlyingpathId());
                FlyingPathVO flyingPathVO = new FlyingPathVO(flyingPath);
                flyingPathVOList.add(flyingPathVO);

                Task tasktemp = taskList.get(i);
                TaskVO taskVO = new TaskVO();
                taskVO.setTaskVO(tasktemp);

                taskVO.setFlyingpathName(flyingPath.getName());
                taskVO.setUserAName(userServiceImpl.getNameByUserId(tasktemp.getUserA()));
                taskVO.setUserZName(userServiceImpl.getNameByUserId(tasktemp.getUserZ()));
                taskVO.setUserCreatorName(userServiceImpl.getNameByUserId(tasktemp.getUsercreator()));
                taskVOList.add(taskVO);

                Uav uav = uavServiceimpl.getUavById(taskList.get(i).getUavId());
                UavVO planevo = new UavVO(uav);
                planeVOList.add(planevo);
            }
        	
        }else {   
        	taskList  = new ArrayList<Task>();
        }
      
        
        model.addAttribute("tasklist", JsonUtils.objectToJson(taskVOList));
        model.addAttribute("flyingPath", JsonUtils.objectToJson(flyingPathVOList));
        model.addAttribute("uavlist", JsonUtils.objectToJson(planeVOList));
        
        model.addAttribute("curNav", "uavAllList");

        return "uavListMap";
    }


    //多条件查询无人机：根据负责人id查询 或者 登记起始时间到登记终止时间段内查询
    @RequestMapping("doFindPlane")
    public String doFindPlane(Model model, @RequestParam("userid") int owner, @RequestParam("starttime") Date starttime, @RequestParam("endtime") Date endtime) {

        List<Uav> allPlane = uavServiceimpl.getPlaneByOption(owner, starttime, endtime);
        List<UavVO> planeList = new ArrayList<UavVO>();
        for (int i = 0; i < allPlane.size(); i++) {
            UavVO planevo = new UavVO(allPlane.get(i));
            planeList.add(planevo);
        }
        model.addAttribute("planelist", JsonUtils.objectToJson(planeList));
        model.addAttribute("curNav", "planFind");
        return "planeList";

    }
    //直接使用无人机发送的航角数据
/*	@RequestMapping(value = "/getPlaneAngle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getPlaneAngle(@RequestParam("preLongitude") String preLongitude, @RequestParam("preLatitude") String preLatitude,
								@RequestParam("currentLongitude") String currentLongitude, @RequestParam("currentLatitude") String currentLatitude) {
		AngleUtil.MyLatLng prePosition=new AngleUtil.MyLatLng(Double.valueOf(preLongitude),Double.valueOf(preLatitude));
		AngleUtil.MyLatLng currentPosition=new AngleUtil.MyLatLng(Double.valueOf(currentLongitude),Double.valueOf(currentLatitude));
		Double angle = Double.valueOf(Math.floor(AngleUtil.getAngle(prePosition,currentPosition)));
		if(angle==null){
			logger.error("===========获取飞机当前角度失败=============");
			return JsonView.render(1,"获取飞机角度失败");
		}
		return JsonView.render(0, WebConst.SUCCESS_RESULT,angle);
	}*/

}
