package maj_api_keolis.mongoDB;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;

import maj_api_keolis.api.RequeteArretBus;
import maj_api_keolis.util.NomCollectionMongoDB;

import org.apache.http.HttpException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import api.ClientREST;

public class ArretBusLigneParser {
	private ClientREST clientREST;
	private	ClientMongoDB clientMongoDB;

	public ArretBusLigneParser(ClientREST clientREST, ClientMongoDB clientMongoDB) {
		this.clientREST = clientREST;
		this.clientMongoDB = clientMongoDB;
	}
	public void execute(String route, String direction){
		RequeteArretBus requeteLigne =  new RequeteArretBus();
		requeteLigne.addParametre("mode", "line");
		requeteLigne.addParametre("route", route);
		requeteLigne.addParametre("direction",direction);
		this.clientREST.setRequete(requeteLigne);
		try {

			Collection<BasicDBObject> basicDBObjects = ArretBusParser.ligneParser(this.clientREST.execute());
			this.clientMongoDB.setDB("star");
			DB db = this.clientMongoDB.getDB();
			DBCollection collection = db.getCollection(NomCollectionMongoDB.ARRETBUS);
			Iterator<BasicDBObject> it = basicDBObjects.iterator();
			while (it.hasNext()) {
				BasicDBObject basicDBObject = (BasicDBObject) it.next();
				this.clientMongoDB.insert(collection, basicDBObject);
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
