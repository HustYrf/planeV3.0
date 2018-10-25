package hust.plane.mapper.mapper;


import hust.plane.mapper.pojo.FlyingPath_has_RouteKey;

import java.util.List;

public interface FlyingPath_has_RouteMapper {

    List<FlyingPath_has_RouteKey> getAllRouteByFlyingPathId(Integer id);
}