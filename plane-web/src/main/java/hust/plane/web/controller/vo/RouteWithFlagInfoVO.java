package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.Route;
import hust.plane.utils.LineUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hujunhui
 * @date 2018/10/20 10:50
 */
public class RouteWithFlagInfoVO {

    private Integer id;

    private String name;

    private List<ArrayList<Double>> routepathdata;

    private List<String> flagdata;

    //初始化
    public RouteWithFlagInfoVO(Route route) {

        this.id = route.getId();

        if (route.getName() != null) {
            this.name = route.getName();
        }

        if (route.getRoutepathdata() != null) {
            this.routepathdata = LineUtil.stringLineToList(route.getRoutepathdata());
        }
        if (route.getFlagdata() != null) {
            this.flagdata = LineUtil.stringflaginfoToList(route.getFlagdata());
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

    public List<ArrayList<Double>> getRoutepathdata() {
        return routepathdata;
    }

    public void setRoutepathdata(List<ArrayList<Double>> routepathdata) {
        this.routepathdata = routepathdata;
    }

    public List<String> getFlagdata() {
        return flagdata;
    }

    public void setFlagdata(List<String> flagdata) {
        this.flagdata = flagdata;
    }


}
