package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.utils.page.AlarmPojo;
import hust.plane.utils.page.TailPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AlarmMapper {

    List<Alarm> selectALLAlarm();

    List<Alarm> selectAllAlarmByCreateTimeDesc();

    int alarmCount(Alarm alarm);

    List<Alarm> queryAlarmPage(Alarm alarm, TailPage<AlarmPojo> page);

    Alarm selectInfoById(Integer id);

    int updateByAlarmId(Integer id);

    int insertAlarmSelective(Alarm alarm);

    int updateDesByAlarmId(Integer id, String description);

    List<Alarm> getAlarmsByTaskId(@Param("taskId") Integer taskId);
}
