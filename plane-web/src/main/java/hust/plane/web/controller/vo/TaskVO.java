package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Task;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TaskVO {

    private String name;
    private String userAName;
    private String userZName;
    private Integer uavId;
    private String uavName;
    private Integer flyingpathId;
    private String flyingpathName;
    private String missionId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planstarttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planendtime;

    private Integer id;
    private Integer usercreator;
    private Integer userA;
    private Integer userZ;
    private String userCreatorName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date executestarttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date executeendtime;
    private Integer status;
    private Integer finishstatus;
    private String imgfolder;
    private String reporturl;

    public void setTaskVO(Task task) {

        this.id = task.getId();
        if (task.getName() != null) {
            this.name = task.getName();
        }
        if (task.getMissionId() != null) {
            this.missionId = task.getMissionId();
        }
        if (task.getUsercreator() != null) {
            this.usercreator = task.getUsercreator();
        }
        if (task.getUserA() != null) {
            this.userA = task.getUserA();
        }
        if (task.getUserZ() != null) {
            this.userZ = task.getUserZ();
        }
        if (task.getFlyingpathId() != null) {
            this.flyingpathId = task.getFlyingpathId();
        }
        if (task.getUavId() != null) {
            this.uavId = task.getUavId();
        }
        if (task.getPlanstarttime() != null) {
            this.planstarttime = task.getPlanstarttime();
        }
        if (task.getPlanendtime() != null) {
            this.planendtime = task.getPlanendtime();
        }
        if (task.getExecutestarttime() != null) {
            this.executestarttime = task.getExecutestarttime();
        }
        if (task.getExecuteendtime() != null) {
            this.executeendtime = task.getExecuteendtime();
        }
        if (task.getCreatetime() != null) {
            this.createtime = task.getCreatetime();
        }


    }

    public String getUavName() {
        return uavName;
    }

    public void setUavName(String uavName) {
        this.uavName = uavName;
    }

    public String getFlyingpathName() {
        return flyingpathName;
    }

    public void setFlyingpathName(String flyingpathName) {
        this.flyingpathName = flyingpathName;
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

    public String getUserCreatorName() {
        return userCreatorName;
    }

    public void setUserCreatorName(String userCreatorName) {
        this.userCreatorName = userCreatorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsercreator() {
        return usercreator;
    }

    public void setUsercreator(Integer usercreator) {
        this.usercreator = usercreator;
    }

    public Integer getUserA() {
        return userA;
    }

    public void setUserA(Integer userA) {
        this.userA = userA;
    }

    public Integer getUserZ() {
        return userZ;
    }

    public void setUserZ(Integer userZ) {
        this.userZ = userZ;
    }

    public Integer getFlyingpathId() {
        return flyingpathId;
    }

    public void setFlyingpathId(Integer flyingpathId) {
        this.flyingpathId = flyingpathId;
    }

    public Integer getUavId() {
        return uavId;
    }

    public void setUavId(Integer uavId) {
        this.uavId = uavId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getPlanstarttime() {
        return planstarttime;
    }

    public void setPlanstarttime(Date planstarttime) {
        this.planstarttime = planstarttime;
    }

    public Date getPlanendtime() {
        return planendtime;
    }

    public void setPlanendtime(Date planendtime) {
        this.planendtime = planendtime;
    }

    public Date getExecutestarttime() {
        return executestarttime;
    }

    public void setExecutestarttime(Date executestarttime) {
        this.executestarttime = executestarttime;
    }

    public Date getExecuteendtime() {
        return executeendtime;
    }

    public void setExecuteendtime(Date executeendtime) {
        this.executeendtime = executeendtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFinishstatus() {
        return finishstatus;
    }

    public void setFinishstatus(Integer finishstatus) {
        this.finishstatus = finishstatus;
    }

    public String getImgfolder() {
        return imgfolder;
    }

    public void setImgfolder(String imgfolder) {
        this.imgfolder = imgfolder;
    }

    public String getReporturl() {
        return reporturl;
    }

    public void setReporturl(String reporturl) {
        this.reporturl = reporturl;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }
}
