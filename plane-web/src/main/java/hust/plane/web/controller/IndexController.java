package hust.plane.web.controller;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.mapper.pojo.Route;
import hust.plane.service.interFace.FlyingPathService;
import hust.plane.service.interFace.InfoPointService;
import hust.plane.service.interFace.RouteService;
import hust.plane.utils.pojo.JsonView;
import hust.plane.web.controller.vo.FlyingPathVO;
import hust.plane.web.controller.vo.RouteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private FlyingPathService flyingPathServiceImpl;

    @Autowired
    private RouteService routeServiceImpl;

    @Autowired
    private InfoPointService infoPointServiceImpl;

    @RequestMapping(value = "indexSearch", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String indexSearch(@RequestParam(value = "queryType") Integer queryType, @RequestParam(value = "queryString") String queryString) {
        List<String> resultList = new ArrayList<>();
        try {
            switch (queryType){
                case 1:{   //查询光缆
                    resultList = routeServiceImpl.fuzzySearchByName(queryString);
                    break;
                }
                case 2:{   //查询飞行路径
                    resultList = flyingPathServiceImpl.fuzzySearchByName(queryString);
                    break;
                }
                case 3:{   //查询信息点

                    break;
                }
                default:break;
            }
        } catch (Exception e) {
            String msg = "用户模糊搜素失败";
            return JsonView.render(1, msg);
        }
        return JsonView.render(0, WebConst.SUCCESS_RESULT, resultList);
    }

    @RequestMapping(value = "getRouteByName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getRouteByName(@RequestParam(value = "name") String name) {

        Route route = routeServiceImpl.getRouteByName(name);
        RouteVO  routeVO = new RouteVO(route);

        return JsonView.render(0, WebConst.SUCCESS_RESULT, routeVO);
    }

    @RequestMapping(value = "getFlyingPathByName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getFlyingPathByName(@RequestParam(value = "name") String name) {

        FlyingPath flyingPath = flyingPathServiceImpl.getFlyingPathByName(name);
        FlyingPathVO flyingPathVO = new FlyingPathVO(flyingPath);

        return JsonView.render(0, WebConst.SUCCESS_RESULT, flyingPathVO);
    }

    //测试视频流界面
    @RequestMapping("test")
    public String test(){
        return "test";
    }

}
