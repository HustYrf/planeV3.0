package hust.plane.utils;

import hust.plane.utils.pojo.RouteExcel;

import java.util.ArrayList;
import java.util.List;

public class LineUtil {
    //转换为ArrayList
    public static ArrayList<ArrayList<Double>> stringLineToList(String s) {
        //s="LineString(1 1,2 2)"
        ArrayList<ArrayList<Double>> list = new ArrayList<ArrayList<Double>>();
        String sub = s.substring(11, s.length() - 1);
        String slist[] = sub.split(",");
        for (int i = 0; i < slist.length; i++) {
            ArrayList<Double> point = new ArrayList<Double>();
            point.add(Double.parseDouble(slist[i].split(" ")[0]));
            point.add(Double.parseDouble(slist[i].split(" ")[1]));
            list.add(point);
        }
        return list;
    }

    //将信息点字符串转化为数组
    public static ArrayList<String> stringflaginfoToList(String s) {
        //s="LineString(1,1,2,2)"
        ArrayList<String> list = new ArrayList<String>();
        String slist[] = s.split(", ");
        for (int i = 0; i < slist.length; i++) {
            list.add(slist[i]);
        }
        return list;
    }

    //将高度列表字符串转化为数组
    public static ArrayList<Double> stringpointToList(String s) {
        //s="LineString(1,1,2,2)"
        ArrayList<Double> list = new ArrayList<Double>();
        String sub = s.substring(11, s.length() - 1);
        String slist[] = sub.split(",");
        for (int i = 0; i < slist.length; i++) {
            list.add(Double.parseDouble(slist[i]));
        }
        return list;
    }

    //将list转换为数据库数据
    public static String ListToString(List<RouteExcel> readExcellist) {
        StringBuffer s = new StringBuffer();
        s.append("LineString(");
        for (int i = 0; i < readExcellist.size(); i++) {
            Double a = readExcellist.get(i).getLongitude();
            Double b = readExcellist.get(i).getLatitude();
            String s1 = a + " " + b + ",";
            s.append(s1);

        }
        s.deleteCharAt(s.length() - 1);
        s.append(")");
        return s.toString();
    }

    /**
     * 获取路径的开始点
     *
     * @param [path]
     * @return java.lang.String
     * @author rfYang
     * @date 2018/6/29 11:06
     */
    public static String getFirstPoint(String path) {
        path = path.substring(1, path.length() - 1);
        String firstPoint = path.substring(0, path.indexOf(']') + 1);
        return firstPoint;
    }

    /**
     * 路径转换为数组
     *
     * @param [path]
     * @return java.util.List<java.lang.String>
     * @author rfYang
     * @date 2018/6/29 11:18
     */
    public static List<String> pathToArray(String path) {
        List<String> pathArray = new ArrayList<>();
        pathArray.add(getFirstPoint(path));
        String[] result = path.split("\\]");
        for (int i = 1; i < result.length; i++) {
            pathArray.add(result[i].substring(1) + "]");
        }
        return pathArray;
    }

    public static void main(String[] args) {
	        /*String s = "LineString(1 1,2 2)";
	        String sub = s.substring(11, s.length() - 1);
	        String slist[] = sub.split(",");
	        System.out.println(slist[0].split(" ")[0]);*/

        List<String> list = new ArrayList<String>();
        list.add("eee");
        list.add("");
        list.add("www");
        System.out.println(list.toString());
    }

//	public static void main(String[] args) {
//		String s="LineString(1 1,2 2)";
//		String sub=s.substring(11, s.length()-1);
//		String slist[]=sub.split(",");
//		System.out.println(slist[0].split(" ")[0]);
//	}

}
