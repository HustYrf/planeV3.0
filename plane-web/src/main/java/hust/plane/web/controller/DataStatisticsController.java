package hust.plane.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hust.plane.constant.StatisticsParam;
import hust.plane.service.impl.RouteServiceImpl;
import hust.plane.service.interFace.RouteService;
import hust.plane.utils.JsonUtils;
import hust.plane.web.controller.vo.RouteStatisticsVo;
import net.sf.ehcache.statistics.extended.ExtendedStatistics.Statistic;

@Controller
public class DataStatisticsController {
	
	@Autowired
	private RouteService routeServiceImpl;
	
	//用于告警数据统计
	@RequestMapping("dataAlarm")
	public String dealWithAlarm()
	{
		return "";
	}
	
	//用于跳转路由数据统计
	@RequestMapping("dataRoute")
	public String dealWithRoute(Model model)
	{
		model.addAttribute("curNav", "taskStatistics");

		return "statistics";
	}
	
	//用于城市数据统计
	@RequestMapping("cityRouteStatistics")
	@ResponseBody
	public List<RouteStatisticsVo> dealWithCity(String target)
	{
		//统计一干二干混合的数据
		int mixCount = routeServiceImpl.getNumOfRouteByType(0);
		int oneCount =routeServiceImpl.getNumOfRouteByType(1);
		int twoCount =routeServiceImpl.getNumOfRouteByType(2);
		List<RouteStatisticsVo> resultList = new ArrayList<RouteStatisticsVo>();
		RouteStatisticsVo v1 = new RouteStatisticsVo();
		v1.setValue(oneCount);
		resultList.add(v1);
		v1.setName(StatisticsParam.ROUTE_TYPE_ONE);
		RouteStatisticsVo v2 = new RouteStatisticsVo();
		v2.setValue(twoCount);
		resultList.add(v2);
		v2.setName(StatisticsParam.ROUTE_TYPE_TWO);
		RouteStatisticsVo v3 = new RouteStatisticsVo();
		v3.setValue(mixCount);
		v3.setName(StatisticsParam.ROUTE_TYPE_MIX);
		resultList.add(v3);
		return resultList;
	}
	
	//用于告警周统计数据
	@RequestMapping("cityAlarmWeekStatistics")
	@ResponseBody
	public List<Integer> dealWithcityAlarmWeekStatistics(String target)
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(20);
		list.add(40);
		list.add(10);
		list.add(10);
		list.add(20);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(10);
		list.add(23);
		list.add(34);
		return list;
	}

}
