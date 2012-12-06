package org.he.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.he.dto.DownLoadInforData;

public interface DownLoadTaskService {
	/**
	 * start a new down load task
	 * 
	 * @param downLoadInforData
	 */
	void startNewTask(DownLoadInforData downLoadInforData);

	/**
	 * continue a exist task by object downLoadInforData
	 * 
	 * @param downLoadInforData
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	void continueExistTask(List<String> taskList) throws FileNotFoundException;

	/**
	 * pause against running task
	 * 
	 * @param downLoadInforData
	 */
	void pauseTask(DownLoadInforData downLoadInforData);

	/**
	 * continue that have be paused task
	 * 
	 * @param downLoadInforData
	 */
	void continueTask(DownLoadInforData downLoadInforData);
}
