package com.example.recipebookgui;

public class Ingredient {
    private String name;
    private int amount;
    private String measureUnit;


    //constructor
    public Ingredient(String name, int amount, String measureUnit) {
        this.name = name;
        this.amount = amount;
        this.measureUnit = measureUnit;
    }

    //getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}