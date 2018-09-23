package hust.plane.service.interFace;

import java.util.List;

import hust.plane.mapper.pojo.Route;

public interface RouteService {
	
    List<Route> getAllRoute();

    List<Route> getRouteByNameAndType(String name, int type);
}