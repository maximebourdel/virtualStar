package maj_api_keolis.mongoDB;

import org.json.JSONArray;
import org.json.JSONObject;

public class LigneParser {
	
	public static String[] parser(JSONObject jsonObject){
		JSONArray lignes =  jsonObject.getJSONArray("stopline");
		String stopList[] = new String[lignes.length()];
		for(int i = 0; i < lignes.length(); i++ ){
			stopList[i] = (String)lignes.getJSONObject(i).getString("stop");
		}
		return stopList;
		
	}

}
