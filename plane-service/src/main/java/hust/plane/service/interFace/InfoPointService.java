package hust.plane.service.interFace;

import hust.plane.mapper.pojo.InfoPoint;

import java.util.List;

public interface InfoPointService {

    List<InfoPoint> getAllInfoPoint();

	List<InfoPoint> selectInfoPointByName(String name);

	List<String> fuzzySearchByName(String queryString);


}
