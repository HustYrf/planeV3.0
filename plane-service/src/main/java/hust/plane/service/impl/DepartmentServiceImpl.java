package hust.plane.service.impl;

import hust.plane.mapper.mapper.DepartmentMapper;
import hust.plane.mapper.pojo.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hust.plane.service.interFace.DepartmentService;

import javax.annotation.Resource;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Resource
    private DepartmentMapper departmentDao;

    @Override
    public String getUserDepartmentNameWithPartId(Integer departmentId) {
        Department department = departmentDao.getDepartmentById(departmentId);
        if (department != null) {
            return department.getName();
        } else {
            LOGGER.error("无法获取用户部门信息");
        }
        return null;
    }
}
