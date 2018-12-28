package hust.plane.web.controller;

import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.service.interFace.InfoPointService;
import hust.plane.utils.JsonUtils;
import hust.plane.web.controller.vo.InfoPointVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hujunhui
 * @date 2018/12/23 15:32
 */
@Controller
public class InfoPointController {

    @Autowired
    private InfoPointService infoPointServiceImpl;


    //返回所有的信息点
    @RequestMapping(value = "showInfoPoint", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String showInfoPoint() {

        //查询所有的信息点
        List<InfoPoint> infoPoints = infoPointServiceImpl.getAllInfoPoint();
        List<InfoPointVO> infoPointVOList = new ArrayList<>();
        Iterator<InfoPoint> infoPointIterator = infoPoints.iterator();
        while (infoPointIterator.hasNext()) {
            InfoPointVO infoPointVO = new InfoPointVO(infoPointIterator.next());
            infoPointVOList.add(infoPointVO);
        }

        return JsonUtils.objectToJson(infoPointVOList);
    }

}
