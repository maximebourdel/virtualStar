package metier;

public class ArretBus {
	
	/**
	 * All theses attributes are obtained on data.keolis API
	 * via v2.2 version
	 * 
	 * il faut passer par 
	 * opendata -> answer -> data
	 */
	
	//stopline[i] -> stop
	private Arret arret;
	//stopline[i] -> route
	private Ligne ligne;
	// : localdatetime
	private String date_requete;
	//stopline[i] -> direction
	private String direction;
	//stopline[i] -> departures -> departure[0] : headSign
	private String en_tete;
	//stopline[i] -> departures -> departure[0] : excepted
	private String prevu;
	//stopline[i] -> departures -> departure[0] 
	private String reel;
	//stopline[i] -> departures -> departure[0] : vehicle
	private String num_vehicule;
	//stopline[i] -> departures -> departure[0] : accurate
	private String precision;
	
	public ArretBus(){};
	
	public ArretBus(Arret arret, Ligne ligne, String date_requete,
			String direction, String en_tete, String prevu, String reel,
			String num_vehicule, String precision) {
		super();
		this.arret = arret;
		this.ligne = ligne;
		this.date_requete = date_requete;
		this.direction = direction;
		this.en_tete = en_tete;
		this.prevu = prevu;
		this.reel = reel;
		this.num_vehicule = num_vehicule;
		this.precision = precision;
	}
	
	
	public Arret getArret() {
		return arret;
	}
	public void setArret(Arret arret) {
		this.arret = arret;
	}
	public Ligne getLigne() {
		return ligne;
	}
	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}
	public String getDate_requete() {
		return date_requete;
	}
	public void setDate_requete(String date_requete) {
		this.date_requete = date_requete;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getEn_tete() {
		return en_tete;
	}
	public void setEn_tete(String en_tete) {
		this.en_tete = en_tete;
	}
	public String getPrevu() {
		return prevu;
	}
	public void setPrevu(String prevu) {
		this.prevu = prevu;
	}
	public String getReel() {
		return reel;
	}
	public void setReel(String reel) {
		this.reel = reel;
	}
	public String getNum_vehicule() {
		return num_vehicule;
	}
	public void setNum_vehicule(String num_vehicule) {
		this.num_vehicule = num_vehicule;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
}
