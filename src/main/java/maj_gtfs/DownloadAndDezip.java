package maj_gtfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Cette classe va t�l�charger le .zip GTFS, puis le d�zipper dans un dossier.
 * @author JackPhillips
 *
 */
public class DownloadAndDezip {

	public static void downloadAndDezip(String pUrl){
		//Telechargement.
        String url = "http://data.keolis-rennes.com/fileadmin/OpenDataFiles/GTFS/GTFS-20150120.zip";
        System.out.println("Downloading GTFS files...");
        Download.getFile(url);
		System.out.println("...GTFS files downloaded.");
		System.out.println("\t File name : "+Download.fileName);
		System.out.println("\t File length : "+ Download.fileLength+ " octets");
        
		//"Dezippage".
		System.out.println("\n");
		File zipfile = new File("GTFS-20150120.zip");
        File folder = new File("GTFS_Files_Folder");
        System.out.println("Dezipping GTFS files...");
        try {
			Dezipping.unzip(zipfile, folder);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("...GTFS files dezipped.");
	}
}
