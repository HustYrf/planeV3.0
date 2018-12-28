package hust.plane.service.impl;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import hust.plane.mapper.mapper.AlarmMapper;
import hust.plane.mapper.mapper.InfoPointMapper;
import hust.plane.mapper.pojo.Alarm;
import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.mapper.pojo.Task;
import hust.plane.service.interFace.AlarmService;
import hust.plane.utils.DateKit;
import hust.plane.utils.GeohashUtil;
import hust.plane.utils.MapUtils;
import hust.plane.utils.PointUtil;
import hust.plane.utils.page.AlarmPojo;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.RouteExcel;
import hust.plane.utils.pojo.TipException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {
    private static Logger logger = LoggerFactory.getLogger(AlarmService.class);
    @Autowired
    public AlarmMapper alarmMapper;

    @Autowired
    public InfoPointMapper infoPointMapper;

    @Override
    public List<Alarm> getAllAlarm() {

        List<Alarm> alarmList = alarmMapper.selectALLAlarm();

        return alarmList.size() > 0 ? alarmList : null;
    }

    @Override
    public TailPage<AlarmPojo> queryAlarmWithPage(Alarm alarm, TailPage<AlarmPojo> page, String pageNum) {

        int count = alarmMapper.alarmCount(alarm);
        if (pageNum != null && Integer.valueOf(pageNum) > (count / TailPage.DEFAULT_PAGE_SIZE + 1)) {
            page.setPageNum(1);
        }
        List<AlarmPojo> alarmPojos = new ArrayList<>();
        if (count >= 0) {
            List<Alarm> alarmList = alarmMapper.queryAlarmPage(alarm, page);
            Iterator<Alarm> iterator = alarmList.iterator();
            while (iterator.hasNext()) {
                alarm = iterator.next();
                alarmPojos.add(new AlarmPojo(alarm));
            }
        }
        page.setItemsTotalCount(count);
        page.setItems(alarmPojos);
        return page;
    }

    @Override
    public Alarm selectAlarmById(int id) {
        if (id != 0) {
            Alarm alarm = alarmMapper.selectInfoById(id);
            return alarm;
        }
        return null;
    }

    @Override
    public boolean updateAlarmStatus(int alarmid) {

        if (alarmMapper.updateByAlarmId(alarmid) == 1)
            return true;
        else
            return false;

    }

    @Override
    public boolean insertAlarmById(int planeId) {
        /*
         * try { if (StringUtils.isBlank(planeId)) { logger.error("输入的无人机编号为空"); throw
         * new TipException("输入的无人机编号为空"); } if (StringUtils.isBlank(taskid)) {
         * logger.error("输入的任务编号为空"); throw new TipException("输入的任务编号为空"); } List<Alarm>
         * alarmList = new ArrayList<>(); alarmList =
         * ImgUtils.alarmList(WebConst.ALARM_PIC_PATH, planeId,taskid); if
         * (alarmList.size() == 0) { throw new TipException("文件夹内无告警图片"); } for (Alarm
         * alarm : alarmList) { int count = alarmMapper.insertAlarmSelective(alarm); if
         * (count < 1) { logger.error("告警点插入错误"); throw new TipException("告警点插入错误"); } }
         * } catch (Exception e) { e.printStackTrace(); }
         */
        return true;
    }

    @Override
    public boolean updateAlarmDesc(int alarmid, String description) {
        if (alarmid < 0 || StringUtils.isBlank(description)) {
            throw new TipException("描述信息为空");
        }
        try {
            return alarmMapper.updateDesByAlarmId(Integer.valueOf(alarmid), description) == 1 ? true : false;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Alarm> getAlarmsByTaskId(Integer taskid) {
        if (taskid == null)
            throw new TipException("任务编号为空");
        return alarmMapper.getAlarmsByTaskId(taskid);
    }

    //插入新的告警点信息
    @Override
    public boolean insertAlarmByAlarms(Alarm alarm) {

        if (alarmMapper.insertAlarmSelective(alarm) == 1)
            return true;
        else
            return false;

    }

    //根据给定文件夹的文件读取告警点信息
    @Override
    public void insertAlarm(Task task, String alarmDir) {

        List<Alarm> alarmList = processlcoaldir(task.getId(), alarmDir);
        Iterator<Alarm> iterator = alarmList.iterator();
        while (iterator.hasNext()) {
            Alarm alarm = iterator.next();
            insertAlarmByAlarms(alarm);
        }

    }

    public List<Alarm> processlcoaldir(int taskid, String alarmDir) {

        List<Alarm> alarmList = new ArrayList<Alarm>();
        File file = new File(alarmDir);
        //File file = new File("D:\\pic");

        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                } else {
                    Alarm alarm = new Alarm();
                    alarm.setUpdatetime(new Date());
                    alarm.setStatus(0);//未处理告警
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

                        //暂时把创建时间设为当前时间
                        alarm.setCreatetime(new Date());

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
                        routeExcel.setLongitude((double) lon);

                        alarm.setPosition(routeExcel.getPositon());

                        alarm.setDescription("这是一张无人机从" + elv + " 高度拍摄告警照片！");

                        //在这里匹配最近的信息点
                        List<String> geohash9area = GeohashUtil.getGeoHashBase32For9(routeExcel);

                        List<InfoPoint> infoPoints = infoPointMapper.getNearPointByGeohash(geohash9area);
                        if (infoPoints.size() == 0) {
                            alarm.setInfoname(null);
                        } else {
                            InfoPoint infoPoint = getMinDisInfoPoint(routeExcel, infoPoints);
                            alarm.setInfoname(infoPoint.getName());
                            alarm.setRouteId(infoPoint.getRouteId());
                        }
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

    //查找离 坐标最近的信息点
    public static InfoPoint getMinDisInfoPoint(RouteExcel routeExcel, List<InfoPoint> infoPoints) {

        Double minDis = Double.MAX_VALUE;
        InfoPoint minInfoPoint = null;

        for (int i = 0; i < infoPoints.size(); i++) {
            InfoPoint infoPoint = infoPoints.get(i);
            List<Double> position = PointUtil.StringPointToList(infoPoint.getPosition());
            Double dis = MapUtils.GetDistance(routeExcel.getLongitude(), routeExcel.getLatitude(), position.get(0), position.get(1));

            if (minDis > dis) {
                minInfoPoint = infoPoint;
                minDis = dis;
            }
        }
        return minInfoPoint;
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


    static public byte[] image2Bytes(File imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(imgSrc);
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
        return bytes;
    }

}
