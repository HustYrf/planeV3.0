package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.Department;

public interface DepartmentMapper {

    Department getDepartmentById(int id);

    String getDepartmentDescriptionById(Integer departmentId);
}