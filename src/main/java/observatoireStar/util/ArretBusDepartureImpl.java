package observatoireStar.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import metier.ArretBus;

public class ArretBusDepartureImpl implements ArretBusDeparture {
	private Collection<ArretBus> checkDepartures ;
	private Iterator<ArretBus> itCheckDepartures;
	private ArretBus arretBus;

	public ArretBusDepartureImpl(
			ArretBus arretBus) {
		this.checkDepartures = new ArrayList<ArretBus>();
		this.itCheckDepartures = this.checkDepartures.iterator();
		this.arretBus = arretBus;
	}
	public boolean check(ArretBus arretBus){
		//Verification s il s agit du meme vehicule
		return (this.arretBus.getNum_vehicule()== arretBus.getNum_vehicule())?true:false;
	}
	public void addCheckDeparture(ArretBus arretBus){
			this.checkDepartures.add(arretBus);
	}
	public void  cleanCheckDeparture(){
		this.checkDepartures.clear();
	}

	public ArretBus getArretBus() {
		return arretBus;
	}

	public void setArretBus(ArretBus arretBus) {
		this.arretBus = arretBus;
	}

	public Iterator<ArretBus> getCheckDepartures(){
		
		return this.itCheckDepartures;
	}
	public ArretBus lastDeparture(){
		
		return null;
	}
}
