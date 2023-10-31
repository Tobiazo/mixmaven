package json;

// import core.json.UtilityJson;
import core.Drink;
import core.MixMavenModel;
import java.util.List;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

/**
 * Datahandler contains the File object and drinks field. Drinks is a list containing all Drink
 * objects
 */
public final class DataHandler {

	private File dataFile;
	private List<Drink> drinks;
    private MixMavenModel mixMavenModel;
    private static DataHandler singleInstance;

    private DataHandler() {
        this.setFilePath("Data.json");
        drinks = new ArrayList<>();
        mixMavenModel = new MixMavenModel(drinks);
    }

    public static synchronized DataHandler getInstance() {
        if (singleInstance == null) singleInstance = new DataHandler();
        return singleInstance;
    }

    public String getDataFile() {
        return dataFile.getName();
    }

    public void setFilePath(String fileName) {
    String userHome = System.getProperty("user.home");
    File folder = new File(userHome, "MixMaven");

    if (!folder.exists()) {
        if (folder.mkdir()) {
            System.out.println("Folder created successfully.");
        } else {
            System.err.println("Failed to create the folder.");
            return;
        }
    }
    dataFile = new File(folder, fileName);

    try {
        if (dataFile.createNewFile()) {
            UtilityJson.saveObjectToJsonFile(new MixMavenModel(new ArrayList<>()), dataFile);
            System.out.println("dataFile created.");
        }

    } catch (IOException e) {
        System.err.println("An error occurred while creating the file: " + e.getMessage());
    }
  }

  public void setFilePath(File file) {
    dataFile = file;
    System.out.println("Current datafile: " + getDataFile());
  }

    public void saveModel() {
        UtilityJson.saveObjectToJsonFile(mixMavenModel, dataFile);
    }

    public void saveModel(MixMavenModel mixMavenModel) {
        UtilityJson.saveObjectToJsonFile(mixMavenModel, dataFile);
    }

    public MixMavenModel loadModel() {
        mixMavenModel = UtilityJson.loadObjectFromJson(dataFile);
        return mixMavenModel;
    }

    public MixMavenModel getModel() {
        return mixMavenModel;
    }

    public Drink deserializeDrink(String drink) {
        Gson gson = new Gson();

        // Parse the JSON string and create an instance of Drink
        return gson.fromJson(drink, Drink.class);
    }
}
