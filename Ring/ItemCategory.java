package com.info.bitirmeprojesi;

public class ItemCategory {
    private String ItemCategory_key;
    private int Food;
    private int Drink;

    public ItemCategory() {
    }

    public ItemCategory(String itemCategory_key, int food, int drink) {
        ItemCategory_key = itemCategory_key;
        Food = food;
        Drink = drink;
    }

    public String getItemCategory_key() {
        return ItemCategory_key;
    }

    public void setItemCategory_key(String itemCategory_key) {
        ItemCategory_key = itemCategory_key;
    }

    public int getFood() {
        return Food;
    }

    public void setFood(int food) {
        Food = food;
    }

    public int getDrink() {
        return Drink;
    }

    public void setDrink(int drink) {
        Drink = drink;
    }
}
