package com.ericstudio.webstore.service;


import java.util.List;

import com.ericstudio.webstore.domain.KaoBus;

public interface GsonService {
	
	List<KaoBus> getKaoBusInRealTime() throws Exception;

}
