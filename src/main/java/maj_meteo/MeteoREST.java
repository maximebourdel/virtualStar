package maj_meteo;

import java.io.BufferedReader;
import java.io.IOException;
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

public class MeteoREST{
	private URI domain ;

	public MeteoREST() throws URISyntaxException {
		this.domain = new URI("http://www.infoclimat.fr/public-api/gfs/json?_ll=48.85341,2.3488&_auth=ARteSQV7ByUDLlJlVSMGLwJqUmdbLVVyB3sDYA5rVClSOQRlBmZdO1A%2BVitVeldhBClTMAw3VWUBalcvD30DYgFrXjIFbgdgA2xSN1V6Bi0CLFIzW3tVcgdtA2UOfVQxUjgEfgZkXTtQOVYqVWdXZwQoUywMMlVpAWBXNA9nA2QBZV46BWEHZQNzUi9VYAY2AmRSOlswVT4HMgMxDjBUZVIzBDIGNF07UCFWMFVsV2UEPlMwDDZVaAFkVy8PfQMZARFeJwUmBycDOVJ2VXgGZwJvUmY%3D&_c=95473fd3dba995d8e6d1913ccc7ce252");
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
