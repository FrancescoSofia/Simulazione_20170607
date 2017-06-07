package it.polito.tdp.seriea.model;

import java.util.HashMap;
import java.util.Map;

public class SeasonMap {
	
private Map<Integer,Season> map ;
	
	public SeasonMap() {
		 map = new HashMap<Integer,Season>();
	}
		
	public Season get(int season) {
		return map.get(season) ;
	}
		
	
	public Season put(Season season) {
		Season old = map.get(season.getSeason()) ; 
		if(old==null) {
			map.put(season.getSeason(), season) ;
			return season ;
		} else {
			return old ;
		}
	}

}
