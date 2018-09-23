package hust.plane.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {

	
	@RequestMapping("/test")
	@ResponseBody
	public String test()
	{
		String s=
				"<!-- 模态框（Modal） -->\r\n" + 
				"<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\r\n" + 
				"    <div class=\"modal-dialog\">\r\n" + 
				"        <div class=\"modal-content\">\r\n" + 
				"            <div class=\"modal-header\">\r\n" + 
				"                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\r\n" + 
				"                <h4 class=\"modal-title\" id=\"myModalLabel\">模态框（Modal）标题</h4>\r\n" + 
				"            </div>\r\n" + 
				"            <div class=\"modal-body\">在这里添加一些文本</div>\r\n" + 
				"            <div class=\"modal-footer\">\r\n" + 
				"                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n" + 
				"                <button type=\"button\" class=\"btn btn-primary\">提交更改</button>\r\n" + 
				"            </div>\r\n" + 
				"        </div><!-- /.modal-content -->\r\n" + 
				"    </div><!-- /.modal -->\r\n" + 
				"</div>";
		
		return s ;
	}
	public void set()
	{
		
	}
	
	

}
