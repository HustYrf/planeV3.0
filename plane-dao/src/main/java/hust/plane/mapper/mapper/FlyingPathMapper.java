package hust.plane.mapper.mapper;


import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.utils.page.TailPage;

import java.util.List;

public interface FlyingPathMapper {

    FlyingPath selectByFlyingPathVO(FlyingPath flyingPath);

    //void insertFlyingPath();

    int insertFlyingPath(FlyingPath flyingPath);

    FlyingPath selectByFlyingPathId(Integer id);

    int flyingPathCount(FlyingPath flyingPath);

    List<FlyingPath> queryFlyingPathPage(FlyingPath flyingPath, TailPage<FlyingPath> page);

    List<FlyingPath> findAllFlyingPath();

    int deleteFlyingPath(FlyingPath flyingPath);

    String getNameById(Integer id);

    FlyingPath selectByFlyingPathIdWithoutData(Integer id);

    int countByName(String name);

    List<String> fuzzySearchByName(String name);

    FlyingPath getFlyingPathByName(String name);
}
