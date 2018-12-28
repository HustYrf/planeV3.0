package hust.plane.mapper.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Route {
    private Integer id;

    private String name;

    private Integer type;

    private String routepathdata;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private String flagdata;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRoutepathdata() {
        return routepathdata;
    }

    public void setRoutepathdata(String routepathdata) {
        this.routepathdata = routepathdata;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFlagdata() {
        return flagdata;
    }

    public void setFlagdata(String flagdata) {
        this.flagdata = flagdata == null ? null : flagdata.trim();
    }
}