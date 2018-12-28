package hust.plane.mapper.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FlyingPath {

    private Integer id;

    private Integer airportZ;

    private Integer airportA;

    private String name;

    private String pathdata;

    private String description;

    private String paramOne;

    private String paramTwo;

    private String pointType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private String heightdata;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAirportZ() {
        return airportZ;
    }

    public void setAirportZ(Integer airportZ) {
        this.airportZ = airportZ;
    }

    public Integer getAirportA() {
        return airportA;
    }

    public void setAirportA(Integer airportA) {
        this.airportA = airportA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPathdata() {
        return pathdata;
    }

    public void setPathdata(String pathdata) {
        this.pathdata = pathdata;
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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getHeightdata() {
        return heightdata;
    }

    public void setHeightdata(String heightdata) {
        this.heightdata = heightdata == null ? null : heightdata.trim();
    }

    public String getParamOne() {
        return paramOne;
    }

    public void setParamOne(String paramOne) {
        this.paramOne = paramOne == null ? null : paramOne.trim();
    }

    public String getParamTwo() {
        return paramTwo;
    }

    public void setParamTwo(String paramTwo) {
        this.paramTwo = paramTwo == null ? null : paramTwo.trim();
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType == null ? null : pointType.trim();

    }
}