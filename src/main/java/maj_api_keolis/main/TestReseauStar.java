package maj_api_keolis.main;

import java.io.IOException;
import java.net.URISyntaxException;

import maj_api_keolis.mongoDB.ClientMongoDB;

import org.apache.http.HttpException;

import api.ClientREST;

public class TestReseauStar {

	public static void main(String[] args) throws URISyntaxException, HttpException, IOException {
		ReseauStar reseauStar = new ReseauStar(new ClientREST(), ClientMongoDB.getInstance());
		long start =  System.currentTimeMillis();
		
		reseauStar.execute();
		System.out.println("Durée : " + (long) ( System.currentTimeMillis() - start)/1000);
	}

}
