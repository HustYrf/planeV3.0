package hust.plane.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hust.plane.constant.WebConst;
import hust.plane.utils.pojo.InfoTplData;
import hust.plane.utils.pojo.JsonView;
import hust.plane.web.controller.vo.QueryRouteVO;
import hust.plane.web.controller.vo.RouteVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import hust.plane.mapper.pojo.Route;
import hust.plane.service.interFace.RouteService;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.LineUtil;

@Controller
public class RouteController {

    @Autowired
    public RouteService routeServiceImpl;
    
	@RequestMapping("/home")
	public String index(Model model)
	{
		model.addAttribute("curNav", "home");
		return "home";
	}
	
	//提供 模板下载
	@RequestMapping("routeExcelDownloed")
	public void routeExcelDownloed(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String 	basepath = request.getSession().getServletContext().getRealPath("");
		
		//String basepath = request.getSession().getServletContext().getRealPath("/WEB-INF/ftl"); 

		File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            // 调用工具类的createDoc方法生成excel文档
            file = new File(basepath +file.separator+ "RouteTemplate.xlsx");
            fin = new FileInputStream(file);
 
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // 设置浏览器以下载的方式处理该文件名
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("RouteTemplate.xlsx", "UTF-8"))));
 
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];  // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的excel文件的内容输出到浏览器中
            while((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if(fin != null) fin.close();
            if(out != null) out.close();
           
        }
				
	}
	
    // 得到路由分布。解决路径序列
    @RequestMapping("/erouteList")
    public String getAllRoute(Model model) {
        List<Route> allRoute = routeServiceImpl.getAllRoute();

        ArrayList<ArrayList<Double>> s = LineUtil.stringLineToList(allRoute.get(1).getRoutepathdata());
        String path = JsonUtils.objectToJson(s);
        List<String> listString = LineUtil.pathToArray(path);
        String position = listString.get(0);

        InfoTplData infoTplData = new InfoTplData("巡检路线1",
                "<img src=\"http://s7d2.scene7.com/is/image/Caterpillar/C10602924?$cc-s$\" />",
                "工程机械行业或许不是最危险的，但是危险程度也不低。我们身边随时都有事故发生。当我在网络上看到那么多事故的照片时，我甚至有时候都想放弃这一行去改行做其他的  可是我除了会开挖机还会干什么呢！都说隔行如隔山 。都到了有家庭的年纪了再去改行我又应该拿什么去撑起这个家呢！没有办法只有硬着头皮继续走下去。当我在这一行做的越久我就越胆小，当看到别人干一些比较危险的活或者做一些比较危险的动作的时候我都很佩服他，我承认我不如他。");
        String result = JsonUtils.objectToJson(infoTplData);
        model.addAttribute("infoData", result);
        model.addAttribute("position", position);
        model.addAttribute("path", path);
        model.addAttribute("curNav", "routeList");
        return "route";
    }

    //显示全部路由线路
    @RequestMapping("/routeList")
    public String egetAllRoute(Model model) {
        List<Route> allRoute = routeServiceImpl.getAllRoute();
        List<RouteVO> routeList = new ArrayList<RouteVO>();
        for (int i = 0; i < allRoute.size(); i++) {
            RouteVO routeVo = new RouteVO(allRoute.get(i));
            routeList.add(routeVo);
        }
        model.addAttribute("routeList", JsonUtils.objectToJson(routeList));
        model.addAttribute("curNav", "routeList");
        return "routeList";
    }

    // 跳转到路由路径
    @RequestMapping("/routeImport")
    public String toRouteImport(Model model) {
        model.addAttribute("curNav", "routeImport");
        return "importRoute";
    }

    /**
     * POST请求
     *
     * @param [model, routeId, status]
     * @return java.lang.String
     * @author rfYang
     * @date 2018/7/8 11:17
     */
    @RequestMapping(value = "/queryRoute", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryRoute(Model model, @RequestParam String routeId, @RequestParam String type) {
        QueryRouteVO queryRouteVo = new QueryRouteVO(routeId, type);
        return JsonView.render(0, WebConst.SUCCESS_RESULT, queryRouteVo);
    }

    /**
     * 跳转到查询路由处
     *
     * @param [model, routeId, type]
     * @return java.lang.String
     * @author rfYang
     * @date 2018/7/8 15:35
     */
    @RequestMapping(value = "/queryRoute/{id}/{type}", method = RequestMethod.GET)
    public String toRouteQuery(Model model, @PathVariable("id") String id, @PathVariable("type") int type) {
        List<Route> allRoute =   routeServiceImpl.getRouteByNameAndType(id, type);
        List<RouteVO> routeList = new ArrayList<RouteVO>();
        for (int i = 0; i < allRoute.size(); i++) {
            RouteVO routeVO = new RouteVO(allRoute.get(i));
            routeList.add(routeVO);
        }
        model.addAttribute("routeList", JsonUtils.objectToJson(routeList));
        model.addAttribute("curNav", "queryRoute");
        return "queryRoute";
    }

}
