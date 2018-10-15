package hust.plane.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import hust.plane.mapper.pojo.Alarm;
import hust.plane.utils.pojo.ImgPicToAlarm;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ImgUtils {
 /*   public static List<Alarm> alarmList(String path,String planeId) throws Exception {
        File file = new File(path);
        List<Alarm> alarmList = new ArrayList<>(file.listFiles().length + 2);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File img : files) {
                File fileIMG = img;
                alarmList.add(printImageTags(fileIMG,planeId));
            }
        }
        return alarmList;
    }*/

    /**
     * 读取照片里面的信息
     */
    public static Alarm printImageTags(File file,int taskid) throws ImageProcessingException, Exception {
        Alarm alarm = new Alarm();
        alarm.setUpdatetime(new Date());
        alarm.setStatus(0);//未处理告警
        alarm.setTaskId(taskid);
        alarm.setImageurl(file.getName());
        
        ImgPicToAlarm imgPicToAlarm = new ImgPicToAlarm();
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        if(file.exists()) {
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();  //标签名
                String desc = tag.getDescription(); //标签信息
                if (tagName.equals("GPS Altitude")) {
                    alarm.setDescription("这是一张无人机从" + desc + " 高度拍摄告警的照片！");
                }
                if (tagName.equals("Date/Time Original")) {
                    alarm.setCreatetime(DateKit.stringToDate(desc));
                }
                if (tagName.equals("GPS Latitude")) {
                    imgPicToAlarm.setLatitude(pointToLatlong(desc));
                }
                if (tagName.equals("GPS Longitude")) {
                    imgPicToAlarm.setLongitude(pointToLatlong(desc));
                }
            }
        }}else {
        	System.out.println("文件不存在！！");
        }
        alarm.setPosition(imgPicToAlarm.setLongLatitude(imgPicToAlarm.getLongitude(), imgPicToAlarm.getLatitude()));
        System.out.println(alarm.toString());
        return alarm;
    }

    public static void processlcoaldir(int taskid){
        String localfiledir = "F:\\广西电信-无人机通信\\数据\\pic\\航拍样例2";
       // String localfiledir =  "/home/gxdx_ai/file-workspace/ImageTask/" + taskid + "/ImageAlarm";

        List<Alarm> alarmList = new ArrayList<Alarm>();
        File file = new File(localfiledir);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath() + "跳过");
                } else {
                    System.out.println("文件:" + file2.getAbsolutePath() + "处理");
                    //处理如下
                    try {
                        Alarm alarm =  printImageTags(file2,taskid);
                        alarmList.add(alarm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }

        System.out.println(alarmList.toString());

    }

    public static void main(String[] args){

        processlcoaldir(1);
    }

    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     *
     * @param point 坐标点
     * @return
     */
    public static String pointToLatlong(String point) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60;
        return duStr.toString();
    }
}
