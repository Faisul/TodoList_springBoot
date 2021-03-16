package com.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.util.Assert;

import com.domain.Task;

//@RunWith(value=)
public class TaskServiceTest {

	@Test
	public void test_success() {
		Task task = new Task();
		task.setDueDate(new Date());
		Assert.isTrue(new TaskService().isToday(task));
	}

}
