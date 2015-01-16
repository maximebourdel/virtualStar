package engine;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import metier.ArretBus;
import mongoDB.ClientMongoDB;
import api.ClientREST;
import api.Requete;
import util.ArretBusDeparture;
import util.ArretBusDepartureImpl;

public class ObservatoireImpl implements Observatoire {
	private ClientMongoDB clientMongoDB;
	private ClientREST clientREST;
	private Requete requete;
	private ArretBus arretBus;
	private ArretBusDepartureImpl arretBusDeparture;


	public ObservatoireImpl(ClientMongoDB clientMongoDB, ClientREST clientREST,
			Requete requete) {
		super();
		this.clientMongoDB = clientMongoDB;
		this.clientREST = clientREST;
		this.requete = requete;
		this.arretBus = new ArretBus();
		this.arretBusDeparture.setArretBus(this.arretBus);
	}



	public ArretBus departure() throws URISyntaxException, HttpException, IOException, InterruptedException {
		
		this.clientREST.setRequete(this.requete);
		this.arretBus = this.clientREST.execute();
		
			if(this.arretBusDeparture.getCheckDepartures().hasNext()){
				this.arretBus = this.clientREST.execute();
				
				if(this.arretBusDeparture.check(arretBus)){
					//this.arretBusDeparture.addCheckDeparture(this.arretBus);
					this.arretBusDeparture.setArretBus(this.arretBus);
					Thread.sleep(5000);
				}
				else {
					MongoClient mongoClient = this.clientMongoDB.getMongoClient();
					this.clientMongoDB.setDB("arretbus");
					
					BasicDBObject object = new BasicDBObject();
					//dernier element insere dans le map
					object.append("","");
					object.append("prenom", "mamoutou");
					
					
					DBCollection collection = dataBase.getCollection("depart");
					//collection.insert(object);
				}
			}


		}

	}	
}