package maj_api_keolis.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import api.Parametre;
import api.Requete;

public class RequeteLinge implements Requete {
	private String command = "getbusnextdepartures";
	private String version = "2.2";
	private Collection<Parametre> parametres;

	public RequeteLinge(){
		this.parametres = new ArrayList<Parametre>();
	}
	public String getCommand() {
		return this.command;
	}

	public String getVersion() {
		return this.version;
	}

	public String getParametre() {
		String params = "";
		if(this.parametres.size()!=0){
			Iterator<Parametre> itParametre = this.parametres.iterator();
			while (itParametre.hasNext()){
				Parametre parametre = itParametre.next();
				params += "&param["+ parametre.getKey()+"]="+ parametre.getValue();
			}
			return params;	
		}
		return null;
	}

	public String getRequete() {
		return "&cmd="+ this.getCommand()+ "" + "&version="+ this.getVersion() + this.getParametre();	
	}

	public void addParametre(String key, String value){
		this.parametres.add(new Parametre(key, value));
	}
	public boolean removeParam(String key){
		//A implementer
		return true;
	}

}
