package mongoDB;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;

public interface Parser {
	BasicDBObject parser(JSONObject jsonObject);
}
