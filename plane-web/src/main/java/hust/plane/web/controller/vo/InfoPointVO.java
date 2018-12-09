package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.utils.PointUtil;

import java.util.List;

/**
 * @author hujunhui
 * @date 2018/12/9 9:43
 */
public class InfoPointVO {

    private Integer id;

    private String name;

    private List<Double> position;

    private Float altitude;

    private Integer routeId;

    private String routeName;

    //初始化
    public  InfoPointVO(InfoPoint infoPoint){

        this.id =  infoPoint.getId();
        if(infoPoint.getName()!=null){
            this.name = infoPoint.getName();
        }
        if(infoPoint.getAltitude()!=null){
            this.altitude = infoPoint.getAltitude();
        }
        if(infoPoint.getRouteId()!=null){
            this.routeId = infoPoint.getRouteId();
        }
        if(this.getRouteName()!=null){
            this.routeName = infoPoint.getRouteName();
        }
        if(this.getPosition()!=null){
            this.position = PointUtil.StringPointToList(infoPoint.getPosition());
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

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public Float getAltitude() {
        return altitude;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
