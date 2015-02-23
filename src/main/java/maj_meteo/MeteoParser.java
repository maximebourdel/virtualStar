package maj_meteo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import maj_api_keolis.util.ArretBusAttribut;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;

public class MeteoParser {
	public static BasicDBObject parser(JSONObject jsonObject) {
		String[] names=JSONObject.getNames(jsonObject);
		String dateJson=DateMeteoManager.getMeteoDate(names);
		JSONObject json;
		JSONObject temperature;

		try{
			json = jsonObject.getJSONObject(dateJson);
			temperature = json.getJSONObject("temperature");
		}
		catch (Exception je){
			je.getStackTrace();
			return null;
		}

		BasicDBObject meteoMongo = new BasicDBObject();
		meteoMongo.append("date", json.toString());
		meteoMongo.append("temperature",MeteoUtils.convertCelsiusKelvin((temperature.getDouble("sol")), false)).toString();
		meteoMongo.append("pluie",json.getDouble("pluie"));
		meteoMongo.append("risque_neige", json.getString("risque_neige"));
		return meteoMongo;
	}

}
