package hust.plane.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.plane.mapper.mapper.FlyingPathMapper;
import hust.plane.mapper.mapper.TaskMapper;
import hust.plane.mapper.mapper.UavMapper;
import hust.plane.mapper.mapper.UserMapper;
import hust.plane.mapper.pojo.Task;
import hust.plane.mapper.pojo.TaskExample;
import hust.plane.mapper.pojo.TaskExample.Criteria;
import hust.plane.mapper.pojo.User;
import hust.plane.service.interFace.TaskService;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.TaskPojo;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UavMapper uavMapper;
	@Autowired
	private FlyingPathMapper flyingPathMapper;

	@Override
	public List<TaskPojo> getALLTask() {
		TaskExample example = new TaskExample();
		// 得到所有的任务
		example.setOrderByClause("CreateTime desc");
		List<Task> taskList = taskMapper.selectByExample(example);
		List<TaskPojo> list = null;
		// 得到每个人的名称
		if (taskList != null) {
			list = new ArrayList<TaskPojo>();
			for (Task task : taskList) {
				TaskPojo taskPojo = new TaskPojo();
				// 查询姓名
				User user1 = userMapper.selectByPrimaryKey(task.getUsercreator());
				User user2 = userMapper.selectByPrimaryKey(task.getUserA());
				User user3 = userMapper.selectByPrimaryKey(task.getUserZ());
				taskPojo.setTask(task);
				taskPojo.setUserCreatorName(user1.getName());
				taskPojo.setUserAName(user2.getName());
				taskPojo.setUserZName(user3.getName());
				taskPojo.setUavName(uavMapper.getNameById(task.getUavId()));
				taskPojo.setFlyingPathName(flyingPathMapper.getNameById(task.getId()));
				list.add(taskPojo);
			}
		}
		return list;
	}

	// 分页查询
	@Override
	public TailPage<TaskPojo> queryPage(Task task, TailPage<TaskPojo> page) {
		TaskExample example = new TaskExample();
		example.setOrderByClause("CreateTime desc");
		Criteria createCriteria = example.createCriteria();

		if (task.getFinishstatus() == null || task.getFinishstatus() == -1) {
			task.setFinishstatus(null);
		} else {
			createCriteria.andFinishstatusEqualTo(task.getFinishstatus());
		}
		if (task.getUsercreator() != null) {
			createCriteria.andUsercreatorEqualTo(task.getUsercreator());
		}
		if(task.getName() !=null)
		{
			createCriteria.andNameEqualTo(task.getName());
		}
		int itemsTotalCount = taskMapper.countByExample(example);

		// 包装数据
		List<TaskPojo> items = null;

		if (itemsTotalCount > 0) {
			List<Task> taskList = taskMapper.queryPage(task, page);
			items = new ArrayList<TaskPojo>();
			for (Task task1 : taskList) {
				TaskPojo taskPojo = new TaskPojo();
				// 查询姓名
				User user1 = userMapper.selectByPrimaryKey(task1.getUsercreator());
				User user2 = userMapper.selectByPrimaryKey(task1.getUserA());
				User user3 = userMapper.selectByPrimaryKey(task1.getUserZ());
				taskPojo.setTask(task1);
				taskPojo.setUserCreatorName(user1.getName());
				taskPojo.setUserAName(user2.getName());
				taskPojo.setUserZName(user3.getName());
				taskPojo.setUavName(uavMapper.getNameById(task1.getUavId()));
				taskPojo.setFlyingPathName(flyingPathMapper.getNameById(task1.getFlyingpathId()));
				items.add(taskPojo);
			}
		}
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	// 保存任务
	@Override
	public boolean saveTask(Task task) {

		if (task.getName() == null || task.getName()=="") {
			return false;
		}
		Task task2 = getTaskByName(task.getName());
		if (task2 == null || task2.getId() == 0) {

			if (taskMapper.insertSelective(task) == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			task.setId(task2.getId());
			if (taskMapper.updateByPrimaryKeySelective(task) == 1) {
				return true;
			} else {
				return false;
			}

		}
	}

	@Override
	public boolean setStatusTaskByTask(Task task, int status) {

		task.setStatus(status);
		if (taskMapper.updateStatusByTask(task) == 1)
			return true;
		else
			return false;

	}

	@Override
	public String getStatusByTask(Task task) {

		return taskMapper.getStatusByTask(task);
	}

	@Override
	public boolean setFinishStatusTaskByTask(Task task, int finishstatus) {
		// TODO Auto-generated method stub
		Task task2 = taskMapper.selectByPrimaryKey(task.getId());
		task2.setFinishstatus(finishstatus);

		if (taskMapper.updateByPrimaryKey(task2) == 1)
			return true;
		else
			return false;
	}

	@Override
	public Task getTaskByTask(Task task) {
		return taskMapper.selectByPrimaryKey(task.getId());
	}

	@Override
	public List<Task> getTasklistByAuser(User aUser) {

		return taskMapper.getTasklistByUserCreator(aUser.getId());
	}

	@Override
	public TailPage<TaskPojo> queryPageWithTime(Task task, TailPage<TaskPojo> page) {
		TaskExample example = new TaskExample();
		Criteria createCriteria = example.createCriteria();
		if (task.getFinishstatus() == null || task.getFinishstatus() == -1) {
			task.setFinishstatus(null);
		} else {
			createCriteria.andFinishstatusEqualTo(task.getFinishstatus());
		}
		if (task.getUsercreator() != null) {
			createCriteria.andUsercreatorEqualTo(task.getUsercreator());
		}
		int itemsTotalCount = taskMapper.countByExample(example);

		// 包装数据
		List<TaskPojo> items = null;

		if (itemsTotalCount > 0) {
			List<Task> taskList = taskMapper.queryPageWithTime(task, page);
			items = new ArrayList<TaskPojo>();
			for (Task task1 : taskList) {
				TaskPojo taskPojo = new TaskPojo();
				// 查询姓名
				User user1 = userMapper.selectByPrimaryKey(task1.getUsercreator());
				User user2 = userMapper.selectByPrimaryKey(task1.getUserA());
				User user3 = userMapper.selectByPrimaryKey(task1.getUserZ());
				taskPojo.setTask(task1);
				taskPojo.setUserCreatorName(user1.getName());
				taskPojo.setUserAName(user2.getName());
				taskPojo.setUserZName(user3.getName());
				taskPojo.setUavName(uavMapper.getNameById(task1.getUavId()));
				taskPojo.setFlyingPathName(flyingPathMapper.getNameById(task1.getFlyingpathId()));
				items.add(taskPojo);
			}
		}
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public boolean deleteByTask(Task task) {

		if (taskMapper.deleteByPrimaryKey(task.getId()) == 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void updataImgFolderByTask(Task task) {
		taskMapper.updateImgFolderByTask(task);

	}

	@Override
	public String selectImgFolderWithId(Integer taskId) {
		Task task = taskMapper.selectByPrimaryKey(taskId.intValue());
		if (task != null) {
			return task.getImgfolder();
		}
		return null;
	}

	@Override
	public Task getTaskByName(String name) {
		Task task = taskMapper.getTaskByName(name);
		return task;
	}
}
