package maj_api_keolis.main;
import java.net.URISyntaxException;

import maj_gtfs.DownloadAndDezip;
import api.ClientGTFS;

public class GtfsMain {

	public static void main(String[] args) {
		String urlGtfsData="";
		try {
			ClientGTFS clientGTFS = new ClientGTFS();
			urlGtfsData=clientGTFS.getUrlGtfsData();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DownloadAndDezip downAndDezip=new DownloadAndDezip();
		downAndDezip.downloadAndDezip(urlGtfsData);
	}

}
