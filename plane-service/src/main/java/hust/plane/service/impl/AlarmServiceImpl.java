package hust.plane.service.impl;

import hust.plane.mapper.mapper.AlarmMapper;
import hust.plane.mapper.pojo.Alarm;
import hust.plane.service.interFace.AlarmService;
import hust.plane.utils.page.AlarmPojo;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.TipException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {
	private static Logger logger = LoggerFactory.getLogger(AlarmService.class);
	@Autowired
	public AlarmMapper alarmMapper;

	@Override
	public List<Alarm> getAllAlarm() {

		List<Alarm> alarmList = alarmMapper.selectALLAlarm();
		return alarmList;
	}

	@Override
	public TailPage<AlarmPojo> queryAlarmWithPage(Alarm alarm, TailPage<AlarmPojo> page) {
		
		int count = alarmMapper.alarmCount(alarm);
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
			return alarmMapper.selectInfoById(id);
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
		if (alarmid<0 || StringUtils.isBlank(description)) {
			throw new TipException("描述信息为空");
		}
		try {
			return alarmMapper.updateDesByAlarmId(Integer.valueOf(alarmid), description) == 1?true:false;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Alarm> getAlarmsByTaskId(Integer taskid) {
		if(taskid ==null)
			throw new TipException("任务编号获取失败");
		return alarmMapper.getAlarmsByTaskId(taskid);
	}

	@Override
	public boolean insertAlarmByAlarms(Alarm alarm) {

		if (alarm.getId() != 0) {
			if (alarmMapper.insertAlarmSelective(alarm) == 1)
				return true;
			else
				return false;
		}
		return false;
	}

}
