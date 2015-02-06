package maj_api_keolis.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import maj_api_keolis.api.RequeteArretBus;
import maj_api_keolis.mongoDB.ArretBusParser;
import maj_api_keolis.mongoDB.ClientMongoDB;
import maj_api_keolis.mongoDB.LigneParser;
import maj_api_keolis.util.NomCollectionMongoDB;
import api.ClientREST;
import api.Parametre;

public class TestLigneStar {

	public static void main(String[] args) throws URISyntaxException {
		ClientMongoDB clientMongoDB = ClientMongoDB.getInstance();
		RequeteArretBus requeteLigne =  new RequeteArretBus();
		requeteLigne.addParametre("mode", "line");
		requeteLigne.addParametre("route", "0001");
		requeteLigne.addParametre("direction", "0");
		ClientREST clientREST = new ClientREST();
		try {
			clientREST = new ClientREST();
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		clientREST.setRequete(requeteLigne);
		try {
			
			Collection<BasicDBObject> basicDBObjects = ArretBusParser.ligneParser(clientREST.execute());
			clientMongoDB.setDB("star");
			DB db = clientMongoDB.getDB();
			DBCollection collection = db.getCollection(NomCollectionMongoDB.ARRETBUS);
			Iterator<BasicDBObject> it = basicDBObjects.iterator();
			while (it.hasNext()) {
				BasicDBObject basicDBObject = (BasicDBObject) it.next();
				clientMongoDB.insert(collection, basicDBObject);
			}
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
