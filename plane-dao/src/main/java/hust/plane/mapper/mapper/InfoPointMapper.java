package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.InfoPoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoPointMapper {

    List<InfoPoint> getNearPointByGeohash(@Param("geohashs")List<String> geohashs);

    int insertInfoPointList(@Param("infoPoints")List<InfoPoint> infoPoints);

}