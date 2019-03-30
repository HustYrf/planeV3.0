package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.utils.DateKit;
import hust.plane.utils.LineUtil;
import hust.plane.utils.PositionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import com.mysql.fabric.xmlrpc.base.Array;

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
		if (flyingPath.getName() != null) {
			this.name = flyingPath.getName();
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
		// 对类型为178和206的数据需要进行处理，不作为航线落图的数据，但是仍需要作为航线数据进行下发。
		if (flyingPath.getPathdata() != null && flyingPath.getPointType() != null) {

			this.pathdata = new ArrayList<ArrayList<Double>>();
			String sub = flyingPath.getPathdata().substring(11, flyingPath.getPathdata().length() - 1);
			String flyingPointDataList[] = sub.split(",");
//            String pointType[] = flyingPath.getPointType().split(",");
	
			for (int i = 0; i < flyingPointDataList.length; i++) {
//              if (pointType[i].equals("22") || pointType[i].equals("20") || pointType[i].equals("178") || pointType[i].equals("206") || pointType[i].equals("189") || pointType[i].equals("208")) {
//              continue;
//          }
//          此处增加22号航点过滤
				// 直接过滤坐标点为00的数据,考虑到系统暂时只会在中国内运行,可以直接这样做
				if (flyingPointDataList[i].equals("0 0")) {
					continue;
				}
				ArrayList<Double> point = new ArrayList<Double>();
				/*
				 * 根据gps84ToGcj02的纠偏算法来纠偏
				 */
				Double lon = Double.parseDouble(flyingPointDataList[i].split(" ")[0]);
				Double lat = Double.parseDouble(flyingPointDataList[i].split(" ")[1]);
				double[] point1 = PositionUtil.gps84_To_Gcj02(lat, lon);
//				System.out.println("wgs84坐标系的坐标为:"+lat+","+lon);
//				System.out.println("纠偏之后的坐标为:"+Arrays.toString(point1));
				// 经度纠偏
				point.add(point1[1]);
				// 纬度纠偏
				point.add(point1[0]);
				this.pathdata.add(point);

			}
		} else if (flyingPath.getPathdata() != null && flyingPath.getPointType() == null) {
			// 如果航点类型为空，则全部都显示
			this.pathdata = new ArrayList<ArrayList<Double>>();
			String sub = flyingPath.getPathdata().substring(11, flyingPath.getPathdata().length() - 1);
			String slist[] = sub.split(",");
			for (int i = 0; i < slist.length; i++) {
				ArrayList<Double> point = new ArrayList<Double>();
				point.add(Double.parseDouble(slist[i].split(" ")[0]) + 0.000637);
				point.add(Double.parseDouble(slist[i].split(" ")[1]) - 0.001596);
				this.pathdata.add(point);
			}

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