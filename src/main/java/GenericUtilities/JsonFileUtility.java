package GenericUtilities;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {
	
	/**
	 * this method is used to fetch data from JsonFile
	 * @param key
	 * @return String
	 * @throws Exception
	 * @throws IOException
	 * @throws ParseException
	 */
	
	public String FetchDataFromJsonFile(String key) throws Exception, IOException, ParseException
	{
		
		JSONParser parser=new JSONParser();
		
		Object obj=parser.parse(new FileReader("./src/test/resources/Jsondata.json"));
		JSONObject js=(JSONObject) obj;
		String data=js.get(key).toString();
		return data;
		
	}

}
