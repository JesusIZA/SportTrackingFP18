package ua.jr.raichuk.Helpers.arrays;

/**
 * Abstract class contain methods for fill arrays
 *
 * @author Jesus Raichuk
 */
public abstract class FillArrays {
    /**
     * Fill array random values from zero to value was input
     * @param array - array will fill
     * @param to - top bound for values will random generate for fill array ones
     */
    public static final void fillIntArrayByRandomFromZero(final int[] array, final int to){
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)((Math.random() * to));
        }
    }
    /**
     * Fill array random values from bottom bound to top that was input
     * @param array - array will fill
     * @param from - bottom bound for values will random generate for fill array ones
     * @param to - top bound for values will random generate for fill array ones
     */
    public static final void fillIntArrayByRandomForBounds(final int[] array, final int from, final int to){
        for (int i = 0; i < array.length; i++) {
            if(from > to){
                if (from > 0 && to > 0) array[i] = (int)((from) + (Math.random() * (to - from + 1)));
                else if (from < 0 && to < 0) array[i] = (int)((from - 1) + (Math.random() * (to - from + 1)));
                else array[i] = (int)(from + (Math.random() * (to - from)));
            } else {
                if (from > 0 && to > 0) array[i] = (int)((from + 1) + (Math.random() * (to - from - 1)));
                else if (from < 0 && to < 0) array[i] = (int)((from) + (Math.random() * (to - from - 1)));
                else array[i] = (int)(from + (Math.random() * (to - from)));
            }

        }
    }
}
