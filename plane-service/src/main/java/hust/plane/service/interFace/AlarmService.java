package hust.plane.service.interFace;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.mapper.pojo.Task;
import hust.plane.utils.page.AlarmPojo;
import hust.plane.utils.page.TailPage;

import java.util.List;


public interface AlarmService {

    List<Alarm> getAllAlarm();

    TailPage<AlarmPojo> queryAlarmWithPage(Alarm alarm, TailPage<AlarmPojo> page, String pageNum);

    Alarm selectAlarmById(int id);

    boolean updateAlarmStatus(int alarmid);

    boolean insertAlarmById(int planeId);

    boolean updateAlarmDesc(int alarmid, String description);

    List<Alarm> getAlarmsByTaskId(Integer taskid);

    boolean insertAlarmByAlarms(Alarm alarm);

    void insertAlarm(Task task, String alarmDir);
}
