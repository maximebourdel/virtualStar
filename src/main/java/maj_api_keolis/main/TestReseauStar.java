package maj_api_keolis.main;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpException;

import maj_api_keolis.mongoDB.ClientMongoDB;
import api.ClientREST;

public class TestReseauStar {

	public static void main(String[] args) throws URISyntaxException, HttpException, IOException {
		ReseauStar reseauStar = new ReseauStar(new ClientREST(), ClientMongoDB.getInstance());
		reseauStar.execute();
	}

}
