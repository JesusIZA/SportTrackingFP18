package ua.jr.raichuk.DB.entities.impls;

import ua.jr.raichuk.DB.entities.Entity;

/**
 * @author Jesus Raichuk
 */
public class Food implements Entity {
    private int idF;
    private String name;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;

    public Food() {
    }

    public Food(String name, double calories, double proteins, double fats, double carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
    public Food(int id, String name, double calories, double proteins, double fats, double carbohydrates) {
        idF = id;
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (idF != food.idF) return false;
        if (Double.compare(food.calories, calories) != 0) return false;
        if (Double.compare(food.proteins, proteins) != 0) return false;
        if (Double.compare(food.fats, fats) != 0) return false;
        if (Double.compare(food.carbohydrates, carbohydrates) != 0) return false;
        return name != null ? name.equals(food.name) : food.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idF;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(calories);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(proteins);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fats);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(carbohydrates);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Food{" +
                "idF=" + idF +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }

    @Override
    public String getClassName() {
        return "Food";
    }
}
