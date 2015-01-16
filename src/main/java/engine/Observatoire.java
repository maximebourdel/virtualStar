package engine;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpException;

import metier.ArretBus;

public interface Observatoire {
	ArretBus departure() throws URISyntaxException, HttpException, IOException, InterruptedException;
}
