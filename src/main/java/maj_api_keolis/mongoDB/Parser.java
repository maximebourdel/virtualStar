package maj_api_keolis.mongoDB;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;

public interface Parser {
	BasicDBObject parser(JSONObject jsonObject);
}
