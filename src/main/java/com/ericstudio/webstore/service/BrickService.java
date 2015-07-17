package com.ericstudio.webstore.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.ericstudio.webstore.domain.brick.v2.GetSets;
import com.ericstudio.webstore.domain.brick.v2.Sets;

public interface BrickService  {
	List<Sets> getSetsByYear(String year);
	
	String getUserHash() throws FileNotFoundException;

}
