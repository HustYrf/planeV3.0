package hust.plane.web.controller;

import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.service.interFace.InfoPointService;
import hust.plane.utils.JsonUtils;
import hust.plane.web.controller.vo.InfoPointVO;
import hust.plane.web.controller.vo.InfoPointVo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
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

//    //存储所有的信息点到C:\\Users\\Administrator\\Desktop\\info.txt文件中
//    @RequestMapping(value = "showInfoPoint", method = RequestMethod.GET)
//    public void storageInfoPoint() {
//        //查询所有的信息点
//        List<InfoPoint> infoPoints = infoPointServiceImpl.getAllInfoPoint();
//        List<InfoPointVo2> infoPointVOList2 = new ArrayList<>();
//        Iterator<InfoPoint> infoPointIterator = infoPoints.iterator();
//        while (infoPointIterator.hasNext()) {
//            InfoPointVo2 infoPointVO = new InfoPointVo2(infoPointIterator.next(), 2);
//            infoPointVOList2.add(infoPointVO);
//        }
//        System.out.println("===========================");
//        System.out.println(JsonUtils.objectToJson(infoPointVOList2));
//        System.out.println("===========================");
//        try {
//            File file = new File("C:\\Users\\Administrator\\Desktop\\info.txt");
//            PrintStream ps = new PrintStream(new FileOutputStream(file));
//            ps.println("var city = " + JsonUtils.objectToJson(infoPointVOList2));// 往文件里写入字符串
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
