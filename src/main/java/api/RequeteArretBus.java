package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RequeteArretBus implements Requete {
	private String version = "2.2";
	private String command;
	private Collection<Parametre> paramtres;
	public RequeteArretBus(String version, String command) {
		this.version = version;
		this.command = command;
		this.paramtres = new ArrayList<Parametre>();
	}
	

	public String getCommand() {
		return this.command;
	}

	public String getVersion() {
		return this.version;
	}

	public String getParametre() {
		String params = "";
		if(this.paramtres.size()!=0){
			Iterator<Parametre> itParametre = this.paramtres.iterator();
			while (itParametre.hasNext()){
				params += "&param["+ itParametre.next().getKey()+"]="+ itParametre.next().getValue();
			}
			return params;	
		}
		return null;
	}

	public String getRequete() {
		return "?cmd="+ this.getCommand()+ "" + "&version="+ this.getVersion() + this.getParametre();
	}

	public void addParametre(String key, String value){
		this.paramtres.add(new Parametre(key, value));
	}
	public boolean removeParam(String key){
		return true;
		}
	}
