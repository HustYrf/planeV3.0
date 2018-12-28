package hust.plane.utils.page;

import hust.plane.mapper.pojo.Alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmPojo {
    private Integer id;
    private Integer taskId;
    private String imageurl;
    private String position;
    private String description;
    private String createtime;
    private String updatetime;
    private Integer status;
    private String infoname;
    private Integer routeId;
    private List<Double> positionList;


    public AlarmPojo(Alarm alarm) {

        this.id = alarm.getId();
        if (alarm.getTaskId() != null) {
            this.taskId = alarm.getTaskId();
        }
        if (alarm.getImageurl() != null) {
            this.imageurl = alarm.getImageurl();
        }
        if (alarm.getDescription() != null) {
            this.description = alarm.getDescription();
        }
        if (alarm.getCreatetime() != null) {
            this.createtime = dateFormat(alarm.getCreatetime(), "yyyy/MM/dd HH:mm:ss");
        }
        if (alarm.getUpdatetime() != null) {
            this.updatetime = dateFormat(alarm.getUpdatetime(), "yyyy/MM/dd HH:mm:ss");

        }
        if (alarm.getPosition() != null) {
            this.positionList = StringPointToList(alarm.getPosition());
            this.position = pointToString(StringPointToList(alarm.getPosition()));
        }
        if (alarm.getStatus() != null) {
            this.status = alarm.getStatus();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfoname() {
        return infoname;
    }

    public void setInfoname(String infoname) {
        this.infoname = infoname;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public List<Double> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Double> positionList) {
        this.positionList = positionList;
    }

    public static List<Double> StringPointToList(String s) {
        List<Double> list = new ArrayList<Double>();
        String sub = s.substring(6, s.length() - 1);
        double x = Double.parseDouble(sub.split(" ")[0]);
        double y = Double.parseDouble(sub.split(" ")[1]);
        list.add(x);
        list.add(y);
        return list;
    }

    /**
     * @param [pointList]
     * @return java.lang.String
     * @author rfYang
     * @date 2018/7/5 16:46
     */
    public static String pointToString(List<Double> pointList) {
        return String.valueOf(pointList.get(0)) + "," + String.valueOf(pointList.get(1));
    }

    /**
     * 时间转换
     *
     * @param [date, dateFormat]
     * @return java.lang.String
     * @author rfYang
     * @date 2018/7/6 13:51
     */
    public static String dateFormat(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            if (date != null) {
                return format.format(date);
            }
        }

        return "";
    }
}
