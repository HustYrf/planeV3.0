package hust.plane.service.impl;

import hust.plane.mapper.mapper.InfoPointMapper;
import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.service.interFace.InfoPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hujunhui
 * @date 2018/12/1 12:58
 */
@Service
public class InfoPointServiceImpl implements InfoPointService {

    @Autowired
    private InfoPointMapper infoPointMapper;

    @Override
    public List<InfoPoint> getAllInfoPoint() {
        return infoPointMapper.selectAllInfoPoint();
    }
}
