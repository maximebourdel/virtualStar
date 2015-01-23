package maj_gtfs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import maj_api_keolis.util.RoadAttribut;

public class GTFSparser {

	private String path; // = "C://Users/jeje/Desktop/routes.txt";

	public GTFSparser (String path){
		this.path =path;	
	}


	public void execute() {

		BufferedReader br = null;
		String line = "";
		String txtSplitBy = ",";

		try {
			
			br = new BufferedReader(new FileReader(path));
			br.readLine();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] routesTab = line.split(txtSplitBy);
				System.out.println("******************************************************************************************************");
				System.out.println("route [" + RoadAttribut.ID_ROAD  + "= " + removeQuotes(routesTab[0]) + "\n" + RoadAttribut.ID_AGENCY  + "= " + routesTab[1] + "\n"+ RoadAttribut.ROAS_SN  + "= " + routesTab[2] + "\n"+ RoadAttribut.ROAD_LN  + "= " + routesTab[3] + "\n"+ RoadAttribut.ROAD_DESC  + "= " + routesTab[4] + "\n"+ RoadAttribut.ROAD_TYPE  + "= " + routesTab[5] + "\n"+ RoadAttribut.ROAD_URL  + "= " + routesTab[6] + "\n"+ RoadAttribut.ROAD_COLOR  + "= " + routesTab[7] + "\n" + RoadAttribut.ROAD_COLORFONT + "= " + routesTab[8] +"]");
//				BasicDBObject road = new BasicDBObject(); 
//				
//				road.append(RoadAttribut.ID_ROAD, routesTab[0]);
//				road.append(RoadAttribut.ID_AGENCY, routesTab[1]);
//				road.append(RoadAttribut.ROAS_SN, routesTab[2]);
//				road.append(RoadAttribut.ROAD_LN, routesTab[3]);
//				road.append(RoadAttribut.ROAD_DESC, routesTab[4]);
//				road.append(RoadAttribut.ROAD_TYPE, routesTab[5]);
//				road.append(RoadAttribut.ROAD_URL, routesTab[6]);
//				road.append(RoadAttribut.ROAD_COLOR, routesTab[7]);
//				road.append(RoadAttribut.ROAD_COLORFONT, routesTab[8]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
				
	}

	public String removeQuotes(String param){
		return param.substring(1, param.length()-1);
	}
	
	public static void main(String[] args) {
		GTFSparser gtfsp = new GTFSparser("C://Users/jeje/Desktop/routes.txt");
		gtfsp.execute();
	}
	
}
