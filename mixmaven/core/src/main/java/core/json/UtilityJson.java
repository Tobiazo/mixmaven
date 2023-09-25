package core.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.Drink;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

public class UtilityJson {
    /**
     * 
     * @param obj
     * @param file
     */
	public static void saveObjectToJsonFile(Object obj, File file) {
		try (FileWriter fileWriter = new FileWriter(file)) {
			Gson gson = new Gson();
			gson.toJson(obj, fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
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
