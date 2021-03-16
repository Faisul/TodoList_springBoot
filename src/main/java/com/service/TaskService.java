package com.service;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.domain.Task;

public class TaskService {

	public boolean isToday(Task task) {
		if(DateUtils.isSameDay(new Date(), task.getDueDate())) {
			return true;
		}
		return false;
	}
}
