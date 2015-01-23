package api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ClientGTFS implements Client {

	private String domain;

	public ClientGTFS() throws URISyntaxException{
		this.domain = "http://data.keolis-rennes.com/fileadmin/OpenDataFiles/GTFS/feed";
	}

	public String execute() throws URISyntaxException, HttpException, IOException, ParserConfigurationException, SAXException{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(domain);
		HttpResponse response = client.execute(request);
		HttpEntity resEntity = response.getEntity();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		NodeList nodes= builder.parse(domain).getElementsByTagName("entry");
		
		if(nodes.getLength()!=0)
			System.out.println(nodes.item(nodes.getLength()-1).getChildNodes());
		NodeList link = nodes.item(nodes.getLength()-1).getChildNodes();
		System.out.println(link.item(4).getFirstChild().getTextContent());
		
		
		return   null;

	}
}
