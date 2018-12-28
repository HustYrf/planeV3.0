package hust.plane.utils.pojo;

public class ImgPicToAlarm {
    private String longitude;
    private String latitude;

    public String setLongLatitude(String longitude, String latitude) {
        return "POINT(" + longitude + " " + latitude + ")";
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
