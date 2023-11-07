package json;

import core.Drink;
import core.MixMavenModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;

/**
 * Manages data handling, including file I/O, serialization, and deserialization.
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

    /**
     * Returns the singleton instance of the DataHandler.
     *
     * @return The DataHandler instance.
     */
    public static synchronized DataHandler getInstance() {
        if (singleInstance == null)
            singleInstance = new DataHandler();
        return singleInstance;
    }

    /**
     * Gets the name of the current data file.
     *
     * @return The name of the data file.
     */
    public String getDataFile() {
        return dataFile.getName();
    }

    /**
     * Sets the file path for data handling.
     *
     * @param fileName The name of the data file to use.
     */
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

    /**
     * Saves the provided MixMavenModel to the data file.
     *
     * @param mixMavenModel The model to be saved.
     */
    public void saveModel(MixMavenModel mixMavenModel) {
        UtilityJson.saveObjectToJsonFile(mixMavenModel, dataFile);
    }

    /**
     * Loads the MixMavenModel from the data file.
     *
     * @return The loaded MixMavenModel.
     */
    public MixMavenModel loadModel() {
        mixMavenModel = UtilityJson.loadObjectFromJson(dataFile);
        return mixMavenModel;
    }

    /**
     * Deserializes a JSON string into a Drink object.
     *
     * @param drink The JSON string representing a Drink.
     * @return A Drink object deserialized from the JSON string.
     */
    public Drink deserializeDrink(String drink) {
        Gson gson = new Gson();

        // Parse the JSON string and create an instance of Drink
        return gson.fromJson(drink, Drink.class);
    }
}
