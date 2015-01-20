package maj_api_keolis.metier;

public class Ligne {
	
	/**
	 * All theses attributes are obtained on data.keolis website
	 */
	
	//route_id
	private String id;
	//agency_id
	private String agence;
	//route_short_name
	private String numero;
	//route_long_name
	private String long_name;
	//route_desc
	private String type_bus;
	//route_type
	private String route_type;
	//route_url
	private String route_url;
	//route_color
	private String couleur_font;
	//route_text_color
	private String couleur_texte;
	
	public Ligne(){};
	
	
	public Ligne(String id, String agence, String numero, String long_name,
			String type_bus, String route_type, String route_url,
			String couleur_font, String couleur_texte) {
		super();
		this.id = id;
		this.agence = agence;
		this.numero = numero;
		this.long_name = long_name;
		this.type_bus = type_bus;
		this.route_type = route_type;
		this.route_url = route_url;
		this.couleur_font = couleur_font;
		this.couleur_texte = couleur_texte;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLong_name() {
		return long_name;
	}
	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}
	public String getType_bus() {
		return type_bus;
	}
	public void setType_bus(String type_bus) {
		this.type_bus = type_bus;
	}
	public String getRoute_type() {
		return route_type;
	}
	public void setRoute_type(String route_type) {
		this.route_type = route_type;
	}
	public String getRoute_url() {
		return route_url;
	}
	public void setRoute_url(String route_url) {
		this.route_url = route_url;
	}
	public String getCouleur_font() {
		return couleur_font;
	}
	public void setCouleur_font(String couleur_font) {
		this.couleur_font = couleur_font;
	}
	public String getCouleur_texte() {
		return couleur_texte;
	}
	public void setCouleur_texte(String couleur_texte) {
		this.couleur_texte = couleur_texte;
	}
}
