package maj_api_keolis.metier;

public class Arret {
	
	/**
	 * All theses attributes are obtained on data.keolis website
	 */
	
	//stop_id
	private String id;
	//stop_code
	private String stop_code;
	//stop_name
	private String nom;
	//stop_desc
	private String description;
	//stop_lat
	private String lon;
	//stop_lat
	private String lat;
	//zone_id
	private String zone_id;
	//stop_url
	private String stop_url;
	//location_type
	private String location_type;
	//parent_station
	private String parent_station;
	//stop_timezone
	private String stop_timezone;
	//wheelchair_boarding
	private String wheelchair_boarding;
	
	public Arret(){};
	
	public Arret(String id, String stop_code, String nom, String description,
			String lon, String lat, String zone_id, String stop_url,
			String location_type, String parent_station, String stop_timezone,
			String wheelchair_boarding) {
		super();
		this.id = id;
		this.stop_code = stop_code;
		this.nom = nom;
		this.description = description;
		this.lon = lon;
		this.lat = lat;
		this.zone_id = zone_id;
		this.stop_url = stop_url;
		this.location_type = location_type;
		this.parent_station = parent_station;
		this.stop_timezone = stop_timezone;
		this.wheelchair_boarding = wheelchair_boarding;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStop_code() {
		return stop_code;
	}
	public void setStop_code(String stop_code) {
		this.stop_code = stop_code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getZone_id() {
		return zone_id;
	}
	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}
	public String getStop_url() {
		return stop_url;
	}
	public void setStop_url(String stop_url) {
		this.stop_url = stop_url;
	}
	public String getLocation_type() {
		return location_type;
	}
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	public String getParent_station() {
		return parent_station;
	}
	public void setParent_station(String parent_station) {
		this.parent_station = parent_station;
	}
	public String getStop_timezone() {
		return stop_timezone;
	}
	public void setStop_timezone(String stop_timezone) {
		this.stop_timezone = stop_timezone;
	}
	public String getWheelchair_boarding() {
		return wheelchair_boarding;
	}
	public void setWheelchair_boarding(String wheelchair_boarding) {
		this.wheelchair_boarding = wheelchair_boarding;
	}
}
