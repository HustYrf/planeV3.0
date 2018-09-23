package hust.plane.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.plane.mapper.mapper.FlyingPathMapper;
import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.service.interFace.FlyingPathService;
import hust.plane.utils.KMLUtil;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.PlanePathVo;

@Service
public class FlyingPathServiceImpl implements FlyingPathService {

	@Autowired
	private FlyingPathMapper flyingPathMapper;

	@Override

	public void importFlyingPath(FlyingPath flyingPath, String filePath) {
		FlyingPath planePathList = flyingPathMapper.selectByFlyingPathVO(flyingPath);
		List<PlanePathVo> plist = KMLUtil.textToList(planePathList.getPathdata(), planePathList.getHeightdata());
		KMLUtil.importKML(filePath, plist);
	}

	// 插入一条飞行路径
	@Override
	public boolean insertFlyingPath(FlyingPath flyingPath) {

		flyingPath.setPathdata("LINESTRING" + flyingPath.getPathdata());
		Date date = new Date();
		flyingPath.setCreatetime(date);
		flyingPath.setUpdatetime(date);
		
		// 然后在下面进行插入数据
		if (flyingPathMapper.insertFlyingPath(flyingPath) == 1)
			return true;
		else
			return false;
	}

	@Override
	public FlyingPath selectByFlyingPathId(Integer id) {

		FlyingPath path = flyingPathMapper.selectByFlyingPathId(id);
		return path;
	}

	@Override
	public TailPage<FlyingPath> queryFlyingPathWithPage(FlyingPath flyingPath, TailPage<FlyingPath> page) {

		int count = flyingPathMapper.flyingPathCount(flyingPath);
		page.setItemsTotalCount(count);
		List<FlyingPath> flyingPaths = flyingPathMapper.queryFlyingPathPage(flyingPath, page);

		page.setItems(flyingPaths);
		return page;
	}

	@Override
	public List<FlyingPath> findAllFlyingPath() {

		List<FlyingPath> planePaths = flyingPathMapper.findAllFlyingPath();
		return planePaths;
	}

	@Override
	public boolean deleteFlyingPath(FlyingPath flyingPath) {

		flyingPathMapper.deleteFlyingPath(flyingPath);
		return true;
	}

}
