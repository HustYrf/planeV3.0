package hust.plane.service.impl;

import hust.plane.mapper.mapper.UavMapper;
import hust.plane.mapper.pojo.Uav;
import hust.plane.service.interFace.UavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UavServiceImpl implements UavService{
	
	@Autowired
	public UavMapper uavMapper;

	@Override
	public List<Uav> getAllPlane() {
		
		List<Uav> planeList = uavMapper.selectALLPlane();
		return planeList;
	}

	@Override
	public List<Uav> getPlaneByOption(int owner, Date starttime, Date endtime) {
	
		List<Uav> planeList = uavMapper.selectPlaneByOption(owner,starttime,endtime);
		return planeList;
	}

	@Override
	public List<Uav> findByPlaneStatus(Uav uav) {
		
		List<Uav> planeList = uavMapper.selectByPlaneStatus(uav.getStatus());
		return planeList;
	}

	@Override
	public Uav getPlaneByPlane(Uav uav) {
		
		return uavMapper.getPlaneByPlane(uav);
	}


}
