package maj_api_keolis.mongoDB;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;

import maj_api_keolis.api.RequeteArretBus;
import maj_api_keolis.util.LigneAttribut;
import maj_api_keolis.util.NomCollectionMongoDB;

import org.apache.http.HttpException;

import api.ClientREST;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
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
				while (it.hasNext()) {
					BasicDBObject basicDBObject = (BasicDBObject) it.next();

					//insertion des donnees meteo
					basicDBObject.append("temperature", dbObjectMeteo.get("temperature"));
					basicDBObject.append("pluie", dbObjectMeteo.get("pluie"));
					basicDBObject.append("risque_neige", dbObjectMeteo.get("risque_neige"));
					
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
//		System.out.println("ligne id in alert = "+(idLigne != null));
		return (idLigne != null);
	}

}
