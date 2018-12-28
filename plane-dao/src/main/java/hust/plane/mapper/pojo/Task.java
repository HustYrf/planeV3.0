package hust.plane.mapper.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Task {
    private Integer id;

    private String name;

    private Integer usercreator;

    private Integer userA;

    private Integer userZ;

    private Integer flyingpathId;

    private String missionId;

    private Integer uavId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planstarttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planendtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date executestarttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date executeendtime;

    private Integer status;

    private Integer finishstatus;

    private String imgfolder;

    private String reporturl;

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
        this.imgfolder = imgfolder == null ? null : imgfolder.trim();
    }

    public String getReporturl() {
        return reporturl;
    }

    public void setReporturl(String reporturl) {
        this.reporturl = reporturl == null ? null : reporturl.trim();
    }


    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

}