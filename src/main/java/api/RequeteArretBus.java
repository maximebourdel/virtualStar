package api;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RequeteArretBus implements Requete {
	private String version = "2.2";
	private String command;
	private Collection<Parametre> paramtres;
	//Cette liste doit être remplie avec les paramètres nécessitant des crochets
	private List<String> hookParameters = new ArrayList<String>();
	
	
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
				//Ici on pourrait tester si c'est "route" ou "stop" ou "direction", alors on rajoute []
				if(hookParameters.contains(itParametre.next().getKey())){
					params += "&param["+ itParametre.next().getKey()+"][]="+ itParametre.next().getValue();
				}else{
					params += "&param["+ itParametre.next().getKey()+"]="+ itParametre.next().getValue();
				}
				
				//params += "&param["+ itParametre.next().getKey()+"]="+ itParametre.next().getValue();
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
		paramtres.remove(key);
		//regénère la requête
		String params = "";
		if(this.paramtres.size()!=0){
			Iterator<Parametre> itParametre = this.paramtres.iterator();
			while (itParametre.hasNext()){
				//Ici on pourrait tester si c'est "route" ou "stop" ou "direction", alors on rajoute []
				if(hookParameters.contains(itParametre.next().getKey())){
					params += "&param["+ itParametre.next().getKey()+"][]="+ itParametre.next().getValue();
				}else{
					params += "&param["+ itParametre.next().getKey()+"]="+ itParametre.next().getValue();
				}
			}
		}
		return true;
	}


	public List<String> getHookParametres() {
		return hookParameters;
	}


	public void setHookParametres(List<String> hookParametres) {
		this.hookParameters = hookParametres;
	}

	
	
	
}
