package maj_api_keolis.main;

import java.io.IOException;
import java.net.URISyntaxException;

import maj_api_keolis.api.RequeteArretBus;
import maj_api_keolis.mongoDB.ArretBusLigneParser;
import maj_api_keolis.mongoDB.ClientMongoDB;
import maj_api_keolis.mongoDB.LigneParser;
import maj_api_keolis.util.Direction;
import maj_api_keolis.util.LigneAttribut;
import maj_api_keolis.util.NomCollectionMongoDB;

import org.apache.http.HttpException;

import api.ClientREST;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ReseauStar {
	private MainArretBus mainArretBus;
	private ClientREST clientREST;
	private ClientMongoDB clientMongoDB;
	private ArretBusLigneParser arretBusLigneParser;
	private String lignes[] ;

	public ReseauStar(ClientREST clientREST, ClientMongoDB clientMongoDB) {
		this.mainArretBus = new MainArretBus(clientREST, clientMongoDB);
		this.clientREST = clientREST;
		this.clientMongoDB = clientMongoDB;
		this.arretBusLigneParser = new ArretBusLigneParser(clientREST, clientMongoDB);
	}

	public void execute() throws URISyntaxException, HttpException, IOException{
		this.lignes = ligneStar();
		if(this.lignes != null){
			for(int i = 0; i < this.lignes.length - 1; i++){
				//				System.out.println("ligne  : " + lignes[i]);
				for(int k = 0; k < Direction.directions.length; k++){
					this.arretBusLigneParser.execute(lignes[i], Direction.directions[k]);
				}
			}
		}
		else System.out.println("lignes nulles ");
	}
	public String [] ligneStar() {
		clientMongoDB.setDB("star");

		DB dataBase = this.clientMongoDB.getDB();
		DBCollection collection = dataBase.getCollection(NomCollectionMongoDB.LIGNE);
		DBCursor dbCursor =  collection.find();
		int lengthDbCursor = dbCursor.count();

		System.out.println("Count  "+ lengthDbCursor);
		if(lengthDbCursor == 0)
			return null;

		String []routes = new String[lengthDbCursor];
		int i = 0;
		while(dbCursor.hasNext()){
			DBObject obj = dbCursor.next();
			routes[i++] = (String) obj.get(LigneAttribut.LIGNE_ID);

		}
		dbCursor.close();
		return routes;

	}
	public String[] makeRequeteArretBus (String route, String direction) throws URISyntaxException, HttpException, IOException{
		RequeteArretBus requeteArretBus = new RequeteArretBus();
		requeteArretBus.addParametre("mode","line");
		requeteArretBus.addParametre("route",route);
		requeteArretBus.addParametre("direction",direction);
		this.clientREST.setRequete(requeteArretBus);
		return  LigneParser.parser(this.clientREST.execute());
	}




}
