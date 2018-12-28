package hust.plane.service.impl;

import hust.plane.mapper.mapper.FlyingPath_has_RouteMapper;
import hust.plane.mapper.pojo.FlyingPath_has_RouteKey;
import hust.plane.service.interFace.FlyingPath_has_RouteKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlyingPath_has_RouteKeyServiceImpl implements FlyingPath_has_RouteKeyService {

    @Autowired
    private FlyingPath_has_RouteMapper flyingPath_has_routeMapper;

    @Override
    public List<FlyingPath_has_RouteKey> getAllFlyingPathId(Integer id) {
        List<FlyingPath_has_RouteKey> flyingPath_has_routeKeyList = flyingPath_has_routeMapper.getAllRouteByFlyingPathId(id);
        if (flyingPath_has_routeKeyList.size() > 0) {
            return flyingPath_has_routeKeyList;
        } else {
            return null;
        }
    }
}
