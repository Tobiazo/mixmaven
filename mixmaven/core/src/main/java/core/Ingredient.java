package core;

import java.io.Serializable;

public class Ingredient implements Serializable {
    
    private String name;
    private int alcoholPercentage; 
    private double amount; 
    private String measurementUnit; 
    private String type;

    public Ingredient(String name, int alcoholPercentage, double amount, String measurementUnit, String type) {
        this.name = name;
        this.amount = amount; 
        this.alcoholPercentage = alcoholPercentage;
        this.measurementUnit = measurementUnit;
        validateType(type);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(int alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } 

    
    public boolean validateType(String type) {
        if (type.equals("alcohol") || type.equals("mixer") || type.equals("extras")) {
            return true; 
        }
        return false; 
    }

    @Override
    public String toString() {
      return "Ingredient [name=" + name + ", type=" + type + "]";
    }

    

}

