package it.polito.tdp.seriea.model;

import java.util.*;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	private List<Season> stagioni;
	private List<Team> teams;
	private SerieADAO dao;
	private DirectedWeightedMultigraph<Team,DefaultWeightedEdge> grafo;
	private TeamMap teamMap;
	//private Map<Team,Integer> classifiche;
	
 public	Model(){
	 dao = new SerieADAO();
	 this.teamMap = new TeamMap();
 }

 public List<Season> getSeason(){
	 if(this.stagioni==null){
		this.stagioni= dao.listSeasons();
	 }
	 return this.stagioni;
 }
 public List<Team> getTeams(){
	 if(this.teams==null){
		this.teams= dao.listTeams(teamMap);
	 }
	 return this.teams;
 }
 
 public List<Team> creaGrafo(Season stagione){
	 this.getTeams();
	// classifiche = new HashMap<Team,Integer>();
	 grafo = new DirectedWeightedMultigraph<Team,DefaultWeightedEdge>(DefaultWeightedEdge.class);
	 List<Match> match = dao.listMatchSeason(stagione, teamMap);
	 for(Match m: match){
		 if(!grafo.containsVertex(m.getAwayTeam())){
			 grafo.addVertex(m.getAwayTeam());
			 m.getAwayTeam().setPunti(0);
		 }
		 if(!grafo.containsVertex(m.getHomeTeam())){
			 grafo.addVertex(m.getHomeTeam());
			 m.getHomeTeam().setPunti(0);
		 }
		 
		 switch (m.getFtr()) {
			case "H":
				DefaultWeightedEdge eCasa = grafo.addEdge(m.getHomeTeam(), m.getAwayTeam());
				grafo.setEdgeWeight(eCasa, +1); 
				int casa = m.getHomeTeam().getPunti()+3;
				m.getHomeTeam().setPunti(casa);
				int fuori = m.getAwayTeam().getPunti()+0;
				m.getAwayTeam().setPunti(fuori);
				break;
			case "D":
				eCasa = grafo.addEdge(m.getHomeTeam(), m.getAwayTeam());
				grafo.setEdgeWeight(eCasa, 0); 	
				casa = m.getHomeTeam().getPunti()+1;
				m.getHomeTeam().setPunti(casa);
				fuori = m.getAwayTeam().getPunti()+1;
				m.getAwayTeam().setPunti(fuori);
				break;
			case "A":
				eCasa = grafo.addEdge(m.getHomeTeam(), m.getAwayTeam());
				grafo.setEdgeWeight(eCasa, -1);	
				casa = m.getHomeTeam().getPunti()+0;
				m.getHomeTeam().setPunti(casa);
				fuori = m.getAwayTeam().getPunti()+3;
				m.getAwayTeam().setPunti(fuori);
				break;
			}
		 
		 }
	 List<Team> punti = new ArrayList<Team>(grafo.vertexSet());
	 Collections.sort(punti);
	 return punti;
	
	
 }
 	 
	 
}
 
 
