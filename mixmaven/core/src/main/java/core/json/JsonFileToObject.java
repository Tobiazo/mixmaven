package core.json;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

/* Utility class with a method for reading Json and converting it to an Object */
public class JsonFileToObject {
	public static Object loadObjectFromJson(String filePath) {
		Gson gson = new Gson();

		try (FileReader reader = new FileReader(filePath)) {
			Object obj = gson.fromJson(reader, Object.class);

			reader.close();

			return obj;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
