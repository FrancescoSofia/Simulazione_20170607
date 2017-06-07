package it.polito.tdp.seriea.db;

import java.util.List;

import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamMap;

public class TestSerieADAO {

	public static void main(String[] args) {
		SerieADAO dao = new SerieADAO() ;
		TeamMap map = new TeamMap();
		
		List<Season> seasons = dao.listSeasons() ;
		System.out.println(seasons);
		
		List<Team> teams = dao.listTeams(map) ;
		System.out.println(teams);


	}

}
