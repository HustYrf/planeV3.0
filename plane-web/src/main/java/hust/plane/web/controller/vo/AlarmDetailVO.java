package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.mapper.pojo.Uav;
import hust.plane.utils.DateKit;
import hust.plane.utils.GetLonLat;
import hust.plane.utils.PointUtil;

import java.util.ArrayList;
import java.util.List;

public class AlarmDetailVO {
	// 用于展示告警详情
	private int id;
	private String image;
	private String descripte;
	private String createTime;
	private String updateTime;
	private String infoName;

	private List<Double> position;
	// 新增照片四角的坐标位置
	private List<List<Double>> picPosition;
	private String taskName;
	private String flyingPathName;

	private String userCreatorName;
	private String userAName;
	private String userZName;

	private String uavName;
	private String uavDeviceId;

	public AlarmDetailVO(Alarm alarm) {

		this.id = alarm.getId();
		if (alarm.getImageurl() != null) {
			this.image = alarm.getImageurl();
		}
		if (alarm.getDescription() != null) {
			this.descripte = alarm.getDescription();
		}
		if (alarm.getPosition() != null) {
			this.position = PointUtil.StringPointToList(alarm.getPosition());
			
			// 根据告警点坐标计算出照片四个顶点的位置
			this.picPosition = new ArrayList<List<Double>>();
			
			List<Double> p1 = new ArrayList<Double>();
			List<Double> p2 = new ArrayList<Double>();
			List<Double> p3 = new ArrayList<Double>();
			List<Double> p4 = new ArrayList<Double>();
			p1 = GetLonLat.computerThatLonLat(this.position.get(0), this.position.get(1), 60.0, 150.0);
			p2 = GetLonLat.computerThatLonLat(this.position.get(0), this.position.get(1), 120.0, 150.0);
			p3 = GetLonLat.computerThatLonLat(this.position.get(0), this.position.get(1), 240.0, 150.0);
			p4 = GetLonLat.computerThatLonLat(this.position.get(0), this.position.get(1), 300.0, 150.0);
			this.picPosition.add(p1);
			this.picPosition.add(p2);
			this.picPosition.add(p3);
			this.picPosition.add(p4);
			this.picPosition.add(p1);
//			System.out.println("this.position: " + this.position);
//			System.out.println("this.picPosition.add(p1): " + this.picPosition.add(p1));
//			System.out.println("this.picPosition: " + this.picPosition);
//			System.out.println("得到照片四个点坐标");
		}
		if (alarm.getCreatetime() != null) {
			this.createTime = DateKit.dateFormat(alarm.getCreatetime(), "yyyy/MM/dd HH:mm:ss");
		}
		if (alarm.getUpdatetime() != null) {
			this.updateTime = DateKit.dateFormat(alarm.getUpdatetime(), "yyyy/MM/dd HH:mm:ss");
		}
		if (alarm.getInfoname() != null) {
			this.infoName = alarm.getInfoname();
		}
	}

	public List<Double> getPosition() {
		return position;
	}

	public void setPosition(List<Double> position) {
		this.position = position;
	}

	public List<List<Double>> getPicPosition() {
		return picPosition;
	}

	public void setPicPosition(List<List<Double>> picPosition) {
		this.picPosition = picPosition;
	}

	public void setUav(Uav uav) {
		if (uav.getName() != null) {
			this.uavName = uav.getName();
		}
		if (uav.getDeviceid() != null) {
			this.uavDeviceId = uav.getDeviceid();
		}
	}

	public String getUserCreatorName() {
		return userCreatorName;
	}

	public void setUserCreatorName(String userCreatorName) {
		this.userCreatorName = userCreatorName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescripte() {
		return descripte;
	}

	public void setDescripte(String descripte) {
		this.descripte = descripte;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getFlyingPathName() {
		return flyingPathName;
	}

	public void setFlyingPathName(String flyingPathName) {
		this.flyingPathName = flyingPathName;
	}

	public String getUserAName() {
		return userAName;
	}

	public void setUserAName(String userAName) {
		this.userAName = userAName;
	}

	public String getUserZName() {
		return userZName;
	}

	public void setUserZName(String userZName) {
		this.userZName = userZName;
	}

	public String getUavName() {
		return uavName;
	}

	public void setUavName(String uavName) {
		this.uavName = uavName;
	}

	public String getUavDeviceId() {
		return uavDeviceId;
	}

	public void setUavDeviceId(String uavDeviceId) {
		this.uavDeviceId = uavDeviceId;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
}
