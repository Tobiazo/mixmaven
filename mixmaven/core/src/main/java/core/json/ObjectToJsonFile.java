package core.json;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;


public class ObjectToJsonFile {
	public static void saveObjectToJsonFile(Object obj, String filePath){
		try (FileWriter fileWriter = new FileWriter(filePath)){
			Gson gson = new Gson();

			gson.toJson(obj, fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  }


