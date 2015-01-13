package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import metier.ArretBus;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClientREST {
	private String key;
	private URI domain;
	private String format ="json" ;
	private Requete requete;
	
	public ClientREST(String key, URI host, String format) {
		this.key = key;
		this.domain = host;
		this.format = format;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public URI getDomain() {
		return domain;
	}
	public void setDomain(URI domain) {
		this.domain = domain;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Requete getRequete() {
		return requete;
	}
	public void setRequete(Requete requete) {
		this.requete = requete;
	}
	public  ArretBus execute() throws URISyntaxException, HttpException, IOException{
	
		String requeteHTTP = "";
		HttpClient client = new DefaultHttpClient();
		requeteHTTP = getDomain()+ getFormat()+ "/?"+ getKey() + requete.getRequete();
		HttpGet request = new HttpGet(requeteHTTP);
		HttpResponse response = client.execute(request);
		 BufferedReader br =  new BufferedReader (new InputStreamReader(response.getEntity().getContent()));

			JSONObject status  = new JSONObject(br.readLine());
			JSONObject openData = status;
			int code = status.getJSONObject("opendata").getJSONObject("answer").getJSONObject("status").getJSONObject("@attributes").getInt("code");
			if ( code==0) {
				
				//  openData.getJSONObject("opendata").getJSONObject("answer").getJSONObject("data");
			}
			return null;
	}
	
	

}
