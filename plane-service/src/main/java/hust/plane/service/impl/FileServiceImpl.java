package hust.plane.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.plane.mapper.mapper.RouteMapper;
import hust.plane.mapper.pojo.Route;
import hust.plane.service.interFace.FileService;
import hust.plane.utils.ExcelUtil;

@Service
public class FileServiceImpl implements FileService {

	/*@Value("${ROOT_FILE}")
	private String ROOT_FILE;*/

	@Autowired
	private RouteMapper routeMapper;

	// 插入路由数据
	@Override
	public boolean insertRoute(File file) {
		// 修改
		// String filepath = ROOT_FILE + path;
		Route route = new Route();
		
		if(ExcelUtil.readExcel(file,route)==false) {
			return false;
		}
		// 设置创建时间		
		Date date = new Date();
		route.setCreatetime(date);

		int count = routeMapper.countByName(route.getName());
		if(count>0)
			return false;
		
		if (routeMapper.insert(route) == 1)
			return true;
		else
			return false;

	}

}
