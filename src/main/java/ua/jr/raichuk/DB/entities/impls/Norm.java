package ua.jr.raichuk.DB.entities.impls;

import ua.jr.raichuk.DB.entities.Entity;

/**
 * @author Jesus Raichuk
 */
public class Norm implements Entity {
    private int idN;
    private int calories;
    private int proteins;

    public Norm() {
    }

    public Norm(int calories, int proteins) {
        this.calories = calories;
        this.proteins = proteins;
    }
    public Norm(int id, int calories, int proteins) {
        idN = id;
        this.calories = calories;
        this.proteins = proteins;
    }

    public int getIdN() {
        return idN;
    }

    public void setIdN(int idN) {
        this.idN = idN;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Norm norm = (Norm) o;

        if (idN != norm.idN) return false;
        if (calories != norm.calories) return false;
        return proteins == norm.proteins;
    }

    @Override
    public int hashCode() {
        int result = idN;
        result = 31 * result + calories;
        result = 31 * result + proteins;
        return result;
    }

    @Override
    public String toString() {
        return "Norm{" +
                "idN=" + idN +
                ", calories=" + calories +
                ", proteins=" + proteins +
                '}';
    }

    @Override
    public String getClassName() {
        return "Norm";
    }
}
