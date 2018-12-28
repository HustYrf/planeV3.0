package hust.plane.utils.pojo;

public class RouteExcel {
    private Double id;
    private Double longitude;
    private Double latitude;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPositon() {
        return "POINT(" + String.valueOf(longitude) + " " + String.valueOf(latitude) + ")";
    }
}
