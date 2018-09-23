package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.utils.DateKit;
import hust.plane.utils.PointUtil;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class AlarmVO {
	
	/*private int id;
	private int taskId;
	private String imageurl;
	private List<Double> position;
	private String description;
	private Date createtime;
	private Date updatetime;
	private int status;
	private String infoname;
	private int routeId;*/
	

	private int id;
	private String image;
	private String descripte;
	private String createTime;
	private String updateTime;
	private String alongda;
	private List<Double> positionList;

	
	
	public AlarmVO(Alarm alarm) {

		this.id = alarm.getId();
		if (alarm.getImageurl() != null) {
			this.image = alarm.getImageurl();
		}

		if (alarm.getDescription() != null) {
			this.descripte = alarm.getDescription();
		}
		if (alarm.getCreatetime() != null) {
			this.createTime = DateKit.dateFormat(alarm.getCreatetime(), "yyyy/MM/dd HH:mm:ss");
		}
		if (alarm.getUpdatetime() != null) {
			this.updateTime = alarm.getUpdatetime().toString();
		}
		if (alarm.getPosition() != null) {
			this.positionList = PointUtil.StringPointToList(alarm.getPosition());
			this.alongda = PointUtil.pointToString(PointUtil.StringPointToList(alarm.getPosition()));
		}
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

	public String getAlongda() {
		return alongda;
	}

	public void setAlongda(String alongda) {
		this.alongda = alongda;
	}

	public List<Double> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Double> positionList) {
		this.positionList = positionList;
	}

	public void setImgBaseCode(String webappRoot) {

		String imgFile = webappRoot + File.separator + image; // 获取数据库的数据
		// System.out.println(imgFile);
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		this.image = encoder.encode(data);

	}
}
