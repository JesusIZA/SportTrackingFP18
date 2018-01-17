package ua.jr.raichuk.Helpers.lists;

import java.util.List;

/**
 * Abstract class contain methods for print lists
 *
 * @author Jesus Raichuk
 */
public abstract class PrintLists {
    /**
     * Print list by one row
     * @param list - list will print
     */
    public static final void printListByOneRow(final List list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
    /**
     * Print list by rows
     * @param list - list will print
     */
    public static final void printListByRows(final List list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
