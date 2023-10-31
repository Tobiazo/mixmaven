package springboot;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import core.Drink;
import core.MixMavenModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
@RequestMapping()
public class MixMavenController {

    @Autowired
    private MixMavenService mixMavenService;

    private MixMavenModel getMixMavenModel()  {
        return mixMavenService.getMixMavenModel();
    }

    private void autoSaveMixMaven() {
        mixMavenService.autoSaveMixMaven();
    }

    @GetMapping("/drinks")
    public List<Drink> drinks() {
        // return getMixMavenModel().getDrinks();
        return getMixMavenModel().getDrinks();
    }

    @PutMapping(path = "/drinks/{id}")
    public boolean addDrink(@PathVariable("id") String id, @RequestBody Drink drink) {
        getMixMavenModel().removeDrink(id);
        getMixMavenModel().addDrink(drink);
        autoSaveMixMaven();
        return true;
    }

    @DeleteMapping(path = "/drinks/{id}")
    public boolean deleteDrink(@PathVariable("id") String id) {
        getMixMavenModel().removeDrink(id);
        autoSaveMixMaven();
        return true;
    }

    @PostMapping(path = "/drinks")
    public boolean addDrink(@RequestBody String drinkstring) {
        Drink drink = mixMavenService.deserializeDrink(drinkstring);
        getMixMavenModel().addDrink(drink);
        autoSaveMixMaven();
        return true;
    }
} 


