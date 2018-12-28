package hust.plane.service.interFace;

import hust.plane.mapper.pojo.Route;
import hust.plane.utils.page.TailPage;

import java.util.List;

public interface RouteService {

    List<Route> getAllRoute();

    List<Route> getRouteByNameAndType(String name, int type);

    TailPage<Route> queryRouteWithPage(Route route, TailPage<Route> page);

    boolean deleteRouteById(Integer id);

    Route getRouteById(Integer id);

    Route getRouteByName(String name);

    int getNumOfRouteByType(int i);

    Route getRouteWithFlagDataById(Integer routeId);

    List<String> fuzzySearchByName(String name);
}