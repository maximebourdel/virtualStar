package api;

import java.net.URI;

public class ClientREST {
	private String key;
	private URI domain;
	private String format ="json" ;
	
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
	
	

}
