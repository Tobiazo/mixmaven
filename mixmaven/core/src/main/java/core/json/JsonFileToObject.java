package core.json;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import core.Drink;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Utility class with a method for reading Json and converting it to a List */
public class JsonFileToObject {
	public static List<Drink> loadObjectFromJson(String filePath) {
		Gson gson = new Gson();

		try (FileReader reader = new FileReader(filePath)) {

      Type listType = new TypeToken<List<Drink>>(){}.getType();
			List<Drink> obj = gson.fromJson(reader, listType);

			reader.close();

			return obj;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
