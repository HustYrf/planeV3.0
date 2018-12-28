package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.mapper.pojo.Uav;
import hust.plane.utils.DateKit;
import hust.plane.utils.PointUtil;

import java.util.List;

public class AlarmDetailVO {
    //用于展示告警详情
    private int id;
    private String image;
    private String descripte;
    private String createTime;
    private String updateTime;


    private String infoName;

    private List<Double> position;
    private String taskName;
    private String flyingPathName;

    private String userCreatorName;
    private String userAName;

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

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
