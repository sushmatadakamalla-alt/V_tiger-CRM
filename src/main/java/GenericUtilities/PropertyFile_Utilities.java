package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile_Utilities {

	public String FetchingDataFromPropertyFile(String key) throws Exception {

		FileInputStream fts = new FileInputStream("./src/test/resources/VtigerCommonData.properties");

		Properties p = new Properties();

		p.load(fts);

		String data = p.getProperty(key);

		return data;
	}

	public void WritebackDatatoPropertyFile(String key, String value, String comments) throws Exception {

		FileInputStream fts = new FileInputStream("./src/test/resources/VtigerCommonData.properties");

		Properties p = new Properties();

		p.load(fts);

		p.put(key, value);

		FileOutputStream fos = new FileOutputStream("./src/test/resources/VtigerCommonData.properties");

		p.store(fos, comments);

	}
}
