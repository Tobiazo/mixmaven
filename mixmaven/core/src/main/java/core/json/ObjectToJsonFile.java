package core.json;

import com.google.gson.Gson;

import core.Drink;

import java.io.FileWriter;
import java.io.IOException;

public class ObjectToJsonFile {
	public static void saveObjectToJsonFile(Object obj, String filePath){
		try (FileWriter fileWriter = new FileWriter(filePath)){
			Gson gson = new Gson();

			gson.toJson(obj, fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	

    public static void main(String[] args){
		Drink test = new Drink("hei");
		ObjectToJsonFile.saveObjectToJsonFile(test, "/Users/emilsolberg/Documents/ntnu/ITP/drinkapp/gr2331/gr2331/test");
	}
  }


