package hust.plane.utils;

import com.drew.imaging.jpeg.JpegProcessingException;

import hust.plane.constant.WebConst;
import hust.plane.mapper.pojo.Alarm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImgTest {
    public static void main(String[] args) throws JpegProcessingException, IOException {
        /*List<Alarm> alarmList = new ArrayList<>();
        try {
           alarmList=ImgUtils.alarmList(WebConst.ALARM_PIC_PATH,"2");
           System.out.println(alarmList);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


      /*  File img = new File("D:/100MEDIA/DJI_0001.JPG");
        System.out.println("File Name:" + img.getName());

        Metadata metadata = JpegMetadataReader.readMetadata(img);
        System.out.println("Directory Count: "+metadata.getDirectoryCount());
        System.out.println();

        //输出所有附加属性数据
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("******\t" + directory.getName() + "\t******");
            for (Tag tag : directory.getTags()) {
                System.out.println(tag.getTagName() + ":" + tag.getDescription());
            }
            System.out.println();
            System.out.println();
        }
*/
    }

}
