package hust.plane.service.impl;

import hust.plane.mapper.mapper.FlyingPathMapper;
import hust.plane.mapper.mapper.InfoPointMapper;
import hust.plane.mapper.mapper.RouteMapper;
import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.mapper.pojo.Route;
import hust.plane.service.interFace.FileService;
import hust.plane.utils.ExcelUtil;
import hust.plane.utils.TxtReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private InfoPointMapper infoPointMapper;

    @Autowired
    private FlyingPathMapper flyingPathMapper;

    // 插入路由数据
    @Override
    public boolean insertRoute(File file) {

        Route route = new Route();
        List<InfoPoint> infoPoints = new ArrayList<>();

        if (ExcelUtil.readExcel(file, route, infoPoints) == false) {
            return false;
        }
        // 设置创建时间
        Date date = new Date();
        route.setCreatetime(date);

        int count = routeMapper.countByName(route.getName());

        if (count > 0)
            return false;

        if (routeMapper.insert(route) == 1) {
            int id = routeMapper.getIdByName(route.getName());
            for (int i = 0; i < infoPoints.size(); i++) {
                infoPoints.get(i).setRouteId(id);
            }

            infoPointMapper.insertInfoPointList(infoPoints);

            return true;
        } else
            return false;

    }

    //从excel读取飞行路径，并且写入数据库
    @Override
    public boolean insertFlyingPath(File f) {

        FlyingPath flyingPath = new FlyingPath();
        if (TxtReaderUtil.readFlyingPathFromTxt(f, flyingPath) == false) {
            return false;
        }
        flyingPath.setCreatetime(new Date());
        flyingPath.setUpdatetime(new Date());
        int count = flyingPathMapper.countByName(flyingPath.getName());
        if (count > 0) {
            return false;
        }
        if (flyingPathMapper.insertFlyingPath(flyingPath) == 1) {
            return true;
        }
        return false;
    }

}
