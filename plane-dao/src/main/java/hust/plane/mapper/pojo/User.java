package hust.plane.mapper.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {

    private Integer id;

    private String name;

    private String password;

    private String nickname;

    private String worknumber;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private Integer departmentId;

    private String phoneone;

    private String phonetwo;

    private String icon;

    private Integer tasknum;

    private String description;

    private String historypoint;

    private int historyzoom;

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
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getWorknumber() {
        return worknumber;
    }

    public void setWorknumber(String worknumber) {
        this.worknumber = worknumber == null ? null : worknumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhoneone() {
        return phoneone;
    }

    public void setPhoneone(String phoneone) {
        this.phoneone = phoneone == null ? null : phoneone.trim();
    }

    public String getPhonetwo() {
        return phonetwo;
    }

    public void setPhonetwo(String phonetwo) {
        this.phonetwo = phonetwo == null ? null : phonetwo.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getTasknum() {
        return tasknum;
    }

    public void setTasknum(Integer tasknum) {
        this.tasknum = tasknum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getHistorypoint() {
        return historypoint;
    }

    public void setHistorypoint(String historypoint) {
        this.historypoint = historypoint;
    }

    public int getHistoryzoom() {
        return historyzoom;
    }

    public void setHistoryzoom(int historyzoom) {
        this.historyzoom = historyzoom;
    }
}