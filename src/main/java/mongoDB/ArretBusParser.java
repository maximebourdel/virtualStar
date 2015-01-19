package mongoDB;

import metier.Arret;
import metier.ArretBus;
import metier.Ligne;

import org.json.JSONArray;
import org.json.JSONObject;

import util.ArretBusAttribut;

import com.mongodb.BasicDBObject;

public class ArretBusParser {

	public static BasicDBObject parser(JSONObject jsonObject) {
		ArretBus arretBus = new ArretBus();
		Arret arret =  new Arret();
		arret.setId(jsonObject.getJSONObject("stopline").getString("stop"));
		arretBus.setArret(arret);
		arretBus.setDate_requete(jsonObject.getJSONObject("@attributes").getString("localdatetime"));
		arretBus.setDirection(jsonObject.getJSONObject("stopline").getString("direction"));
		Ligne ligne = new Ligne();
		ligne.setId(jsonObject.getJSONObject("stopline").getString("route"));
		arretBus.setLigne(ligne);

		JSONObject  json = jsonObject.getJSONObject("stopline");
		JSONObject departures =json.getJSONObject("departures");
		JSONObject value ;
		
		try {
			JSONArray departure = departures.getJSONArray("departure");
			value =departure.getJSONObject(0);
		} catch (Exception e) {
			value = departures.getJSONObject("departure");
		}

		if(value==null){
			//aucune donnee 
			return null;
		}
		
		BasicDBObject arretBusMongo = new BasicDBObject();
		arretBusMongo.append("id_arret",arretBus.getArret().getId());
		arretBusMongo.append("id_ligne",arretBus.getLigne().getId());
		arretBusMongo.append(ArretBusAttribut.DATE_REQUETE, arretBus.getDate_requete());
		arretBusMongo.append(ArretBusAttribut.DIRECTION, arretBus.getDirection());
		arretBusMongo.append(ArretBusAttribut.EN_TETE, arretBus.getEn_tete());
		arretBusMongo.append(ArretBusAttribut.NUM_VEHICULE, value.getJSONObject("@attributes").getString("vehicle"));
		arretBusMongo.append(ArretBusAttribut.PRECISION, value.getJSONObject("@attributes").getString("accurate"));
		arretBusMongo.append(ArretBusAttribut.PREVU, value.getJSONObject("@attributes").getString("expected"));
		arretBusMongo.append(ArretBusAttribut.REEL, value.getString("content"));

		return arretBusMongo;


	}

}
