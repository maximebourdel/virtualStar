package maj_gtfs;

import maj_api_keolis.mongoDB.ClientMongoDB;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MainGTFS {

	public static void main(String[] args) {
		
		ClientMongoDB clientMongoDB = ClientMongoDB.getInstance();
		clientMongoDB.setDB("star");
		//on se connecte à la base de données de nom "test"
		MongoClient mongoClient = clientMongoDB.getMongoClient();
		DB dataBase = clientMongoDB.getDB();
		//création/utilisation/suppression de la table de nom "LigneDeBus"
		DBCollection lignesDeBus = dataBase.getCollection("ligne");
		GTFSParser gtfsp; 
		gtfsp = new GTFSParserLigne(lignesDeBus);
		gtfsp.execute("GTFS_Files_Folder/routes.txt");
		gtfsp.execute("GTFS_Files_Folder/routes_additionals.txt");
		
		DBCollection arretDeBus = dataBase.getCollection("arret");
		gtfsp = new GTFSParserLigne(arretDeBus);
		gtfsp.execute("GTFS_Files_Folder/stops.txt");
		
		clientMongoDB.close();
		
	}	
}
