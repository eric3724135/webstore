package com.ericstudio.webstore.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.ericstudio.webstore.domain.KaoBus;
import com.ericstudio.webstore.service.GsonService;
import com.ericstudio.webstore.service.InitService;
import com.ericstudio.webstore.utils.ListUtils;

@Service
public class InitServiceImpl implements InitService,
		ApplicationListener<ApplicationEvent> {
	private static final Logger LOG = Logger.getLogger(InitServiceImpl.class);
	
	@Autowired
	private GsonService gsonService;

	@Override
	public void getInitData() {
		try {
			List<KaoBus> list = gsonService.getKaoBusInRealTime();
			ListUtils.setKaoBusList(list);
			LOG.info("InitServiceImpl getdata");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// ContextRefreshedEvent 在server 啟動時會執行此事件
		if (event instanceof ContextRefreshedEvent) {
			this.getInitData();

		}
	}

}
