package com.hy.wo.service.impl;

import java.util.TimerTask;

import com.hy.wo.cache.JCSManager;

public class MonitorLockService extends TimerTask{
	
	@Override
	public void run() {
			try {
				JCSManager.getInstance().clearDataOutTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
