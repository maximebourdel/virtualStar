package maj_api_keolis.mongoDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import maj_api_keolis.util.ArretBusAttribut;

import com.mongodb.BasicDBObject;

public class ArretBusParser {

	public static BasicDBObject parser(JSONObject jsonObject) {

		JSONObject json;
		JSONObject departures;
		JSONArray departure;
		JSONObject value;
		
		try{
			json = jsonObject.getJSONObject("stopline");
			departures = json.getJSONObject("departures");
			departure = departures.getJSONArray("departure");
			value = departure.getJSONObject(0);
		}
		catch (JSONException je){
			return null;
		}


		//		try{	
		
		//		} catch (Exception e) {
		//			
		//			value = departures.getJSONObject("departure");
		//			System.out.println("catch " + value);
		//		}
		//		
		//		if(value==null){
		//			//aucune donnee 
		//			return null;
		//		}

		
		BasicDBObject arretBusMongo = new BasicDBObject();
		arretBusMongo.append("id_arret",jsonObject.getJSONObject("stopline").getString("stop"));
		arretBusMongo.append("id_ligne",jsonObject.getJSONObject("stopline").getString("route"));
		arretBusMongo.append(ArretBusAttribut.DATE_REQUETE, jsonObject.getJSONObject("@attributes").getString("localdatetime"));
		arretBusMongo.append(ArretBusAttribut.DIRECTION, jsonObject.getJSONObject("stopline").getString("direction"));
		arretBusMongo.append(ArretBusAttribut.EN_TETE, value.getJSONObject("@attributes").getString("headsign"));
		arretBusMongo.append(ArretBusAttribut.NUM_VEHICULE, value.getJSONObject("@attributes").getString("vehicle"));
		arretBusMongo.append(ArretBusAttribut.PRECISION, value.getJSONObject("@attributes").getString("accurate"));
		arretBusMongo.append(ArretBusAttribut.PREVU, value.getJSONObject("@attributes").getString("expected"));
		arretBusMongo.append(ArretBusAttribut.REEL, value.getString("content"));
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat ("YYYY-MM-dd'T'hh:mm:ss");
			//conversion des strings dates en format Date
			Date dateExpected 	= sdf.parse(value.getJSONObject("@attributes").getString("expected"));
			Date dateReel 	= sdf.parse(value.getString("content"));
			//soustraction des deux dates
			long diff_TR = dateExpected.getTime() - dateReel.getTime();
			
			arretBusMongo.append(ArretBusAttribut.DIFF_TR, Long.toString(diff_TR/1000));
			
		} catch (JSONException e) {
			System.out.println("Erreur du JSON");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Erreur de PARSE");
			e.printStackTrace();
		}
		
		return arretBusMongo;
		
	}
	/**
	 * Le parseur getbusdeparture en mode "line"
	 * @param jsonObject
	 * @return collection de basicDBObject
	 */
	public static List<BasicDBObject> ligneParser(JSONObject jsonObject){
		
		
			
		return null;
	}

}
