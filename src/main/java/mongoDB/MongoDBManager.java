package mongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import metier.ArretBus;

public class MongoDBManager {
	private ClientMongoDB _clientMongoDB;
	
	/**
	 * processus a faire pour inserer les donnees d'un objet ArretBus vers la base mongoDB
	 * @param pArretBus objet ArretBus
	 */
	public void mongoDBProcess(ArretBus pArretBus){
		openConnexion();
		_clientMongoDB.setDB("mongobase");
		stockArretToMongoDB(pArretBus);
		closeConnexion();
		
	}
	/**
	 * permet d'inserer un objet Arret dans la base MongoDB
	 * @param pArretBus objet ArretBus
	 */
	public void stockArretToMongoDB(ArretBus pArretBus){
		DB db=_clientMongoDB.getDB();
		
		DBCollection lignesDeBus = db.getCollection("ArretBus");
		
		BasicDBObject arretBusLine=new BasicDBObject();
		
		//TODO il manque les collections Arret et Ligne encore a definir si on utilise une collection ou pas
		arretBusLine.append("localdatetime", pArretBus.getDate_requete());	
		arretBusLine.append("direction", pArretBus.getDirection());
		arretBusLine.append("headSign", pArretBus.getEn_tete());
		arretBusLine.append("excepted", pArretBus.getPrevu());
		arretBusLine.append("real", pArretBus.getReel());
		arretBusLine.append("vehicle", pArretBus.getNum_vehicule());
		arretBusLine.append("accurate", pArretBus.getPrecision());
		
		lignesDeBus.insert(arretBusLine);

	}
	/**
	 * permet d'ouvrir une connexion a MongoDB
	 */
	private void openConnexion(){
//		_clientMongoDB=new ClientMongoDB();
		_clientMongoDB=ClientMongoDB.getInstance();
	}
	/**
	 * permet de fermer la connexion MongoDB
	 */
	private void closeConnexion(){
		_clientMongoDB.close();
	}
	
}
