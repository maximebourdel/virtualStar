package main;

import java.io.IOException;
import java.net.URISyntaxException;

import mongoDB.ArretBusParser;
import mongoDB.ClientMongoDB;

import org.apache.http.HttpException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import api.ClientREST;
import api.RequeteArretBus;

public class App {

	public static void main(String[] args) throws URISyntaxException, HttpException, IOException {
		RequeteArretBus requeteArretBus = new RequeteArretBus();
		requeteArretBus.addParametre("mode","stopline");
		requeteArretBus.addParametre("route][","0001");
		requeteArretBus.addParametre("direction][","0");
		requeteArretBus.addParametre("stop][","1005");

		ClientREST clientREST = new ClientREST();
		clientREST.setRequete(requeteArretBus);
		ClientMongoDB clientMongoDB = ClientMongoDB.getInstance();
		clientMongoDB.setDB("star");
		
		MongoClient mongoClient = clientMongoDB.getMongoClient();
		DB dataBase = clientMongoDB.getDB();

		DBCollection collection = dataBase.getCollection("arretbus");
		BasicDBObject basicDBObject = ArretBusParser.parser(clientREST.execute());
		if(basicDBObject!=null){
			//collection.insert(basicDBObject);
			//System.out.println(basicDBObject);
		}
		else System.out.println("null");
		clientMongoDB.close();


	}

}
