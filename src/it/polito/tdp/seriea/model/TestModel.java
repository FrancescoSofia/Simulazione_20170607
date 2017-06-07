package it.polito.tdp.seriea.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();
		Season s = new Season(2017,"2016/2017");
		List<Team> team = m.creaGrafo(s);
		for(Team t : team){
			System.out.println(t.getTeam()+t.getPunti());
		}
			
	}

}


