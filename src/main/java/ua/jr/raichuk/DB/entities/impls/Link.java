package ua.jr.raichuk.DB.entities.impls;

import ua.jr.raichuk.DB.entities.Entity;

/**
 * @author Jesus Raichuk
 */
public class Link implements Entity {
    private int idL;
    private int idU;
    private int idP;
    private int idN;

    public Link() {
    }

    public Link(int idU, int idP, int idN) {
        this.idU = idU;
        this.idP = idP;
        this.idN = idN;
    }
    public Link(int id, int idU, int idP, int idN) {
        idL = id;
        this.idU = idU;
        this.idP = idP;
        this.idN = idN;
    }

    public int getIdL() {
        return idL;
    }

    public void setIdL(int idL) {
        this.idL = idL;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdN() {
        return idN;
    }

    public void setIdN(int idN) {
        this.idN = idN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (idL != link.idL) return false;
        if (idU != link.idU) return false;
        if (idP != link.idP) return false;
        return idN == link.idN;
    }

    @Override
    public int hashCode() {
        int result = idL;
        result = 31 * result + idU;
        result = 31 * result + idP;
        result = 31 * result + idN;
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "idL=" + idL +
                ", idU=" + idU +
                ", idP=" + idP +
                ", idN=" + idN +
                '}';
    }

    @Override
    public String getClassName() {
        return "Link";
    }
}
