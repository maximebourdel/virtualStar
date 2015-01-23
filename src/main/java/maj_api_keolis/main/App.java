package maj_api_keolis.main;

import java.io.IOException;
import java.net.URISyntaxException;

import maj_api_keolis.mongoDB.ArretBusParser;
import maj_api_keolis.mongoDB.ClientMongoDB;
import maj_api_keolis.util.ArretBusAttribut;

import org.apache.http.HttpException;

import api.ClientREST;
import maj_api_keolis.api.RequeteArretBus;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class App {

	public static void main(String[] args) throws URISyntaxException, HttpException, IOException {
		RequeteArretBus requeteArretBus = new RequeteArretBus();
		requeteArretBus.addParametre("mode","stopline");
		requeteArretBus.addParametre("route][","0004");
		requeteArretBus.addParametre("direction][","0");
		requeteArretBus.addParametre("stop][","1160");

		ClientREST clientREST = new ClientREST();
		clientREST.setRequete(requeteArretBus);
		ClientMongoDB clientMongoDB = ClientMongoDB.getInstance();
		clientMongoDB.setDB("star");

		MongoClient mongoClient = clientMongoDB.getMongoClient();
		DB dataBase = clientMongoDB.getDB();

		DBCollection collection = dataBase.getCollection("arretbus");
		BasicDBObject basicDBObject = ArretBusParser.parser(clientREST.execute());
		
		
		//On construit ici un objet ne contenant que les donnes : "prevu" et "vehicule" (nos attributs a tester)
		DBObject searchIfArretBusAlreadyExist = collection.findOne(
				new BasicDBObject()
				.append(ArretBusAttribut.PREVU, basicDBObject.get(ArretBusAttribut.PREVU))//on vérifie la date
				.append(ArretBusAttribut.NUM_VEHICULE, basicDBObject.get(ArretBusAttribut.NUM_VEHICULE))//on vérifie le numéro du vehicule
		);
		
		//si on a bien parse notre objet et qu'il n'existe pas deja, on l'insere
		if(basicDBObject!=null){
			//
			if (searchIfArretBusAlreadyExist != null) {
				
				//variable temporaire qui représentera notre nouvelle ligne a update
				BasicDBObject UpdatedBasicDBObject = basicDBObject;
				
				//modification des nouvelles donnees
				UpdatedBasicDBObject.put(ArretBusAttribut.DATE_REQUETE,basicDBObject.get(ArretBusAttribut.DATE_REQUETE));
				UpdatedBasicDBObject.put(ArretBusAttribut.REEL,basicDBObject.get(ArretBusAttribut.REEL));
				UpdatedBasicDBObject.put(ArretBusAttribut.PRECISION,basicDBObject.get(ArretBusAttribut.PRECISION));
				
				//on modifie la date prévue seulementde notre collection
				collection.update(searchIfArretBusAlreadyExist, UpdatedBasicDBObject);
				
			} else {//on a bien parse notre objet et qu'il n'existe pas deja, on l'insere
				collection.insert(basicDBObject);
				System.out.println(basicDBObject);
			} 
		} 

		
		
		DBCursor cursor = collection.find();
		while(cursor.hasNext()) {
			DBObject object = cursor.next();
			System.out.println("****************************************");
			System.out.println("\n id arret : " + object.get("id_arret") + "\n id ligne : " + object.get("id_ligne") + "\n date requete : " + object.get("date_requete") + "\n direction : " + object.get("direction") + "\n en tête : " + object.get("en_tete") + "\n prevu : " + object.get("prevu") + "\n reel : " + object.get("reel") + "\n num vehic : " + object.get("num_vehicule") + "\n precision : " + object.get("precision") +  "\n diff_TR : " + object.get("diff_TR"));
		}
		cursor.close();
		clientMongoDB.close();


	}

}
