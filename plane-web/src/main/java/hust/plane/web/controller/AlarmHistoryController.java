package hust.plane.web.controller;

import hust.plane.mapper.pojo.Alarm;
import hust.plane.service.interFace.AlarmService;
import hust.plane.utils.page.AlarmPojo;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.JsonView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AlarmHistoryController {

    @Autowired
    private AlarmService alarmService;

    @RequestMapping(value = "alarmHistory")
    public String alarmHistoryQueryPage(Alarm alarm, TailPage<AlarmPojo> page, Model model,
                                        @RequestParam(required = false) String finishstatus, @RequestParam(required = false) String inputId,
                                        @RequestParam(required = false) String pageNum) {

        if (finishstatus == null || Integer.valueOf(finishstatus) == -1) {
            alarm.setStatus(null);
        } else {
            alarm.setStatus(Integer.valueOf(finishstatus));
        }
        if (inputId == null || inputId == "") {
            alarm.setId(null);
        } else {
            int id = Integer.parseInt(inputId);
            alarm.setTaskId(id);
        }
        page = alarmService.queryAlarmWithPage(alarm, page, pageNum);
        if (StringUtils.isNotBlank(inputId)) {
            model.addAttribute("taskId", inputId);
        }
        model.addAttribute("selectStatus", alarm.getStatus());
        model.addAttribute("page", page);
        model.addAttribute("curNav", "alarmhistory");
        return "alarmHistory";
    }

    //处理告警
    @RequestMapping(value = "dealWithAlarm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dealWithAlarm(Alarm alarm) {

        if (alarmService.updateAlarmStatus(alarm.getId()) == true) {

            return JsonView.render(1, "告警处理成功！");
        } else {
            return JsonView.render(1, "告警处理失败，请重试！");
        }
    }
}
