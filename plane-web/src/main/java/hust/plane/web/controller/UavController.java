package hust.plane.web.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.mapper.pojo.Task;
import hust.plane.mapper.pojo.User;
import hust.plane.service.interFace.FlyingPathService;
import hust.plane.service.interFace.TaskService;
import hust.plane.utils.AngleUtil;
import hust.plane.utils.PlaneUtils;
import hust.plane.utils.pojo.JsonView;
import hust.plane.web.controller.vo.FlyingPathVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hust.plane.mapper.pojo.Uav;
import hust.plane.service.interFace.UavService;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.PointUtil;
import hust.plane.web.controller.vo.UavVO;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UavController {

    private static final Logger logger = LoggerFactory.getLogger(FlyingPathController.class);

    @Autowired
    public UavService uavServiceimpl;

    @Autowired
    public TaskService taskService;

    @Autowired
    private FlyingPathService flyingPathServiceImpl;

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

        List<Task> taskList = taskService.getTaskByCreatorAndStatus(user, 9);

        // List<FlyingPathVO> flyingPathVOList = new ArrayList<FlyingPathVO>();   //初始化向前台传送的数据
        List<UavVO> planeList = new ArrayList<>();

        //去除重复的无人机 暂时的方案
        List listTemp = new ArrayList<Integer>();
        for (int i = 0; i < taskList.size(); i++) {

            //FlyingPath flyingPath = flyingPathServiceImpl.selectByFlyingPathId(taskList.get(i).getFlyingpathId());
            //FlyingPathVO flyingPathVO = new FlyingPathVO(flyingPath);
            //flyingPathVOList.add(flyingPathVO);
            if(!listTemp.contains(taskList.get(i).getUavId())){    //为了去除重复的无人机
                listTemp.add(taskList.get(i).getUavId());

                Uav uav = uavServiceimpl.getUavById(taskList.get(i).getUavId());
                UavVO planevo = new UavVO(uav);
                planeList.add(planevo);

            }
        }

        //model.addAttribute("flyingPath", JsonUtils.objectToJson(flyingPathVOList));
        model.addAttribute("uavlist", JsonUtils.objectToJson(planeList));
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
