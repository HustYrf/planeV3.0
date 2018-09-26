package hust.plane.service.interFace;

import java.util.List;

import hust.plane.mapper.pojo.Route;
import hust.plane.utils.page.TailPage;

public interface RouteService {
	
    List<Route> getAllRoute();

    List<Route> getRouteByNameAndType(String name, int type);

    TailPage<Route> queryRouteWithPage(Route route, TailPage<Route> page);

    boolean deleteRouteById(Integer id);

    Route getRouteById(Integer id);

    Route getRouteByName(String name);
}