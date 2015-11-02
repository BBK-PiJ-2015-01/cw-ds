/**
 * An array backed implementation of the List interface.
 *
 * @author Simon Baird
 */
public class ArrayList implements List {

    /**
     * Initial size of array unless otherwise specified.
     */
    public final int ARRAY_SEGMENT_SIZE = 100;
    private final int segmentSize;
    //
    private Object[] array;
    //
    private int insertPosition = 0;

    /**
     * Creates an ArrayList with an initial size equal to ARRAY_SEGMENT_SIZE.
     */
    public ArrayList() {

        segmentSize = ARRAY_SEGMENT_SIZE;
        initalize();
    }

    /**
     * Creates an ArrayList by supplying an initial size.
     *
     * @param initialSize the initial size of the array. If not greater than 0
     * then ARRAY_SEGMENT_SIZE will be substituted.
     */
    public ArrayList(int initialSize) {

        segmentSize = initialSize <= 0 ? ARRAY_SEGMENT_SIZE : initialSize;
        initalize();
    }

    /**
     * Creates an ArrayList from an existing array. Null values in the array
     * will not be stored.
     *
     * @param fromArray an array of objects to populate the list. If null then
     * an empty list will be created. Null values in the supplied array will be
     * ignored.
     */
    public ArrayList(Object[] fromArray) {

        if (fromArray == null || fromArray.length == 0) {
            initalize();
        }
        segmentSize = fromArray.length;
        initalize();
        for (Object o : fromArray) {
            if (o != null) {
                array[insertPosition++] = o;
            }
        }
    }

    private void initalize() {

        array = new Object[segmentSize];
    }

    @Override
    public boolean isEmpty() {
        return getListSize() == 0;
    }

    @Override
    public int size() {
        return getListSize();
    }

    @Override
    public ReturnObject get(int index) {

        if (insertPosition == 0) {
            return new ReturnObjectImpl(null, ErrorMessage.EMPTY_STRUCTURE);
        }
        if (index < 0 || index >= insertPosition) {
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        }
        return new ReturnObjectImpl(array[index], ErrorMessage.NO_ERROR);
    }

    @Override
    public ReturnObject remove(int index) {

        if (insertPosition == 0) {
            return new ReturnObjectImpl(null, ErrorMessage.EMPTY_STRUCTURE);
        }
        if (index >= insertPosition || index < 0) {
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        }
        ReturnObject returnObject = new ReturnObjectImpl(array[index], ErrorMessage.NO_ERROR);
        shuffleArrayUp(index);
        // TO DO: truncate the array depending on threshhold size
        return returnObject;
    }

    @Override
    public ReturnObject add(int index, Object item) {

        if (item == null) {
            return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);
        }
        if (index < 0 || index > insertPosition) {
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        }
        shuffleArrayDown(index);
        array[index] = item;
        return new ReturnObjectImpl(null, ErrorMessage.NO_ERROR);
    }

    @Override
    public ReturnObject add(Object item) {

        if (item == null) {
            return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);
        }
        insertItem(insertPosition, item);
        return new ReturnObjectImpl(null, ErrorMessage.NO_ERROR);
    }
    /**
     * Convenience method to return the contents of the list as an array.
     * 
     * @return list contents as an array
     */
    public Object[] asArray() {

        return ArrayUtilities.copy(array);
    }

    //
    //  Private methods
    //
    private int getListSize() {

        int listSize = 0;
        for (Object o : array) {
            if (o != null) {
                listSize++;
            }
        }
        return listSize;
    }

    private void insertItem(int index, Object item) {

        if (index >= array.length) {
            array = ArrayUtilities.extendArray(array, segmentSize);
        }
        if (index > insertPosition) {
            index = insertPosition;
        }
        if (index < insertPosition) {
            shuffleArrayDown(index);
        }
        array[index] = item;
        insertPosition++;
    }

    private void shuffleArrayDown(int indexToInsert) {

        if (insertPosition == array.length) {
            array = ArrayUtilities.extendArray(array, segmentSize);
        }
        if (indexToInsert > insertPosition) {
            indexToInsert = insertPosition;
        }
        ArrayUtilities.shuffleArrayDown(array, indexToInsert, insertPosition);
        insertPosition++;
    }

    private void shuffleArrayUp(int indexToRemove) {

        if (isEmpty() || indexToRemove < 0) {
            return;
        }
        ArrayUtilities.shuffleArrayUp(array, indexToRemove, insertPosition);
        insertPosition--;
    }
}
