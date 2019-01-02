package hust.plane.utils.page;

import hust.plane.mapper.pojo.User;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserPojo {
    private int id;

    private String name;

    private String password;

    private String nickname;

    private String worknumber;//工号

    private String email;

    private String updatetime;

    private Integer departmentId;

    private String departmentName;//部门

    private String phoneone;

    private String phonetwo;

    private String icon;

    private Integer tasknum;

    private String description;

    private String position;//职位

    private int historyzoom;

    public UserPojo(User user,String description) {
        this.id = user.getId();
        if (StringUtils.isNotBlank(user.getName())) {
            this.name = user.getName();
        }
        if (StringUtils.isNotBlank(user.getPhoneone())) {
            this.phoneone = user.getPhoneone();
        } else {
            this.phoneone = user.getPhonetwo();
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            this.email = user.getEmail();
        }
        if (user.getUpdatetime() != null) {
            this.updatetime = dateFormat(user.getUpdatetime(), "yyyy年MM月dd日 HH:mm:ss");
        }
        if (user.getDepartmentId() != null) {
            this.departmentId = user.getDepartmentId();
        }
        if (StringUtils.isNotBlank(user.getWorknumber())) {
            this.worknumber = user.getWorknumber();
        }
        if(StringUtils.isNotBlank(description)){
            this.departmentName=description;
        }
    }

    public static String dateFormat(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            if (date != null) {
                return format.format(date);
            }
        }

        return "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWorknumber() {
        return worknumber;
    }

    public void setWorknumber(String worknumber) {
        this.worknumber = worknumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHistoryzoom() {
        return historyzoom;
    }

    public void setHistoryzoom(int historyzoom) {
        this.historyzoom = historyzoom;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
