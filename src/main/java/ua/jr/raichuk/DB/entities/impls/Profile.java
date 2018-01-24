package ua.jr.raichuk.DB.entities.impls;


import ua.jr.raichuk.DB.entities.Entity;

import java.sql.Date;

/**
 * @author Jesus Raichuk
 */
public class Profile implements Entity {
    private int idP;
    private String name;
    private String surname;
    private String sex;
    private Date birthday;
    private double height;
    private double weight;
    private int activeTime;

    public Profile() {
    }

    public Profile(String name, String surname, String sex, Date birthday, double height, double weight, int activeTime) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.activeTime = activeTime;
    }

    public Profile(int id, String name, String surname, String sex, Date birthday, double height, double weight, int activeTime) {
        idP = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.activeTime = activeTime;
    }


    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (idP != profile.idP) return false;
        if (Double.compare(profile.height, height) != 0) return false;
        if (Double.compare(profile.weight, weight) != 0) return false;
        if (activeTime != profile.activeTime) return false;
        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (surname != null ? !surname.equals(profile.surname) : profile.surname != null) return false;
        if (sex != null ? !sex.equals(profile.sex) : profile.sex != null) return false;
        return birthday != null ? birthday.equals(profile.birthday) : profile.birthday == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idP;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + activeTime;
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "idP=" + idP +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", weight=" + weight +
                ", activeTime=" + activeTime +
                '}';
    }

    @Override
    public String getClassName() {
        return "Profile";
    }
}
