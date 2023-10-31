package springboot;

import core.Drink;
import json.DataHandler;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MixMavenService {

    private List<Drink> drinks = new ArrayList<>();

    public MixMavenService() {
        //this.drinks = DataHandler.getDrinks();
    }
}
