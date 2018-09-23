package hust.plane.mapper.mapper;


import java.util.List;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.utils.page.TailPage;

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
}
