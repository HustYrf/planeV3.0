package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.utils.DateKit;
import hust.plane.utils.PointUtil;
import org.apache.commons.collections.ArrayStack;
import sun.misc.BASE64Encoder;
import sun.net.www.protocol.http.HttpURLConnection;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AlarmVO {

	private int id;
	private String image;
	private String descripte;
	private String createTime;
	private String updateTime;
	private String alongda;
	private List<Double> positionList;
	private String infoname;

	public AlarmVO(){}
	
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
		if(alarm.getInfoname()!=null){
			this.infoname = alarm.getInfoname();
		}
	}

	public String getInfoname() {
		return infoname;
	}

	public void setInfoname(String infoname) {
		this.infoname = infoname;
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

	public void setImgBaseCode() {
		try {
			//url = new URL("http://218.65.240.246:18089/ImageTask/24/ImageAlarm/alarm1.jpg");
			URL url = new URL(this.image);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			InputStream inputStream = connection.getInputStream();
			byte[] data = null;
			try {

				data = new byte[inputStream.available()];
				inputStream.read(data,0,inputStream.available());

				inputStream.close();         //关闭所有连接
				connection.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}
			BASE64Encoder encoder = new BASE64Encoder();
			this.image = encoder.encode(data);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBase(){

		try{
			URL url = new URL(this.image);
			//打开一个网络连接
			HttpURLConnection httpURL = (HttpURLConnection)url.openConnection();
			//设置网络连接超时时间
			httpURL.setConnectTimeout(3000);
			//设置应用程序要从网络连接读取数据
			httpURL.setDoInput(true);
			//设置请求方式
			httpURL.setRequestMethod("GET");
			//获取请求返回码
			int responseCode = httpURL.getResponseCode();
			InputStream ins;
			if(responseCode == 200){
				//如果响应为“200”，表示成功响应，则返回一个输入流
				ins = httpURL.getInputStream();
				byte[] data = new byte[10240000];
				byte[] temp = new byte[1024];
				int index = 0;
				int len = 0;
				while((len = ins.read(temp)) > 0){
					System.arraycopy(temp,0,data,index,len);
					index = index + len;
				}
				byte[] im = new byte[index];
				System.arraycopy(data,0,im,0,index);
				ins.close();
				BASE64Encoder encoder = new BASE64Encoder();
				this.image = encoder.encode(im);
			}
		}catch(Exception e){
				e.printStackTrace();
		}
	}


}
