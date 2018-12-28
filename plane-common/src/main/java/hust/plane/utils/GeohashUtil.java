package hust.plane.utils;

import hust.plane.utils.pojo.RouteExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hujunhui
 * @date 2018/11/11 13:58
 */
public class GeohashUtil {

    public static final double MINLAT = -90;    //经纬度最小值和最大值
    public static final double MAXLAT = 90;

    public static final double MINLNG = -180;
    public static final double MAXLNG = 180;

    /**
     * 精度：
     * 1 2500km;2 630km;3 78km;4 30km
     * 5 2.4km; 6 610m; 7 76m; 8 19m
     */
    private static int hashLength = 8; //经纬度转化为geohash长度

    private static int latLength = 20; //纬度转化为二进制长度
    private static int lngLength = 20; //经度转化为二进制长度

    private static double minLat;     //每格经纬度的单位大小
    private static double minLng;
    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public GeohashUtil(double lat, double lng) {
        setMinLatLng();
    }

    /**
     * @Author:lulei
     * @Description: 设置经纬度的最小单位
     */
    private static void setMinLatLng() {
        minLat = MAXLAT - MINLAT;
        for (int i = 0; i < latLength; i++) {
            minLat /= 2.0;
        }
        minLng = MAXLNG - MINLNG;
        for (int i = 0; i < lngLength; i++) {
            minLng /= 2.0;
        }
    }

    /**
     * @return
     * @Author:lulei
     * @Description: 求所在坐标点及周围点组成的九个
     */
    public static List<String> getGeoHashBase32For9(RouteExcel routeExcel) {

        setMinLatLng();

        double leftLat = routeExcel.getLatitude() - minLat;
        double rightLat = routeExcel.getLatitude() + minLat;
        double upLng = routeExcel.getLongitude() - minLng;
        double downLng = routeExcel.getLongitude() + minLng;
        List<String> base32For9 = new ArrayList<String>();
        //左侧从上到下 3个
        String leftUp = getGeoHashBase32(leftLat, upLng);
        if (!(leftUp == null || "".equals(leftUp))) {
            base32For9.add(leftUp);
        }
        String leftMid = getGeoHashBase32(leftLat, routeExcel.getLongitude());
        if (!(leftMid == null || "".equals(leftMid))) {
            base32For9.add(leftMid);
        }
        String leftDown = getGeoHashBase32(leftLat, downLng);
        if (!(leftDown == null || "".equals(leftDown))) {
            base32For9.add(leftDown);
        }
        //中间从上到下 3个
        String midUp = getGeoHashBase32(routeExcel.getLatitude(), upLng);
        if (!(midUp == null || "".equals(midUp))) {
            base32For9.add(midUp);
        }
        String midMid = getGeoHashBase32(routeExcel.getLatitude(), routeExcel.getLongitude());
        if (!(midMid == null || "".equals(midMid))) {
            base32For9.add(midMid);
        }
        String midDown = getGeoHashBase32(routeExcel.getLatitude(), downLng);
        if (!(midDown == null || "".equals(midDown))) {
            base32For9.add(midDown);
        }
        //右侧从上到下 3个
        String rightUp = getGeoHashBase32(rightLat, upLng);
        if (!(rightUp == null || "".equals(rightUp))) {
            base32For9.add(rightUp);
        }
        String rightMid = getGeoHashBase32(rightLat, routeExcel.getLongitude());
        if (!(rightMid == null || "".equals(rightMid))) {
            base32For9.add(rightMid);
        }
        String rightDown = getGeoHashBase32(rightLat, downLng);
        if (!(rightDown == null || "".equals(rightDown))) {
            base32For9.add(rightDown);
        }
        return base32For9;
    }

    /**
     * @param length
     * @return
     * @Author:lulei
     * @Description: 设置经纬度转化为geohash长度
     */
    public boolean sethashLength(int length) {
        if (length < 1) {
            return false;
        }
        hashLength = length;
        latLength = (length * 5) / 2;
        if (length % 2 == 0) {
            lngLength = latLength;
        } else {
            lngLength = latLength + 1;
        }
        setMinLatLng();
        return true;
    }

    /**
     * @return
     * @Author:lulei
     * @Description: 获取经纬度的base32字符串
     */
    public static String getGeoHashBase32(RouteExcel routeExcel) {
        setMinLatLng();
        return getGeoHashBase32(routeExcel.getLatitude(), routeExcel.getLongitude());
    }

    /**
     * @param lat
     * @param lng
     * @return
     * @Author:lulei
     * @Description: 获取经纬度的base32字符串
     */
    private static String getGeoHashBase32(double lat, double lng) {
        boolean[] bools = getGeoBinary(lat, lng);
        if (bools == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bools.length; i = i + 5) {
            boolean[] base32 = new boolean[5];
            for (int j = 0; j < 5; j++) {
                base32[j] = bools[i + j];
            }
            char cha = getBase32Char(base32);
            if (' ' == cha) {
                return null;
            }
            sb.append(cha);
        }
        return sb.toString();
    }

    /**
     * @param base32
     * @return
     * @Author:lulei
     * @Description: 将五位二进制转化为base32
     */
    private static char getBase32Char(boolean[] base32) {
        if (base32 == null || base32.length != 5) {
            return ' ';
        }
        int num = 0;
        for (boolean bool : base32) {
            num <<= 1;
            if (bool) {
                num += 1;
            }
        }
        return CHARS[num % CHARS.length];
    }

    /**
     * @param lat
     * @param lng
     * @return
     * @Author:lulei
     * @Description: 获取坐标的geo二进制字符串
     */
    private static boolean[] getGeoBinary(double lat, double lng) {
        boolean[] latArray = getHashArray(lat, MINLAT, MAXLAT, latLength);
        boolean[] lngArray = getHashArray(lng, MINLNG, MAXLNG, lngLength);
        return merge(latArray, lngArray);
    }

    /**
     * @param latArray
     * @param lngArray
     * @return
     * @Author:lulei
     * @Description: 合并经纬度二进制
     */
    private static boolean[] merge(boolean[] latArray, boolean[] lngArray) {
        if (latArray == null || lngArray == null) {
            return null;
        }
        boolean[] result = new boolean[lngArray.length + latArray.length];
        Arrays.fill(result, false);
        for (int i = 0; i < lngArray.length; i++) {
            result[2 * i] = lngArray[i];
        }
        for (int i = 0; i < latArray.length; i++) {
            result[2 * i + 1] = latArray[i];
        }
        return result;
    }

    /**
     * @param value
     * @param min
     * @param max
     * @return
     * @Author:lulei
     * @Description: 将数字转化为geohash二进制字符串
     */
    private static boolean[] getHashArray(double value, double min, double max, int length) {
        if (value < min || value > max) {
            return null;
        }
        if (length < 1) {
            return null;
        }
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            double mid = (min + max) / 2.0;
            if (value > mid) {
                result[i] = true;
                min = mid;
            } else {
                result[i] = false;
                max = mid;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        RouteExcel routeExcel = new RouteExcel();
        routeExcel.setLatitude(14.53456543);
        routeExcel.setLongitude(34.5435288);
        System.out.println(GeohashUtil.getGeoHashBase32(routeExcel));
        System.out.println(GeohashUtil.getGeoHashBase32For9(routeExcel).toString());
    }

}
