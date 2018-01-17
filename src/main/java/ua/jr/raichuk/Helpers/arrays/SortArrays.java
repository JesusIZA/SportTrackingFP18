package ua.jr.raichuk.Helpers.arrays;
/**
 * Abstract class contain methods for sort arrays
 *
 * @author Jesus Raichuk
 */
public abstract class SortArrays {
    /**
     * Shell sort algorithm
     * @param array - array will sort
     */
    public static final void sortIntArrayByShell(final int[] array)
    {
        for (int d = array.length / 2; d >= 1; d /= 2)
            for (int i = d; i < array.length; i++)
                for (int j = i; j >= d && array[j-d] > array[j]; j -= d) {
                    int tmp;
                    tmp = array[j];
                    array[j] = array[j-d];
                    array[j-d] = tmp;
                }
    }
    /**
     * Bulb sort algorithm
     * @param array - array will sort
     */
    public static final void sortIntArrayByBulb(final int[] array)
    {
        boolean swapped = true;
        int z = 0;
        while (swapped){
            swapped = false;
            for (int i = 0; i < array.length - 1 - z; i++) {
                if (array[i] > array[i + 1]) {
                    int a = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = a;
                    swapped = true;
                }
            }
            z++;
        }
    }
}
