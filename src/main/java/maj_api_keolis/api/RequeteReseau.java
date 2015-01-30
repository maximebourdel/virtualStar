package maj_api_keolis.api;

import api.Requete;

public class RequeteReseau extends Requete {
	
	private String command;
	private String version;
	
	public RequeteReseau(String command, String version){
		super();
		this.command = command;
		this.version = version;
	}
	public RequeteReseau() {
		super();
	}

	public String getCommand() {
		return this.command;
	}

	public String getVersion() {
		return this.version;
	}

	public String getRequete() {
		return null;
	}

}
