package maj_gtfs;

import java.io.File;

/**
 * Cette classe va t�l�charger le .zip GTFS, puis le d�zipper dans un dossier.
 * @author JackPhillips
 *
 */
public class DownloadAndDezip {

    public static void main(String[] args) throws Exception {
        
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
        Dezipping.unzip(zipfile, folder);
        System.out.println("...GTFS files dezipped.");
    }
}
