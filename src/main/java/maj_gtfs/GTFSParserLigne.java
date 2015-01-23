package maj_gtfs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import maj_api_keolis.util.LigneAttribut;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class GTFSParserLigne implements GTFSParser {

	private DBCollection collectionTxtToMongoDB; 

	public GTFSParserLigne(DBCollection collectionTxtToMongoDB) {
		this.collectionTxtToMongoDB = collectionTxtToMongoDB;
	}

	public void execute(String path) {

		BufferedReader br = null;
		String line = "";
		String dbSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(path));
			br.readLine();
			while ((line = br.readLine()) != null) {

				String[] routesTab = line.split(dbSplitBy);
				BasicDBObject dbLine = new BasicDBObject(); 

				dbLine.append(LigneAttribut.ID_LIGNE, removeQuotes(routesTab[0]));
				dbLine.append(LigneAttribut.ID_AGENCY, removeQuotes(routesTab[1]));
				dbLine.append(LigneAttribut.ROAS_SN, removeQuotes(routesTab[2]));
				dbLine.append(LigneAttribut.LIGNE_LN, removeQuotes(routesTab[3]));
				dbLine.append(LigneAttribut.LIGNE_DESC, removeQuotes(routesTab[4]));
				dbLine.append(LigneAttribut.LIGNE_TYPE, removeQuotes(routesTab[5]));
				dbLine.append(LigneAttribut.LIGNE_URL, removeQuotes(routesTab[6]));
				dbLine.append(LigneAttribut.LIGNE_COLOR, removeQuotes(routesTab[7]));
				dbLine.append(LigneAttribut.LIGNE_COLORFONT, removeQuotes(routesTab[8]));

				collectionTxtToMongoDB.insert(dbLine);
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
	
}
