package virtualStar;

import maj_api_keolis.mongoDB.ArretBusLigneParser;
import maj_api_keolis.mongoDB.ClientMongoDB;
import maj_api_keolis.util.NomCollectionMongoDB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import api.ClientREST;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class TestArretBusLigneParser {
	ArretBusLigneParser arretBusLigneParser;
	String route ;
	String direction;
	ClientMongoDB clientMongoDB;
	@Before
	public void setUp() throws Exception {
		route = "0001";
		direction = "0";
		clientMongoDB = ClientMongoDB.getInstance();
				arretBusLigneParser = new ArretBusLigneParser(new ClientREST()
				, clientMongoDB);
	}

	@Test
	public void execute(){
		clientMongoDB.setDB("star");
		DB bd = clientMongoDB.getDB();
		DBCollection collection = bd.getCollection(NomCollectionMongoDB.ARRETBUS);
		long count = collection.count();
	arretBusLigneParser.execute(route, direction);
	Assert.assertTrue(count!=collection.count());
	}

}
