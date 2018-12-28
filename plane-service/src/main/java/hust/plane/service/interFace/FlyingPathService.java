package hust.plane.service.interFace;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.utils.page.TailPage;

import java.util.List;

public interface FlyingPathService {

    void importFlyingPath(FlyingPath flyingPath, String filePath);

    boolean insertFlyingPath(FlyingPath flyingPath);

    TailPage<FlyingPath> queryFlyingPathWithPage(FlyingPath planePath, TailPage<FlyingPath> page);

    List<FlyingPath> findAllFlyingPath();

    boolean deleteFlyingPath(FlyingPath flyingPath);

    FlyingPath selectByFlyingPathId(Integer id);

    FlyingPath selectByFlyingPathIdWithoutData(Integer id);

    String getNameById(Integer id);

    List<String> fuzzySearchByName(String queryString);

    FlyingPath getFlyingPathByName(String name);
}
