package hust.plane.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import hust.plane.mapper.pojo.Alarm;
import hust.plane.utils.pojo.ImgPicToAlarm;
import hust.plane.utils.pojo.RouteExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImgUtils {

    /**
     * 读取照片里面的信息
     */
    public static Alarm printImageTags(File file, int taskid) throws ImageProcessingException, Exception {
        Alarm alarm = new Alarm();
        alarm.setUpdatetime(new Date());
        alarm.setStatus(0);//未处理告警
        alarm.setTaskId(taskid);
        alarm.setImageurl(file.getName());

        ImgPicToAlarm imgPicToAlarm = new ImgPicToAlarm();
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        if (file.exists()) {
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
            }
        } else {
//            System.out.println("文件不存在！！");
        }
        alarm.setPosition(imgPicToAlarm.setLongLatitude(imgPicToAlarm.getLongitude(), imgPicToAlarm.getLatitude()));
        System.out.println(alarm.toString());
        return alarm;
    }

    public static List<Alarm> processlcoaldir(int taskid, String alarmDir) {

        String localfiledir = alarmDir;

        List<Alarm> alarmList = new ArrayList<Alarm>();
        File file = new File(localfiledir);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    // System.out.println("文件夹:" + file2.getAbsolutePath() + "跳过");
                } else {
                    //System.out.println("文件:" + file2.getAbsolutePath() + "处理");

                    Alarm alarm = new Alarm();
                    alarm.setUpdatetime(new Date());
                    alarm.setStatus(1);//未处理告警
                    alarm.setTaskId(taskid);
                    alarm.setImageurl(file2.getName());

                    try {
                        Metadata metadata = ImageMetadataReader.readMetadata(file2);
                        for (Directory directory : metadata.getDirectories()) {
                            for (Tag tag : directory.getTags()) {
                                String tagName = tag.getTagName();  //标签名
                                String desc = tag.getDescription(); //标签信息
                                if (tagName.equals("Date/Time Original")) {
                                    alarm.setCreatetime(DateKit.stringToDate(desc));
                                }
                            }
                        }

                        byte[] imgbyte = image2Bytes(file2);
                        int length = imgbyte.length;
                        byte[] position = new byte[12];
                        for (int i = 0; i < 12; i++) {
                            position[i] = imgbyte[length - 12 + i];
                        }

                        float lat = getFloat(position, 0);
                        float lon = getFloat(position, 4);
                        float elv = getFloat(position, 8);

                        RouteExcel routeExcel = new RouteExcel();

                        routeExcel.setLatitude((double) lat);
                        routeExcel.setLatitude((double) lon);

                        alarm.setPosition(routeExcel.getPositon());
                        alarm.setDescription("这是一张无人机从" + elv + " 高度拍摄告警照片！");

                        //在这里匹配最近的信息点
                        // String geohash = GeohashUtil.getGeoHashBase32(routeExcel);

                        List<String> geohash9area = GeohashUtil.getGeoHashBase32For9(routeExcel);


                        alarmList.add(alarm);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return alarmList;
    }

//    public static void main(String[] args) {
//
//        float te = 5.920667E-30f;
//       byte[] test = float2byte(te);
//       System.out.println("%" + Arrays.toString(test) + "%");
//        processlcoaldir(1,"");
//
//    }

    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }
        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }
        return dest;
    }


    public static float getFloat(byte[] b, int index) {

        byte sndlen[] = new byte[4];
        sndlen[3] = b[index + 3];
        sndlen[2] = b[index + 2];
        sndlen[1] = b[index + 1];
        sndlen[0] = b[index + 0];
        long data = (long) (Byte2Int(sndlen) & 0x0FFFFFFFFl);
        //获取长度
        return data / 10000000.0f;       //把float字节码转换成float
    }

    public static int Byte2Int(byte[] bytes) {

        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);
    }

    static void buff2Image(byte[] b, String tagSrc) throws Exception {
        FileOutputStream fout = new FileOutputStream(tagSrc);
        //将字节写入文件
        fout.write(b);
        fout.close();
    }

    static public byte[] image2Bytes(File imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(imgSrc);
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
        return bytes;
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
