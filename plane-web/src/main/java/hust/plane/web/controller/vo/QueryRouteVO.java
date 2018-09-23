package hust.plane.web.controller.vo;

public class QueryRouteVO {
    private String routeid;
    private String type;

    public QueryRouteVO() {
    }

    public QueryRouteVO(String routeid, String type) {
        this.routeid = routeid;
        this.type = type;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
