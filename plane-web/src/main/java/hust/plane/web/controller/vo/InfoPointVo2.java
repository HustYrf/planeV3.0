package hust.plane.web.controller.vo;

import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.utils.PointUtil;

import java.util.List;

/**
 * @ClassName InfoPointVo2
 * @Descripition TODO
 * @Author Administrator
 * @Date 2018/12/28 14:36
 **/
public class InfoPointVo2 {

    private List<Double> lnglat;
    private String name;
    private int style;

    public InfoPointVo2(InfoPoint infoPoint,int style) {
        if (infoPoint.getPosition() != null) {
            this.lnglat = PointUtil.StringPointToList(infoPoint.getPosition());
        }
        if (infoPoint.getName() != null) {
            this.name = infoPoint.getName();
        }
        if (Integer.valueOf(style) != null) {
            this.style = style;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getLnglat() {
        return lnglat;
    }

    public void setLnglat(List<Double> lnglat) {
        this.lnglat = lnglat;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
