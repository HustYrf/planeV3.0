package hust.plane.utils.page;

import hust.plane.mapper.pojo.Task;

public class TaskPojo {

    private Task task;

    private String userCreatorName;

    private String userAName;

    private String userZName;

    private String uavName;

    private String deviceId;

    private String flyingPathName;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUavName() {
        return uavName;
    }

    public void setUavName(String uavName) {
        this.uavName = uavName;
    }

    public String getFlyingPathName() {
        return flyingPathName;
    }

    public void setFlyingPathName(String flyingPathName) {
        this.flyingPathName = flyingPathName;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getUserCreatorName() {
        return userCreatorName;
    }

    public void setUserCreatorName(String userCreatorName) {
        this.userCreatorName = userCreatorName;
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


}
