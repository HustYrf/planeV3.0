package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.Route;
import hust.plane.utils.page.TailPage;

import java.util.List;

public interface RouteMapper {
    List<Route> selectALLRoute();

    int insert(Route route);

    List<Route> getRouteByNameAndType(String name, int type);

    List<Route> selectRoute(String name, int type);

    List<Route> getRouteByType(int type);

    int countByName(String name);

    int routeCount(Route route);

    int deleteRouteById(Integer id);

    Route getRouteById(Integer id);

    Route getRouteByName(String name);

    List<Route> queryRoutePage(Route route, TailPage<Route> page);

    Route getRouteWithFlagDataById(Integer id);

    int getIdByName(String name);

    List<String> fuzzySearchByName(String name);
}
