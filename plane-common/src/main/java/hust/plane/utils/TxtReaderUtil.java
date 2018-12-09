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
            InputStreamReader reader = new InputStreamReader(fileInputStream); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            List<RouteExcel> list = new ArrayList<RouteExcel>();
            List<String> heightdata = new ArrayList<String>();
            List<String> paramOneList = new ArrayList<String>();
            List<String> paramTwoList = new ArrayList<String>();
            List<String> pointTypeList = new ArrayList<String>();

            String line = "";
            try {
                line = br.readLine();
                if (line == null || line.trim().length() == 0) {
                    //如果飞行路径名为空则出错
                    return false;
                }
                //设定飞行路径名称
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

                    pointTypeList.add(subline[3]);
                    paramOneList.add(subline[4]);
                    paramTwoList.add(subline[5]);
                    heightdata.add(subline[10]);
                    list.add(routeExcel);
                }

                flyingPath.setPathdata(LineUtil.ListToString(list));
                flyingPath.setPointType(pointTypeList.toString().replace("[", "").replace("]", ""));
                flyingPath.setParamOne(paramOneList.toString().replace("[", "").replace("]", ""));
                flyingPath.setParamTwo(paramTwoList.toString().replace("[", "").replace("]", ""));
                flyingPath.setHeightdata(heightdata.toString().replace("[", "").replace("]", ""));

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

   /* public static void main(String[] args) throws FileNotFoundException {

        File file = new File("D:\\航线112701.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fileInputStream); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader);

        String line = "";
        try {
            line = br.readLine();
            System.out.println(line);
            while ((line = br.readLine()) != null) {
                if ( line.length() == 0 ) {
                    System.out.println("@@@") ;
                    continue;
                }
                String[] subline = line.split("\\s+");
                System.out.println(line + "**"  + subline.length) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
