package maj_api_keolis.mongoDB;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import maj_api_keolis.api.RequeteArretBus;
import maj_api_keolis.util.ArretBusAttribut;
import maj_api_keolis.util.LigneAttribut;
import maj_api_keolis.util.NomCollectionMongoDB;
import maj_meteo.MeteoParser;
import maj_meteo.MeteoREST;

import org.apache.http.HttpException;
import org.json.JSONObject;

import api.ClientREST;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ArretBusLigneParser {
	private ClientREST clientREST;
	private	ClientMongoDB clientMongoDB;
	private DB db;
	public ArretBusLigneParser(ClientREST clientREST, ClientMongoDB clientMongoDB) {
		this.clientREST = clientREST;
		this.clientMongoDB = clientMongoDB;
	}
	public void execute(String route, String direction,BasicDBObject dbObjectMeteo){
		
		System.out.println("Ligne : " + route + " Direction : " + direction);
		
		RequeteArretBus requeteLigne =  new RequeteArretBus();
		requeteLigne.addParametre("mode", "line");
		requeteLigne.addParametre("route", route);
		requeteLigne.addParametre("direction",direction);
		this.clientREST.setRequete(requeteLigne);
		
		try {

			Collection<BasicDBObject> basicDBObjects = ArretBusParser.ligneParser(this.clientREST.execute());
			this.clientMongoDB.setDB("star");
			db = this.clientMongoDB.getDB();
			DBCollection collection = db.getCollection(NomCollectionMongoDB.ARRETBUS);
			if (basicDBObjects != null) {
				Iterator<BasicDBObject> it = basicDBObjects.iterator();
				//collection travaux
				Collection<String> ligneAlerts = ligneAlert();
				
				while (it.hasNext()) {
					BasicDBObject basicDBObject = (BasicDBObject) it.next();
					//Ajout du champs travaux 
					if(ligneAlerts != null && ligneAlerts.contains(basicDBObject.getString(LigneAttribut.LIGNE_ID)))
						basicDBObject.append(ArretBusAttribut.TRAVAUX, "true");
					//insertion des donnees meteo
					basicDBObject.append("temperature", dbObjectMeteo.get("temperature"));
					basicDBObject.append("humidite", dbObjectMeteo.get("humidite"));
					basicDBObject.append("meteo", dbObjectMeteo.get("meteo"));
					
					this.clientMongoDB.insert(collection, basicDBObject);
				}
			}
		} catch (URISyntaxException e) {

			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isTravaux(String route){
		DBCollection collectionLigneAlert = db.getCollection(NomCollectionMongoDB.LIGNEALERT);
		DBObject idLigne = collectionLigneAlert.findOne(
				new BasicDBObject()
				.append(LigneAttribut.LIGNE_ID,route)
				);
		return (idLigne != null);
	}
	public Collection<String> ligneAlert(){
		Collection<String> collections = new ArrayList<String>();
		//les lignes du reseau star
		DBCollection lignes = db.getCollection(NomCollectionMongoDB.LIGNE);
		DBCursor cursorLignes =  lignes.find();
		if(cursorLignes != null){
			while (cursorLignes.hasNext()){
				BasicDBObject ligne = (BasicDBObject) cursorLignes.next();
				if (isTravaux(ligne.getString(LigneAttribut.LIGNE_ID))) {
					collections.add(ligne.getString(LigneAttribut.LIGNE_ID));
				}
			}
			return collections;
		}
		return null;
	}
}
