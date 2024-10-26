package com.example.calorimetro.model;

public class Food {
    private String name;
    private int calories;
    private boolean isSelected;

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
