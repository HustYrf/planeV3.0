package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Route;
import hust.plane.utils.DateKit;
import hust.plane.utils.LineUtil;

import java.util.ArrayList;
import java.util.List;

public class RouteVO {

    private Integer id;

    private String name;

    private Integer type;

    private List<ArrayList<Double>> routepathdata;

    private String description;

    private String createtime;

    private String flagdata;

    public RouteVO(Route route) {

        this.id = route.getId();

        if (route.getType() != null) {
            this.type = route.getType();
        }
        if (route.getName() != null) {
            this.name = route.getName();
        }

        if (route.getRoutepathdata() != null) {

            this.routepathdata = LineUtil.stringLineToList(route.getRoutepathdata());
        }
        if (route.getDescription() != null) {
            this.description = route.getDescription();
        }

        if (route.getCreatetime() != null) {
            this.createtime = DateKit.dateFormat(route.getCreatetime(), "yyyy/MM/dd HH:mm:ss");
        }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<ArrayList<Double>> getRoutepathdata() {
        return routepathdata;
    }

    public void setRoutepathdata(List<ArrayList<Double>> routepathdata) {
        this.routepathdata = routepathdata;
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

    public String getFlagdata() {
        return flagdata;
    }

    public void setFlagdata(String flagdata) {
        this.flagdata = flagdata;
    }

}
