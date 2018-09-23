package hust.plane.web.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hust.plane.mapper.pojo.Uav;
import hust.plane.service.interFace.UavService;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.PointUtil;
import hust.plane.web.controller.vo.UavVO;

@Controller
public class UavController {
	@Autowired
	public UavService planeServiceimpl;
	
	@RequestMapping("/plane")
	//获取飞机信息
	//实例解决经纬度路径
	public String getAllPlane(Model model)
	{
		List<Uav> allPlane = planeServiceimpl.getAllPlane();
		List<Double> p=PointUtil.StringPointToList(allPlane.get(0).getPosition());
		String pp =JsonUtils.objectToJson(p);
		model.addAttribute("pp",pp);		
		return "plane";
	}

	@RequestMapping("/planeList")
	//获取飞机列表信息
	//实例解决经纬度路径
	public String getPlaneList(Model model)
	{
		List<Uav> allPlane = planeServiceimpl.getAllPlane();
		List<UavVO> planeList = new ArrayList<UavVO>(); 	
		for(int i=0;i<allPlane.size();i++) {
			UavVO planevo = new UavVO(allPlane.get(i)) ;
			planeList.add(planevo);	
		}
		model.addAttribute("planelist",JsonUtils.objectToJson(planeList));
		model.addAttribute("curNav", "planeAllList");
		//System.out.println(planeList.size());
		//return JsonUtils.objectToJson(planeList);
		return "planeList";
	}
	
	
	
	
	
	//多条件查询无人机：根据负责人id查询 或者 登记起始时间到登记终止时间段内查询
	@RequestMapping("doFindPlane")
	public String doFindPlane(Model model,@RequestParam("userid")int owner,@RequestParam("starttime")Date starttime,@RequestParam("endtime")Date endtime) {
		
		List<Uav> allPlane = planeServiceimpl.getPlaneByOption(owner,starttime,endtime);
		List<UavVO> planeList = new ArrayList<UavVO>();	
		for(int i=0;i<allPlane.size();i++) {
			UavVO planevo = new UavVO(allPlane.get(i)) ;
			planeList.add(planevo);	
		}
		model.addAttribute("planelist",JsonUtils.objectToJson(planeList));
		model.addAttribute("curNav", "planFind");
		return "planeList";	
		
	}
//	@RequestMapping("/setFlyPath")
//	public String setFlyPath(Model model){
//		model.addAttribute("curNav", "setFlyPath");
//		return "setFlyPath";
//	}
	
}
