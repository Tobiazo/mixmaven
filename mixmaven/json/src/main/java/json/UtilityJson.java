package json;

import core.MixMavenModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UtilityJson {
	/**
	 * Writes the obj to file in a pretty format.
	 * @param obj
	 * @param file
	 */
	public static void saveObjectToJsonFile(Object obj, File file) {
		try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(obj, fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param file
	 * @return A MixMavenModel object read from persistance.
	 */
	public static MixMavenModel loadObjectFromJson(File file) {
		try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
			Gson gson = new Gson();
			//return gson.fromJson(reader, new TypeToken<List<Drink>>() { } .getType());
            return gson.fromJson(reader, MixMavenModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
