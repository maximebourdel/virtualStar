package maj_api_keolis.main;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpException;

import api.ClientREST;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import maj_api_keolis.api.RequeteArretBus;
import maj_api_keolis.mongoDB.ArretBusParser;
import maj_api_keolis.mongoDB.ClientMongoDB;
import maj_api_keolis.mongoDB.LigneParser;

public class TestLigne {

	public static void main(String[] args) throws URISyntaxException, HttpException, IOException {
		MainArretBus mainArretBus =  new MainArretBus(new ClientREST(), ClientMongoDB.getInstance());
		RequeteArretBus requeteArretBus = new RequeteArretBus();
		String route = "0008";//args[0];
		String direction = "1";//args[1];
		//		
		//		requeteArretBus.addParametre("mode","line");
		//		requeteArretBus.addParametre("route",route);
		//		requeteArretBus.addParametre("direction",direction);

		requeteArretBus.addParametre("mode","line");
		requeteArretBus.addParametre("route",route);
		requeteArretBus.addParametre("direction",direction);

		ClientREST clientREST = new ClientREST();
		clientREST.setRequete(requeteArretBus);
		String [] stops = LigneParser.parser(clientREST.execute());

		if(stops.length!=0){
			for (int i = 0; i < stops.length; i++) {
				mainArretBus.execute(route, stops[i], direction);
				System.out.println("Requete  "+ i);
			}

		}

	}

}
