package hust.plane.service.interFace;

import java.util.List;

import hust.plane.mapper.pojo.Task;
import hust.plane.mapper.pojo.User;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.TaskPojo;


public interface TaskService {

    List<TaskPojo> getALLTask();

    TailPage<TaskPojo> queryPage(Task task, TailPage<TaskPojo> page);

    boolean saveTask(Task task);

    boolean setStatusTaskByTask(Task task, int status);

	String getStatusByTask(Task task);

	boolean setFinishStatusTaskByTask(Task task, int finishstatus);

	Task getTaskByTask(Task task);

	List<Task> getTasklistByAuser(User aUser);

    TailPage<TaskPojo> queryPageWithTime(Task task, TailPage<TaskPojo> page);

	boolean deleteByTask(Task task);

	void updataImgFolderByTask(Task task2);

    String selectImgFolderWithId(Integer taskId);

	Task getTaskByName(String name);
}
