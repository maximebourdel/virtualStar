package maj_api_keolis.api;

import api.Requete;

public class RequeteLigne extends Requete {
	private String command = "getbusnextdepartures";
	private String version = "2.2";

	public RequeteLigne(){
		super();
	}
	public String getCommand() {
		return this.command;
	}

	public String getVersion() {
		return this.version;
	}

	public String getRequete() {
		return "&cmd="+ this.getCommand()+ "" + "&version="+ this.getVersion() + this.getParametre();	
	}

}
