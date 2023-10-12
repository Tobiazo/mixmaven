package core;

import static core.Constants.VALIDTYPES;

import java.io.Serializable;

public final class Ingredient implements Serializable {

    private String name;
    private int alcoholPercentage;
    private double amount;
    private String unit;
    private String type;

    /**
     * Constructor for ingredients of type mixer and extras.
     *
     * @param name
     * @param amount
     * @param unit
     * @param type
     */
    public Ingredient(String name, double amount, String unit, String type) {
        if (!VALIDTYPES.contains(type))
            throw new IllegalArgumentException();
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.type = type;
    }

    /**
     * Constructor for ingredients of type "alcohol".
     *
     * @param name
     * @param alcoholPercentage
     * @param amount
     * @param unit
     * @param type
     */
    public Ingredient(String name, int alcoholPercentage, double amount, String unit, String type) {
        this(name, amount, unit, type);
        if (alcoholPercentage > 100 || alcoholPercentage < 0) throw new IllegalArgumentException();
        this.alcoholPercentage = alcoholPercentage;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!VALIDTYPES.contains(type))
            throw new IllegalArgumentException("Not valid type");
        this.type = type;
    }

    @Override
    public String toString() {
        if (alcoholPercentage == 0) {
            return amount + " " + unit + " " + name;
        }
        return amount + " " + unit + " " + name + " " + alcoholPercentage + "%";
    }
}

