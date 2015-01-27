package maj_gtfs;

import java.net.URISyntaxException;

import maj_api_keolis.mongoDB.ClientMongoDB;
import api.ClientGTFS;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MainGTFS {

	public static void main(String[] args) {
		
		ClientGTFS clientGTFS;
		try {
			clientGTFS = new ClientGTFS();
			DownloadAndDezip.downloadAndDezip(clientGTFS.getUrlGtfsData());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ClientMongoDB clientMongoDB = ClientMongoDB.getInstance();
		clientMongoDB.setDB("star");
		//on se connecte à la base de données de nom "test"
		MongoClient mongoClient = clientMongoDB.getMongoClient();
		DB dataBase = clientMongoDB.getDB();
		//création/utilisation/suppression de la table de nom "LigneDeBus"
		GTFSParser gtfsp; 
		
		DBCollection lignesDeBus = dataBase.getCollection("ligne");
		lignesDeBus.drop();
		gtfsp = new GTFSParserLigne(lignesDeBus);
		gtfsp.execute("GTFS_Files_Folder/routes.txt");
		gtfsp.execute("GTFS_Files_Folder/routes_additionals.txt");
		
		DBCollection arretDeBus = dataBase.getCollection("arret");
		arretDeBus.drop();
		gtfsp = new GTFSParserLigne(arretDeBus);
		gtfsp.execute("GTFS_Files_Folder/stops.txt");
		
		clientMongoDB.close();
		
	}	
}