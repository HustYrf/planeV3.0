
package hust.plane.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


	//提供 app下载
	@RequestMapping("AppDownload")
	public void AppDownLoads(HttpServletRequest request, HttpServletResponse response) throws IOException{

		String 	basepath = request.getSession().getServletContext().getRealPath("");
		//String basepath = request.getSession().getServletContext().getRealPath("/WEB-INF/ftl"); 
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			response.setContentType(request.getSession().getServletContext().getMimeType(basepath +file.separator+ "TelUAV.apk"));
			response.setHeader("Content-Disposition", "attachment;filename=TelUAV.apk");

			file = new File(basepath +file.separator+ "NewPhone.apk");
			fin = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];  // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的excel文件的内容输出到浏览器中
			while((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if(fin != null) fin.close();
			if(out != null) out.close();
		}
	}

	// 导入光缆功能
	@RequestMapping(value = "routeFileImport", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String importOneFile(@RequestParam(value = "routePathExcel", required = true) MultipartFile[] files,
								HttpServletRequest request){

		//并且在这把相关的路由 保存在数据库

		// 记录有错误的文件名字，并返回前台
		List<String> errfile = new ArrayList<String>();
		List<String> succfile = new ArrayList<String>();
		String 	basepath = request.getSession().getServletContext().getRealPath("");
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getOriginalFilename();// 获取到上传文件的名字
				File f = null; // 把MultipartFile转化成File
				if (files[i].getSize() <= 0 || files.equals("")) {
					errfile.add(fileName + "文件内容为空；");
				} else {
					InputStream ins;
					try {
						ins = files[i].getInputStream();

						f = new File(basepath+fileName);
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
			reString = reString + succfile.toString().replace("[", "").replace("]", "")+",等文件导入成功!</br>";
		}
		if(errfile.size()>0) {
			reString = reString + errfile.toString().replace("[", "").replace("]", "")+",等文件导入失败。";
		}

		return JsonView.render(0, reString);
	}

	// 导入飞行路径功能，使用txt文件

	@RequestMapping(value = "flyingPathfileImport", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String flyingPathfileImport(@RequestParam(value = "flyingPathExcel", required = true) MultipartFile[] files,
								HttpServletRequest request){

        // 记录有错误的文件名字，并返回前台
		List<String> errfile = new ArrayList<String>();
		List<String> succfile = new ArrayList<String>();
		String 	basepath = request.getSession().getServletContext().getRealPath("");
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getOriginalFilename();// 获取到上传文件的名字
				File f = null; // 把MultipartFile转化成File
				if (files[i].getSize() <= 0 || files.equals("")) {
					errfile.add(fileName + "的文件内容为空；");
				} else {
					InputStream ins;
					try {
						ins = files[i].getInputStream();

						f = new File(basepath+fileName);
						ExcelUtil.inputStreamToFile(ins, f);

						if (FileServiceImpl.insertFlyingPath(f) == false) {
							errfile.add(fileName + "的格式错误或飞行路径名称重复");
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
			reString = reString + succfile.toString().replace("[", "").replace("]", "")+",等文件导入成功!</br>";
		}
		if(errfile.size()>0) {
			reString = reString + errfile.toString().replace("[", "").replace("]", "")+",等文件导入失败。";
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
				// 或者保存
				files[i].transferTo(dir); // MultipartFile自带的解析方法
			}
			return JsonView.render(0, "导入成功!");

		} else {
			return JsonView.render(0, "导入失败，无文件!");
		}
	}
}
