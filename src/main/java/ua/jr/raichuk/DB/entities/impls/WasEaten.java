package ua.jr.raichuk.DB.entities.impls;

import ua.jr.raichuk.DB.entities.Entity;

import java.sql.Date;

/**
 * @author Jesus Raichuk
 */
public class WasEaten implements Entity, Comparable {
    private int idWE;
    private int idP;
    private int idF;
    private Date dateWE;

    public WasEaten() {
    }

    public WasEaten(int idP, int idF, Date dateWE) {
        this.idP = idP;
        this.idF = idF;
        this.dateWE = dateWE;
    }

    public WasEaten(int id, int idP, int idF, Date dateWE) {
        idWE = id;
        this.idP = idP;
        this.idF = idF;
        this.dateWE = dateWE;
    }

    public int getIdWE() {
        return idWE;
    }

    public void setIdWE(int idWE) {
        this.idWE = idWE;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public Date getDateWE() {
        return dateWE;
    }

    public void setDateWE(Date dateWE) {
        this.dateWE = dateWE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WasEaten wasEaten = (WasEaten) o;

        if (idWE != wasEaten.idWE) return false;
        if (idP != wasEaten.idP) return false;
        if (idF != wasEaten.idF) return false;
        return dateWE != null ? dateWE.equals(wasEaten.dateWE) : wasEaten.dateWE == null;
    }

    @Override
    public int hashCode() {
        int result = idWE;
        result = 31 * result + idP;
        result = 31 * result + idF;
        result = 31 * result + (dateWE != null ? dateWE.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WasEaten{" +
                "idWE=" + idWE +
                ", idP=" + idP +
                ", idF=" + idF +
                ", dateWE=" + dateWE +
                '}';
    }

    @Override
    public String getClassName() {
        return "WasEaten";
    }

    @Override
    public int compareTo(Object o) {
        Date objDate = ((WasEaten)o).getDateWE();

        return (int)(this.getDateWE().getTime() - objDate.getTime());
    }
}
