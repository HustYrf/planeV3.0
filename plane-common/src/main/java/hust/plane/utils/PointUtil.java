package hust.plane.utils;

import hust.plane.utils.pojo.Point;

import java.util.ArrayList;
import java.util.List;

public class PointUtil {
    // 解析经纬度，构成经纬度数据类。输入字符串Point(x,y)
    public static Point StringToPoint(String s) {
        Point p = new Point();
        String sub = s.substring(6, s.length() - 1);
        double x = Double.parseDouble(sub.split(" ")[0]);
        double y = Double.parseDouble(sub.split(" ")[1]);
        p.setLatitude(x);
        p.setLongitude(y);
        return p;
    }

    public static String StringPointToString(String s) {
        String sub = s.substring(6, s.length() - 1);
        String newString = sub.split(" ")[0] + "," + sub.split(" ")[1];
        return newString;
    }

    // 解析经纬度，构成经纬度数据类。输入字符串Point(x,y),拼接成[x,y]形式
    public static List<Double> StringPointToList(String s) {
        List<Double> list = new ArrayList<Double>();
        String sub = s.substring(6, s.length() - 1);
        double x = Double.parseDouble(sub.split(" ")[0]);
        double y = Double.parseDouble(sub.split(" ")[1]);
        list.add(x);
        list.add(y);
        return list;
    }

    public static String pointToString(List<Double> pointList) {
        return String.valueOf(pointList.get(0)) + "," + String.valueOf(pointList.get(1));
    }

    public static String pointToSqlString(List<Double> point) {

        return "POINT(" + String.valueOf(point.get(0)) + " " + String.valueOf(point.get(1)) + ")";

    }


    public static void main(String[] args) {
//		String s = "Point(1.9999 1.88888)";
//		Point stringToPoint = PointUtil.StringToPoint(s);
//		System.out.println(stringToPoint.getLatitude());
//		List<Double> list = new ArrayList<>();
//		list.add(123.43);
//		list.add(1.43);
//		System.out.println(pointToSqlString(list));

        Integer intHao = 6;
        String strHao = intHao.toString();
        while (strHao.length() < 4) {
            strHao = "0" + strHao;
        }
        System.out.println(strHao);

    }
}
