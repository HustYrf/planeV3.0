package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Uav;
import hust.plane.utils.DateKit;
import hust.plane.utils.PointUtil;

import java.util.List;

public class UavVO {

    private Integer id;

    private String deviceid;

    private String name;

    private String password;

    private Integer status;

    private Integer owner;

    private List<Double> position;

    private Float height;

    private String description;

    private String createtime;

    private String updatetime;

    private String phoneone;

    private String phonetwo;

    public UavVO(Uav uav) {

        // 使用Uav初始化该对象

        this.id = uav.getId();

        if (uav.getDeviceid() != null) {
            this.deviceid = uav.getDeviceid();
        }
        if (uav.getName() != null) {
            this.name = uav.getName();
        }
        if (uav.getPassword() != null) {
            this.password = uav.getPassword();
        }
        if (uav.getOwner() != null) {
            this.owner = uav.getOwner();
        }
        if (uav.getStatus() != null) {
            this.status = uav.getStatus();
        }
        if (uav.getPosition() != null) {
            this.position = PointUtil.StringPointToList(uav.getPosition());
        }
        if (uav.getHeight() != null) {
            this.height = uav.getHeight();
        }
        if (uav.getDescription() != null) {
            this.description = uav.getDescription();
        }
        if (uav.getUpdatetime() != null) {
            this.updatetime = DateKit.dateFormat(uav.getUpdatetime(), "yyyy/MM/dd HH:mm:ss");
        }
        if (uav.getPhoneone() != null) {
            this.phoneone = uav.getPhoneone();
        }
        if (uav.getPhoneone() != null) {
            this.phonetwo = uav.getPhoneone();
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
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

    public String getPhoneone() {
        return phoneone;
    }

    public void setPhoneone(String phoneone) {
        this.phoneone = phoneone;
    }

    public String getPhonetwo() {
        return phonetwo;
    }

    public void setPhonetwo(String phonetwo) {
        this.phonetwo = phonetwo;
    }
}
