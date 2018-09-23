package hust.plane.mapper.mapper;

import java.util.Date;
import java.util.List;

import hust.plane.mapper.pojo.Uav;

public interface UavMapper {
	
	List<Uav> selectALLPlane();
	List<Uav> selectPlaneByOption(int id,Date starttime,Date endtime);
	List<Uav> selectByPlaneStatus(int status);
	Uav getPlaneByPlane(Uav uav);
	String getNameById(Integer id);
}
