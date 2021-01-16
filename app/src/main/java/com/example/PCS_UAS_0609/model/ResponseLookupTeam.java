package com.example.PCS_UAS_0609.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLookupTeam{

	@SerializedName("teams")
	private List<TeamsItem> teams;

	public void setTeams(List<TeamsItem> teams){
		this.teams = teams;
	}

	public List<TeamsItem> getTeams(){
		return teams;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLookupTeam{" + 
			"teams = '" + teams + '\'' + 
			"}";
		}
}