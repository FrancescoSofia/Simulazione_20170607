package it.polito.tdp.seriea.model;

import java.util.*;

public class TeamMap {
	
	private Map<String,Team> map ;
	
	public TeamMap() {
		 map = new HashMap<String,Team>();
	}
		
	public Team get(String team) {
		return map.get(team) ;
	}
		
	
	public Team put(Team team) {
		Team old = map.get(team.getTeam()) ; 
		if(old==null) {
			map.put(team.getTeam(), team) ;
			return team ;
		} else {
			return old ;
		}
	}

}
