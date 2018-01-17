package ua.jr.raichuk.Helpers.arrays;
/**
 * Abstract class contain methods for print arrays
 *
 * @author Jesus Raichuk
 */
public abstract class PrintArrays<T> {
    /**
     * Print int array by one row
     * @param array - array will print
     */
    public static final <T>void printArrayByOneRow(final T[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    /**
     * Print int array by rows
     * @param array - array will print
     */
    public static final <T>void printArrayByRows(final T[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    /**
     * Print int array by one row
     * @param array - array will print
     */
    public static final void printIntArrayByOneRow(final int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    /**
     * Print int array by rows
     * @param array - array will print
     */
    public static final void printIntArrayByRows(final int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    /**
     * Print int array by one row
     * @param array - array will print
     */
    public static final void printCharArrayByOneRow(final char[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    /**
     * Print int array by rows
     * @param array - array will print
     */
    public static final void printCharArrayByRows(final char[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

}
