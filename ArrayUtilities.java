
public class ArrayUtilities {

    public static Object[] copy(Object[] array) {

        Object[] to = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            to[i] = array[i];
        }
        return to;
    }

    public static Object[] copy(Object[] array, int from) {

        if (from >= array.length) {
            return new Object[0];
        }
        Object[] to = new Object[array.length - from];
        for (int i = 0; i < to.length; i++) {
            to[i] = array[i+from];
        }
        return to;
    }

    public static void shuffleArrayDown(Object[] array, int from) {

        shuffleArrayDown(array, from, array.length - 2);
    }

    /**
     *
     *
     *
     * @param array
     * @param from
     * @param to
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

    public static void shuffleArrayUp(Object[] array, int from) {

        shuffleArrayUp(array, from, array.length - 1);
    }

    public static void shuffleArrayUp(Object[] array, int from, int to) {

        if (from >= array.length || to >= array.length) {
            return;
        }
        for (int i = from; i < to; i++) {
            array[i] = array[i + 1];
        }
        array[to] = null;
    }

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
