package api;

import java.awt.Paint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ArretBus implements Requete {
	private String version = "2.2";
	private String command;
	private Map<String, String> params;
	private Collection<Parametre> paramtres;
	public ArretBus(String version, String command) {
		this.version = version;
		this.command = command;
		this.paramtres = new ArrayList<Parametre>();
	}


	public String getCommand() {

		return null;
	}

	public String getVersion() {
		return this.version;
	}

	public String getParametre() {
		//		if(this.params.size()!=0)
		return null;
	}


	public void addParametre(String key, String value){

		params.put(key, value);
	}
	public boolean removeParam(String key){
		if(this.params.containsKey(key)){
			this.params.remove(key);
			return true;
		}
		return false;
	}
	public String getRequete() {
		return null;
	}

}
