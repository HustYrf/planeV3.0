package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.Task;
import hust.plane.mapper.pojo.TaskExample;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.TaskPojo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    int countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(int id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(int id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    
    //以下为自定义查询方法
    
    List<Task> queryPage(Task task, TailPage<TaskPojo> page);

	String getStatusByTask(Task task);

	List<Task> getTasklistByUserCreator(int userCreator);

    List<Task> queryPageWithTime(Task task, TailPage<TaskPojo> page);

	int updateStatusByTask(Task task);

	int updateImgFolderByTask(Task task);

	Task getTaskByName(String name);
}