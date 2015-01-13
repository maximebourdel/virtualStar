package observatoireStar.clientMongoDB;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class ClientMongoDB {
	private static String host = "";
	private static int port = 0;
	private MongoClient mongoClient;
	private String dBName;
	private DB db;


	public ClientMongoDB( String dBName) throws UnknownHostException {
		this.mongoClient = new MongoClient(new ServerAddress(host, port));
		this.dBName = dBName;
	}
	public MongoClient getMongoClient(){
		return  mongoClient;
	}
	public void close(){
		mongoClient.close();
	}
	public DB getDB() {
		return mongoClient.getDB(dBName);
	}
	public void setDB(String dBName){
		db =  mongoClient.getDB(dBName);
	}
}
