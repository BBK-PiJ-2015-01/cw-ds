
/**
 * A utility class to enable simple operations on arrays of Objects.
 *
 * @author Simon Baird
 */
public class ArrayUtilities {

    /**
     * Returns an identical copy of a supplied array.
     *
     * @param array to copy
     * @return an Object array identical to the supplied value or an empty
     * array if the supplied array is null
     */
    public static Object[] copy(Object[] array) {

        return copy(array, 0);
    }

    /**
     * Create a copy of an array starting at a specified position
     *
     * @param array to copy
     * @param from index offset to begin copy from
     * @return an Object array identical to the supplied value or an empty
     * array if the supplied array is null
     */
    public static Object[] copy(Object[] array, int from) {

        if (array == null || from >= array.length) {
            return new Object[0];
        }
        Object[] to = new Object[array.length - from];
        for (int i = 0; i < to.length; i++) {
            to[i] = array[i + from];
        }
        return to;
    }

    /**
     * Creates an empty array element by copying elements from a position to
     * their immediately adjacent position. Elements at the end of the array
     * will be lost. The element at @from will be set to null.
     *
     * @param array to be updated
     * @param from position of element to be freed.
     */
    public static void shuffleArrayDown(Object[] array, int from) {

        shuffleArrayDown(array, from, array.length - 2);
    }

    /**
     * Creates an empty array element by copying a range of elements from a
     * position to their immediately adjacent position. Elements at the end of
     * the array will be lost if the range exceeds the array size -1. The
     * element at @from will be set to null.
     *
     * @param array to be updated
     * @param from position of element to be freed.
     * @param to range end position
     */
    public static void shuffleArrayDown(Object[] array, int from, int to) {

        if (from < 0 || to < 0 || to <= from) {
            return; // Can't throw an exception
        }
        if (from >= array.length || to >= array.length) {
            return;
        }
        for (int i = to; i >= from; i--) {
            array[i + 1] = array[i];
        }
        array[from] = null;
    }

    /**
     * Overrides array elements with their immediate adjacent value and creates
     * and empty element at the end of the array.
     *
     * @param array to be updated
     * @param from start position of array copy.
     */
    public static void shuffleArrayUp(Object[] array, int from) {

        shuffleArrayUp(array, from, array.length - 1);
    }

    /**
     * Overrides array elements with their immediate adjacent value within an
     * range. The element at the end of the range will be set to null.
     *
     *
     * @param array to be updated
     * @param from start of range
     * @param to end of range
     */
    public static void shuffleArrayUp(Object[] array, int from, int to) {

        if (from >= array.length || to >= array.length) {
            return;
        }
        for (int i = from; i < to; i++) {
            array[i] = array[i + 1];
        }
        array[to] = null;
    }

    /**
     * Return an Object array extended or truncated by a specified length. The
     * relevant array elements will be copied where applicable.
     *
     * @param array original array
     * @param extendBy length to extend by. If negative the array will be
     * truncated.
     * @return an extended copy of the array with elements preserved where
     * applicable
     */
    public static Object[] extendArray(Object[] array, int extendBy) {

        //  Check that subtracting the extension from the array 
        //  length is not < 0
        //
        //
        if (array.length + extendBy < 0) {
            return array;
        }
        Object[] newArray = new Object[array.length + extendBy];
        // Can't use System.arraycopy() or Math.min()
        int limit = (array.length > newArray.length ? newArray.length : array.length);
        for (int i = 0; i < limit; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}
