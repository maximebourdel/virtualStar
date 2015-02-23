package maj_meteo;

import java.io.BufferedReader;
import java.io.IOException;
<<<<<<< HEAD
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
=======
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
>>>>>>> branch 'master' of https://github.com/maximebourdel/virtualStar

public class MeteoREST{
	private URI domain ;

	public MeteoREST() throws URISyntaxException {
		this.domain = new URI("http://www.infoclimat.fr/public-api/gfs/json?_ll=48.11198,-1.67429&_auth=VkxTRAd5ASNecwM0VyEELQBoV2IMegMkAn4HZFg9BXgJa1E0VTdXPANkBHkFKlZ%2FBTcFew82BToFYgVgXztQLFYqUzUHbQFrXjIDaFduBDAALFcoDC4DOgJ%2BB39YNAViCWJRK1UwVzUDcgRlBTBWfAU3BWEPMgUiBXkFY182UDFWMVMxB2wBZl41A2VXbwQvACxXMgw3A24CMAc2WDwFbgliUTJVNFcxA2kEZgU8VnwFPwVgDzEFNQVgBWFfOlAwVipTKAcdARBeLAMhVyUEZQB1VyoMZgNlAjU%3D&_c=2d2f64ab574b007b032a417155282177");
	}

	public URI getDomain() {
		return domain;
	}
	public void setDomain(URI domain) {
		this.domain = domain;
	}
	public  JSONObject execute() throws URISyntaxException, HttpException, IOException{

		String requeteHTTP = "";
		HttpClient client = new DefaultHttpClient();
		//construction de la requete 
		requeteHTTP = this.getDomain().toString();
		System.out.println(requeteHTTP);
		HttpGet request = new HttpGet(requeteHTTP);
		HttpResponse response = client.execute(request);
		BufferedReader br =  new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String jsonStringLine = br.readLine();
		JSONObject jsonObject  = new JSONObject(jsonStringLine);
		return jsonObject;
	}



}
