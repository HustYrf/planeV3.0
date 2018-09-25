package hust.plane.mapper.mapper;

import java.util.List;

import hust.plane.mapper.pojo.Route;
import hust.plane.utils.page.TailPage;

public interface RouteMapper {
	List<Route> selectALLRoute();

	int insert(Route route);

	List<Route> getRouteByNameAndType(String name, int type);

	List<Route> selectRoute(String name, int type);

	List<Route> getRouteByType(int type);

	int countByName(String name);

    int routeCount(Route route);

	List<Route> queryFlyingPathPage(Route route, TailPage<Route> page);
}
