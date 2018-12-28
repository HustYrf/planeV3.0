package hust.plane.utils;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.utils.pojo.RouteExcel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hujunhui
 * @date 2018/12/9 11:53
 */
public class TxtReaderUtil {

    public static boolean readFlyingPathFromTxt(File f, FlyingPath flyingPath) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(f);
            InputStreamReader reader = null; // 建立一个输入流对象reader
            try {
                reader = new InputStreamReader(fileInputStream, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                System.out.println("出现乱码");
                e.printStackTrace();
                return false;
            }
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            List<RouteExcel> list = new ArrayList<RouteExcel>();

            StringBuilder heightdata = new StringBuilder();
            StringBuilder paramOneList = new StringBuilder();
            StringBuilder paramTwoList = new StringBuilder();
            StringBuilder pointTypeList = new StringBuilder();

            String line = "";
            try {
                line = br.readLine();
                if (line == null || line.trim().length() == 0) {
                    //如果飞行路径名为空则出错
                    return false;
                }
                //设定飞行路径名称  这里的中文为啥是乱码呢？？
                flyingPath.setName(line);
                while ((line = br.readLine()) != null) {
                    if (line.length() == 0) {
                        continue;
                    }

                    String[] subline = line.split("\\s+");
                    if (subline.length < 11) {
                        return false;
                    }
                    RouteExcel routeExcel = new RouteExcel();

                    Double Latitude = 0.0;
                    try {
                        Latitude = Double.parseDouble(subline[8]);
                    } catch (Exception e) {
                        return false;
                    }
                    routeExcel.setLatitude(Latitude);
                    Double Longitude = 0.0;
                    try {
                        Longitude = Double.parseDouble(subline[9]);
                    } catch (Exception e) {
                        return false;
                    }
                    routeExcel.setLongitude(Longitude);

                    pointTypeList.append(subline[3]).append(',');
                    paramOneList.append(subline[4]).append(',');
                    paramTwoList.append(subline[5]).append(',');
                    heightdata.append(subline[10]).append(',');
                    list.add(routeExcel);
                }
                //去掉最后的一个逗号
                pointTypeList.deleteCharAt(pointTypeList.length() - 1);
                paramOneList.deleteCharAt(paramOneList.length() - 1);
                paramTwoList.deleteCharAt(paramTwoList.length() - 1);
                heightdata.deleteCharAt(heightdata.length() - 1);

                flyingPath.setPathdata(LineUtil.ListToString(list));
                flyingPath.setPointType(pointTypeList.toString());
                flyingPath.setParamOne(paramOneList.toString());
                flyingPath.setParamTwo(paramTwoList.toString());
                flyingPath.setHeightdata(heightdata.toString());

                br.close();
                reader.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

//    public static void main(String[] args) throws FileNotFoundException {
//
//        File file = new File("D:\\航线112701.txt");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        InputStreamReader reader = new InputStreamReader(fileInputStream); // 建立一个输入流对象reader
//        BufferedReader br = new BufferedReader(reader);
//
//        String line = "";
//        try {
//            while ((line = br.readLine()) != null) {
////                if ( line.length() == 0 ) {
////                    System.out.println("@@@") ;
////                    continue;
////                }
//                String[] subline = line.split("\\s+");
//                System.out.println(line + "**"  + line.length()) ;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
