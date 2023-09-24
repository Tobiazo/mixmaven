package core.json;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ObjectToJsonFile {
	public static void saveObjectToJsonFile(Object obj, File file) {
		try (FileWriter fileWriter = new FileWriter(file)) {
			Gson gson = new Gson();
			gson.toJson(obj, fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
