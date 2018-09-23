package hust.plane.web.controller;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.service.interFace.AlarmService;
import hust.plane.utils.page.AlarmPojo;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AlarmHistoryController {
	
    @Autowired
    private AlarmService alarmService;

    @RequestMapping(value = "alarmHistory")
    public String alarmHistoryQueryPage(Alarm alarm, TailPage<AlarmPojo> page, Model model) {

        if (alarm.getStatus() == null || alarm.getStatus() == -1) {
            alarm.setStatus(null);
        }
        if (alarm.getInputId()== null || alarm.getInputId() == "") {
            alarm.setId(null);
        }else
        {
        	int id = Integer.parseInt(alarm.getInputId());
        	alarm.setId(id);
        }
        page = alarmService.queryAlarmWithPage(alarm, page);
        model.addAttribute("selectStatus", alarm.getStatus());
        model.addAttribute("page", page);
        model.addAttribute("curNav", "alarmhistory");
        return "alarmHistory";
    }

    @RequestMapping(value = "dealWithAlarm")
    @ResponseBody
    public String dealWithAlarm(int id) {
        alarmService.updateAlarmStatus(id);
        return new JsonView(0).toString();
    }
}
