package hust.plane.service.interFace;

import java.util.List;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.utils.page.TailPage;

public interface FlyingPathService {

	void importFlyingPath(FlyingPath flyingPath, String filePath);

    boolean insertFlyingPath(FlyingPath flyingPath);

    TailPage<FlyingPath> queryFlyingPathWithPage(FlyingPath planePath, TailPage<FlyingPath> page);

	List<FlyingPath> findAllFlyingPath();

	boolean deleteFlyingPath(FlyingPath flyingPath);

	FlyingPath selectByFlyingPathId(Integer id);
}
