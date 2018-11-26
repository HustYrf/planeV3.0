package hust.plane.utils;

/**
 * @author hujunhui
 * @date 2018/11/12 21:02
 */
public class MapUtils {

    //地球平均半径
    private static final double EARTH_RADIUS = 6378137;
    //把经纬度转为度（°）
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     * @param lng1  经
     * @param lat1  纬
     * @param lng2
     * @param lat2
     * @return
     */
    public static double GetDistance(double lng1, double lat1, double lng2, double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a/2),2)
                                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)
                )
        );
        //如果为了求真实距离把下面两行加上去，但是为了比较距离的话就不要加了
        //s = s * EARTH_RADIUS;
        //s = Math.round(s * 10000) / 10000;
        return s;
    }

//    public static double GetLagrangeDistance(double lat1, double lng1, double lat2, double lng2){
//        double cha1 = lat1 - lat2;
//        double cha2 = lng1 - lng2;
//        return Math.sqrt(cha1*cha1 + cha2*cha2);
//    }

//    public static void  main(String[] args){
//
//        double d1 = 110.30766296386719;
//        double d2 = 25.054119110107422;
//
//        double dr1 = 110.30753;
//        double dr2 = 25.05391;
//
//        double dd1 = 110.30749;
//        double dd2 = 25.05387;

//        System.out.println("第一种：");
//
//        System.out.println(MapUtils.GetDistance(d1,d2,dr1,dr2));
//        System.out.println(MapUtils.GetDistance(d1,d2,dd1,dd2));

       //System.out.println("第二种：");
        //System.out.println(MapUtils.GetLagrangeDistance(d2,d1,dr2,dr1));
       // System.out.println(MapUtils.GetLagrangeDistance(d2,d1,dd2,dd1));
  //  }

}
