package springboot;

import core.Drink;
import core.MixMavenModel;
import json.DataHandler;
import org.springframework.stereotype.Service;

@Service
public class MixMavenService {

    private MixMavenModel mixMavenModel;
    private DataHandler dataHandler;

    /**
   * Initializes the service with a specific TodoModel.
   *
   * @param todoModel the TodoModel
   */
  public MixMavenService() {
    this.dataHandler = DataHandler.getInstance();
    dataHandler.setFilePath("springbootserver-data.json");
    this.mixMavenModel = dataHandler.loadModel();
  }

  public MixMavenModel getMixMavenModel() {
    return mixMavenModel;
  }

  public void autoSaveMixMaven() {
    dataHandler.saveModel(mixMavenModel);
  }

  public Drink deserializeDrink(String drink) {
    return dataHandler.deserializeDrink(drink);
  }





   




    // /**
    //  * Initializes the service with a specific MixMavenModel
    //  * 
    //  * @param mixMaven
    //  */
    // public MixMavenService(MixMavenModel mixMavenModel) {
    //     this.mixMavenModel = mixMavenModel;
    //     //DataHandler.setFile("springbootserver-data.json");
    // }
    
    // // public MixMavenService() {
    // //     this(createDefaultMixMavenModel());
    // // }

    // public MixMavenModel getMixMavenModel() {
    //     return mixMavenModel;
    // }

    // public void setMixMavenModel(MixMavenModel mixMavenModel) {
    //     this.mixMavenModel = mixMavenModel;
    // }

    // public MixMavenModel createDefaultMixMavenModel() {
    //     // URL url  = MixMavenService.class.getResource("local-data.json");
    //     // if (url != null) {
    //     //     DataHandler.setFilePath(url); //TODO
    //     //     try (DataHandler.loadMixMavenModel();) {
    //     //         return DataHandler.getMixMavenModel();
    //     //     } catch (IOException e) {
    //     //         System.out.println("Could not read local-data.json");
    //     //     }
    //     // }
    //     // MixMavenModel mixMavenModel = new MixMavenModel();
    //     // Ingredient ingredient1 = new Ingredient("This is an ingredient", 10, 20, "ml", "alchohol");
    //     // Drink drink1 = new Drink("Dette er en drink");
    //     // drink1.addIngredient(ingredient1);
    //     // return mixMavenModel;
    //     return mixMavenModel;
    // }

    // public void autoSaveMixMavenModel() {
    //     try {
    //         // DataHandler.save();
    //     } catch (Exception e) {
    //         System.err.println("couldnt save " + e);
    //     }
    // }
}