package springboot;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import core.Drink;
import core.MixMavenModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller for handling RESTful API requests related to the MixMaven application.
 */
@CrossOrigin
@RestController
@RequestMapping()
public class MixMavenController {

    @Autowired
    private MixMavenService mixMavenService;

    /**
     * Retrieve the MixMaven model.
     *
     * @return The MixMavenModel instance.
     */
    private MixMavenModel getMixMavenModel() {
        return mixMavenService.getMixMavenModel();
    }

    /**
     * Automatically save the MixMavenModel.
     */
    private void autoSaveMixMaven() {
        mixMavenService.autoSaveMixMaven();
    }

    /**
     * Retrieves a list of drinks from the MixMavenModel.
     *
     * @return A list of Drink objects.
     */
    @GetMapping("/drinks")
    public List<Drink> drinks() {
        return getMixMavenModel().getDrinks();
    }

    /**
     * Update a drink by its ID.
     *
     * @param id The ID of the drink to replace.
     * @param drinkString The JSON representation of the new drink.
     * @return True if the drink was successfully replaced.
     */
    @PutMapping(path = "/drinks/{id}")
    public boolean replaceDrink(@PathVariable("id") String id, @RequestBody String drinkString) {
        Drink drink = mixMavenService.deserializeDrink(drinkString);
        getMixMavenModel().replaceDrink(id, drink);
        autoSaveMixMaven();
        return true;
    }

    /**
     * Delete a drink by its ID.
     *
     * @param id The ID of the drink to delete.
     * @return True if the drink was successfully deleted.
     */
    @DeleteMapping(path = "/drinks/{id}")
    public boolean deleteDrink(@PathVariable("id") String id) {
        getMixMavenModel().removeDrink(id);
        autoSaveMixMaven();
        return true;
    }

    /**
     * Add a new drink to the mixMavenModel.
     *
     * @param drinkString The JSON representation of the new drink.
     * @return True if the drink was successfully added.
     */
    @PostMapping(path = "/drinks")
    public boolean addDrink(@RequestBody String drinkString) {
        Drink drink = mixMavenService.deserializeDrink(drinkString);
        getMixMavenModel().addDrink(drink);
        autoSaveMixMaven();
        return true;
    }

    /**
     * Set the file path for the MixMavenModel.
     *
     * @param fileName The file path to set.
     * @return True if the file path was successfully set.
     */
    @PostMapping(path = "/setfile")
    public boolean setFilePath(@RequestBody String fileName) {
        mixMavenService.setFilePath(fileName);
        autoSaveMixMaven();
        return true;
    }
}


