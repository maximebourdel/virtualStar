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
		arretBus.setPrecision(value.getJSONObject("@attributes").getString("accurate"));
		arretBus.setNum_vehicule(value.getJSONObject("@attributes").getString("vehicle"));
		arretBus.setEn_tete(value.getJSONObject("@attributes").getString("headsign"));
		arretBus.setPrevu(value.getJSONObject("@attributes").getString("expected"));
		arretBus.setReel(value.getString("content"));


		//Arret 
		BasicDBObject arretMongo = new BasicDBObject();
		arretMongo.append("description", arretBus.getArret().getDescription());
		arretMongo.append("id",arretBus.getArret().getId());
		arretMongo.append("lat", arretBus.getArret().getLat());
		//ligne
		BasicDBObject ligneMongo = new BasicDBObject();
		ligneMongo.append("id", arretBus.getLigne().getId());
		ligneMongo.append("long_name", arretBus.getLigne().getLong_name());
		//arretBus
		BasicDBObject arretBusMongo = new BasicDBObject();
		arretBusMongo.append(ArretBusAttribut.ARRET, arretMongo);
		arretBusMongo.append(ArretBusAttribut.LIGNE, ligneMongo);
		arretBusMongo.append(ArretBusAttribut.DATE_REQUETE, arretBus.getDate_requete());
		arretBusMongo.append(ArretBusAttribut.DIRECTION, arretBus.getDirection());
		arretBusMongo.append(ArretBusAttribut.EN_TETE, arretBus.getEn_tete());
		arretBusMongo.append(ArretBusAttribut.NUM_VEHICULE, arretBus.getNum_vehicule());
		arretBusMongo.append(ArretBusAttribut.PRECISION, arretBus.getPrecision());
		arretBusMongo.append(ArretBusAttribut.PREVU, arretBus.getPrevu());
		arretBusMongo.append(ArretBusAttribut.REEL, arretBus.getReel());

		return arretBusMongo;


	}

}
