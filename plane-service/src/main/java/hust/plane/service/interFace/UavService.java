package hust.plane.service.interFace;

import hust.plane.mapper.pojo.Uav;

import java.util.Date;
import java.util.List;

public interface UavService {

    List<Uav> getAllPlane();

    List<Uav> getPlaneByOption(int userid, Date starttime, Date endtime);

	List<Uav> findByPlaneStatus(Uav plane);

	Uav getPlaneByPlane(Uav plane);

}
