package hust.plane.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.mapper.pojo.Route;
import hust.plane.mapper.pojo.Task;
import hust.plane.service.interFace.FlyingPathService;
import hust.plane.service.interFace.RouteService;
import hust.plane.service.interFace.TaskService;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.JsonView;
import hust.plane.web.controller.vo.FlyingPathVO;
import hust.plane.web.controller.vo.RouteVO;

@Controller
public class FlyingPathController {
	
	@Autowired
	private FlyingPathService flyingPathServiceImpl;
	
	@Autowired
	public RouteService routeServiceImpl;

	@Autowired
	public TaskService taskServiceImpl;

	/*@Autowired
	private AirportService airportServiceImpl;*/
	
	@RequestMapping("/toImportPlanePath")
	public String toImportPlanePath(Model model)
	{
		model.addAttribute("curNav", "importPlanePath");
		return "importPlanePath";
	}
	
	@RequestMapping("/importPlanePath")
	@ResponseBody
	public String importPlanePath(FlyingPath flyingPath)
	{
		if(flyingPath!=null)
		{
			//E:\\hello.kml
			String filePath = "E:\\\\"+flyingPath.getName()+".kml";//设置文件名
			flyingPathServiceImpl.importFlyingPath(flyingPath, filePath);
		}
		return   new JsonView(0).toString();
	}
	
	//返回设定飞行路径页面，返回所有路由数据，并在前台显示
	@RequestMapping("/setFlyingPath")
	public String doSetFlyPath(Model model) {
		List<Route> allRoute = routeServiceImpl.getAllRoute();
		List<RouteVO> routeList = new ArrayList<RouteVO>();
		for (int i = 0; i < allRoute.size(); i++) {
					
			RouteVO routeVo = new RouteVO(allRoute.get(i));
			routeList.add(routeVo);
		}
		
		model.addAttribute("routeList", JsonUtils.objectToJson(routeList));
		model.addAttribute("curNav","setFlyPath");
		return "setFlyingPath";
	}
	
	//获取前台传输的路径字符串
	@RequestMapping("/doSetFlyPath")
	@ResponseBody
	public String doSetFlyPath(FlyingPath flyingPath) {
		
		//System.out.println(route.getRoutePath());
		
		if(flyingPathServiceImpl.insertFlyingPath(flyingPath)==true)
			
			return "success";
		else
			return "failed";
	}
	
	 //查询所有的飞行路径列表  分页查询
	@RequestMapping("/doGetFlyingPathList")
	public String doGetFlyPathListQueryPage(FlyingPath flyingPath,TailPage<FlyingPath> page, Model model) {
		
    	if(flyingPath.getName()==null || flyingPath.getName()==""){
    		flyingPath.setName(null);
		}
        model.addAttribute("inputName",flyingPath.getName());

        page = flyingPathServiceImpl.queryFlyingPathWithPage(flyingPath,page);

        model.addAttribute("page",page);
        model.addAttribute("curNav", "flyingPathList");
        return "flyingPathList";
		
		
	}
	
	//返回一条飞行路径，包括所有信息，在这里使用包装类，用于画图
	@RequestMapping(value="/showPlanePath",method=RequestMethod.GET)
	public String showPlanePath(@RequestParam("id") int id,Model model) {
			
		FlyingPath flyingPath = flyingPathServiceImpl.selectByFlyingPathId(id);
		FlyingPathVO flyingPathVO = new FlyingPathVO(flyingPath);
		
		model.addAttribute("PlanePath",JsonUtils.objectToJson(flyingPathVO));
		return "showFlyingPath";
	}
	
	//删除飞行路径
	@RequestMapping(value = "/deletePlanePath", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String deletePlanePath(FlyingPath flyingPath) {
		if(flyingPath.getId()!=null) {
			//查询所有的任务中是否含有该飞行路径
			List<Task> list =taskServiceImpl.getFlyingPathByFlyingId(flyingPath.getId());
			if(list!=null)
			{
				return new JsonView(0,"SUCCESS","任务中有该条飞行路径,删除失败").toString();
			}
			else {
				if(flyingPathServiceImpl.deleteFlyingPath(flyingPath)) {
					return new JsonView(0,"SUCCESS","删除成功").toString();
				}
				return new JsonView(0,"SUCCESS","删除失败").toString();
			}
		}
		return new JsonView(0,"SUCCESS","未传入飞行路径编号,删除失败").toString();

	}
	
}
