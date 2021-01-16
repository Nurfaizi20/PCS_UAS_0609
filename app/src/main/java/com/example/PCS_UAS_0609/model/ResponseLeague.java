package com.example.PCS_UAS_0609.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLeague{

	@SerializedName("leagues")
	private List<LeaguesItem> leagues;

	public void setLeagues(List<LeaguesItem> leagues){
		this.leagues = leagues;
	}

	public List<LeaguesItem> getLeagues(){
		return leagues;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLeague{" + 
			"leagues = '" + leagues + '\'' + 
			"}";
		}
}