package hust.plane.service.impl;

import java.util.ArrayList;
import java.util.List;

import hust.plane.utils.page.TailPage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.plane.mapper.mapper.RouteMapper;
import hust.plane.mapper.pojo.Route;
import hust.plane.service.interFace.RouteService;

@Service
public class RouteServiceImpl implements RouteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);
    @Autowired
    private RouteMapper routeMapper;

    //查询所有路由
    @Override
    public List<Route> getAllRoute() {
        List<Route> routeList = routeMapper.selectALLRoute();
        return routeList;
    }

    //从路由名字和类型查询路由
    @Override
    public List<Route> getRouteByNameAndType(String name, int type) {
        List<Route> routeList = new ArrayList<>();
      
        if (StringUtils.isBlank(name)) {
            LOGGER.error("查询的路由name为空");
            routeList = routeMapper.getRouteByType(type);
        }else {
           routeList = routeMapper.getRouteByNameAndType(name, type);
        }
        
        return routeList;
      
    }

    @Override
    public TailPage<Route> queryRouteWithPage(Route route, TailPage<Route> page) {

        int count = routeMapper.routeCount(route);
        page.setItemsTotalCount(count);
        List<Route> routeList = routeMapper.queryFlyingPathPage(route, page);
        page.setItems(routeList);
        return page;

    }

    //  删除路由
    @Override
    public boolean deleteRouteById(Integer id) {

        if(routeMapper.deleteRouteById(id) == 1){
            return true;
        }
        return false;
    }

    @Override
    public Route getRouteById(Integer id) {
        return routeMapper.getRouteById(id);
    }

    @Override
    public Route getRouteByName(String name) {
        return routeMapper.getRouteByName(name);
    }

	@Override
	public int getNumOfRouteByType(int i) {
		Route route = new Route();
		route.setType(i);
		int count = routeMapper.routeCount(route);
		if(count !=0)
		{
			return count;
		}
		return 0;
	}
}
