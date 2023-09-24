package core.json;

import core.Drink;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/* Utility class with a method for reading Json and converting it to a List */
public class JsonFileToObject {

	/**
	 * @param file
	 * @return List<Drink> A list of all drinks on file
	 */
	public static List<Drink> loadObjectFromJson(File file) {
		try (FileReader reader = new FileReader(file)) {
			Gson gson = new Gson();
			return gson.fromJson(reader, new TypeToken<List<Drink>>(){}.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
