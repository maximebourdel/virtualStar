package maj_api_keolis.mongoDB;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
public class ClientMongoDB {
	private static String host = "localhost";
	private static int port = 0;
	private MongoClient mongoClient;
	private String dBName;
	private DB db;
	private static ClientMongoDB clientMongoDBstatic;
	private ClientMongoDB() throws UnknownHostException{
		// this.mongoClient = new MongoClient(new ServerAddress(host, port));
		this.mongoClient = new MongoClient(new ServerAddress(host));
	}
	/**
	 * recuperation instance de ClientMongoDB en singleton
	 * @return instance de ClientMongoDB
	 */
	public static synchronized ClientMongoDB getInstance() {
		if (clientMongoDBstatic == null) {
			try {
				clientMongoDBstatic = new ClientMongoDB();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return clientMongoDBstatic;
	}
	// public ClientMongoDB() throws UnknownHostException {
	// this.mongoClient = new MongoClient(new ServerAddress(host, port));
	// }
	// public ClientMongoDB( String dBName) throws UnknownHostException {
	// this.mongoClient = new MongoClient(new ServerAddress(host, port));
	// this.dBName = dBName;
	// }
	public MongoClient getMongoClient(){
		return mongoClient;
	}
	public void close(){
		mongoClient.close();
	}
	public DB getDB() {
		return db;
	}
	public void setDB(String dBName){
		db = mongoClient.getDB(dBName);
	}
}