package hust.plane.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hust.plane.service.interFace.FileService;
import hust.plane.utils.ExcelUtil;
import hust.plane.utils.pojo.JsonView;

@Controller
public class FileController {

	@Autowired
	private FileService FileServiceImpl;

	// 导入路由功能
	@RequestMapping(value = "routeFileImport", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String importOneFile(@RequestParam(value = "routePathExcel", required = true) MultipartFile[] files,
			HttpServletRequest request){

		// 记录有错误的文件名字，并返回前台
		List<String> errfile = new ArrayList<String>();
		List<String> succfile = new ArrayList<String>();
		
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getOriginalFilename();// 获取到上传文件的名字
				File f = null; // 把MultipartFile转化成File
				if (files[i].getSize() <= 0) {
					errfile.add(fileName + "内容为空；");
				} else {
					InputStream ins;
					try {
						ins = files[i].getInputStream();
						f = new File(fileName);
						ExcelUtil.inputStreamToFile(ins, f);
						if (FileServiceImpl.insertRoute(f) == false) {
							errfile.add(fileName + "的格式错误或路由名称重复");
						}else {
							succfile.add(fileName);
						}
						File del = new File(f.toURI());
						del.delete(); // 删除临时文件
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			return JsonView.render(0, "导入文件为空！");
		}
		String reString="";
		if(succfile.size()>0) {
			reString = reString + succfile.toString().replace("[", "").replace("]", "")+"等导入成功!</br>";	
		}
		if(errfile.size()>0) {
			reString = reString + errfile.toString().replace("[", "").replace("]", "")+"等导入失败。";
		}
		
		return JsonView.render(0, reString);
	}

	// 测试文件上传
	@RequestMapping("file")
	public String fileList(Model model) {
		return "file";
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(value = "files", required = true) MultipartFile[] files,
			HttpServletRequest request) throws IOException {

		// String path = request.getSession().getServletContext().getRealPath("upload");
		String path = "D:\\upload\\";
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getOriginalFilename();// 获取到上传文件的名字
				File dir = new File(path, fileName);
				System.out.println(files[i].getSize());
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// 或者处理
				// 保存
				files[i].transferTo(dir); // MultipartFile自带的解析方法
			}
			return JsonView.render(0, "导入成功!");

		} else {
			return JsonView.render(0, "导入失败，无文件!");
		}
	}
}
