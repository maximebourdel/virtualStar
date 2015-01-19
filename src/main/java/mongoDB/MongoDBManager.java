package mongoDB;

import metier.ArretBus;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDBManager {
	private ClientMongoDB _clientMongoDB;
	
	/**
	 * processus a faire pour inserer les donnees d'un objet ArretBus vers la base mongoDB
	 * @param pArretBus objet ArretBus
	 */
	public void mongoDBStockDataProcess(ArretBus pArretBus){
		openConnexion();
		_clientMongoDB.setDB("mongobase");
		stockArretToMongoDB(pArretBus);
		closeConnexion();
		
	}
	/**
	 * permet d'inserer un objet Arret dans la base MongoDB
	 * @param pArretBus objet ArretBus
	 */
	private void stockArretToMongoDB(ArretBus pArretBus){
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
	 * recupere les donnees de MongoDB
	 * @param pBaseName nom de la base MongoDB
	 * @return DBcursor des resultats
	 */
	public DBCursor getDBDataFromMongoDb(String pBaseName){
		//on ouvre la connexion au client mongoDB
		openConnexion();
		
		_clientMongoDB.setDB(pBaseName);
		
		DB db =  _clientMongoDB.getDB();
		
		DBCollection lignesDeBus = db.getCollection("ArretBus");
		
		DBCursor cursor = lignesDeBus.find();
		
		//affichage
		displayDBResults(cursor);
		
		closeConnexion();
		
		return cursor;
	}
	
	/**
	 * pour afficher le contenu de la base
	 * @param pDbCursor
	 */
	private void displayDBResults(DBCursor pDbCursor){
		while(pDbCursor.hasNext()) {
			DBObject object = pDbCursor.next();
			System.out.println(object.get("localdatetime") + " / " + object.get("direction")  + " / " + object.get("headSign") + " / " + object.get("excepted") + " / " + object.get("real") + " / " +object.get("vehicle") + " / " + object.get("accurate") );
		}
	}
	
	/**
	 * permet d'ouvrir une connexion a MongoDB
	 */
	public void openConnexion(){
//		_clientMongoDB=new ClientMongoDB();
		_clientMongoDB=ClientMongoDB.getInstance();
	}
	/**
	 * permet de fermer la connexion MongoDB
	 */
	public void closeConnexion(){
		_clientMongoDB.close();
	}
	
}
