package hust.plane.web.controller.vo;

import java.util.ArrayList;


import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.utils.DateKit;
import hust.plane.utils.LineUtil;

public class FlyingPathVO {

	private Integer id;

	private Integer airportZ;

	private Integer airportA;

	private String name;

	private ArrayList<ArrayList<Double>> pathdata;

	private String description;

	private String createtime;

	private String updatetime;

	private ArrayList<Double> heightdata;

	public FlyingPathVO(FlyingPath flyingPath) {

		this.id = flyingPath.getId();
		if (flyingPath.getPathdata() != null) {
			this.pathdata = LineUtil.stringLineToList(flyingPath.getPathdata());
		}
		if (flyingPath.getHeightdata() != null) {
			this.heightdata = LineUtil.stringpointToList(flyingPath.getHeightdata());
		}
		if (flyingPath.getDescription() != null) {
			this.description = flyingPath.getDescription();
		}
		if (flyingPath.getCreatetime() != null) {
			this.createtime = DateKit.dateFormat(flyingPath.getCreatetime(), "yyyy/MM/dd HH:mm:ss");
		}
		if (flyingPath.getUpdatetime() != null) {
			this.updatetime = DateKit.dateFormat(flyingPath.getUpdatetime(), "yyyy/MM/dd HH:mm:ss");
		}
		if(flyingPath.getName()!=null) {
			this.name = flyingPath.getName();
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAirportZ() {
		return airportZ;
	}

	public void setAirportZ(Integer airportZ) {
		this.airportZ = airportZ;
	}

	public Integer getAirportA() {
		return airportA;
	}

	public void setAirportA(Integer airportA) {
		this.airportA = airportA;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ArrayList<Double>> getPathdata() {
		return pathdata;
	}

	public void setPathdata(ArrayList<ArrayList<Double>> pathdata) {
		this.pathdata = pathdata;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public ArrayList<Double> getHeightdata() {
		return heightdata;
	}

	public void setHeightdata(ArrayList<Double> heightdata) {
		this.heightdata = heightdata;
	}

}
