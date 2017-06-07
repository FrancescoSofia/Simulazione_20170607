package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamMap;

public class SerieADAO {
	
	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons" ;
		
		List<Season> result = new ArrayList<Season>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add( new Season(res.getInt("season"), res.getString("description"))) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Team> listTeams(TeamMap teamMap) {
		String sql = "SELECT team FROM teams" ;
		
		List<Team> result = new ArrayList<Team>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
			Team team = new Team(res.getString("team")) ;
			team = teamMap.put(team);
			result.add(team);
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
		
		public List<Match> listMatchSeason(Season season,TeamMap teamMap) {
			String sql = "SELECT match_id,HomeTeam,AwayTeam, FTR FROM matches WHERE Season = ? " ;
			
			List<Match> result = new ArrayList<Match>() ;
			
			Connection conn = DBConnect.getConnection() ;
			
			try {
				PreparedStatement st = conn.prepareStatement(sql) ;
				st.setInt(1, season.getSeason());
				
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					Match m =  new Match(res.getInt("match_id"),season,teamMap.get(res.getString("HomeTeam")),
							teamMap.get(res.getString("AwayTeam")),res.getString("FTR")) ;
					result.add(m);
				}
				
				
				conn.close();
				return result ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
	}


}
