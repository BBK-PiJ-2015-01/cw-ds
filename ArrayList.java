
public class ArrayList implements List {

    private int ARRAY_SEGMENT_SIZE = 100;
    //
    private Object[] array = new Object[ARRAY_SEGMENT_SIZE];
    //
    private int insertPosition = 0;

    public ArrayList() {

        initalize();
    }

    public ArrayList(int initialSize) {

        ARRAY_SEGMENT_SIZE = initialSize == 0 ? ARRAY_SEGMENT_SIZE : initialSize;
        initalize();
    }

    public ArrayList(Object[] fromArray) {

        if (fromArray == null || fromArray.length == 0) {
            initalize();
        }
        ARRAY_SEGMENT_SIZE = fromArray.length;
        initalize();
        for (Object o : fromArray) {
            if (o != null) {
                array[insertPosition++] = o;
            }
        }
    }

    private void initalize() {

        array = new Object[ARRAY_SEGMENT_SIZE];
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
            return new ReturnObjectImpl(null, EMPTY_STRUCTURE);
        }
        if (index < 0 || index >= insertPosition) {
            return new ReturnObjectImpl(null, INDEX_OUT_OF_BOUNDS);
        }
        return new ReturnObjectImpl(array[index], NO_ERROR);
    }

    @Override
    public ReturnObject remove(int index) {

        if (insertPosition == 0) {
            return new ReturnObjectImpl(null, EMPTY_STRUCTURE);
        }
        if (index >= insertPosition || index < 0) {
            return new ReturnObjectImpl(null, INDEX_OUT_OF_BOUNDS);
        }
        ReturnObject returnObject = new ReturnObjectImpl(array[index], NO_ERROR);
        shuffleArrayUp(index);
        // TO DO: truncate the array depending on threshhold size
        return returnObject;
    }

    @Override
    public ReturnObject add(int index, Object item) {

        if (item == null) {
            return new ReturnObjectImpl(null, INVALID_ARGUMENT);
        }
        if (index < 0 || index > insertPosition) {
            return new ReturnObjectImpl(null, INDEX_OUT_OF_BOUNDS);
        }
        shuffleArrayDown(index);
        array[index] = item;
        return new ReturnObjectImpl(null, NO_ERROR);
    }

    @Override
    public ReturnObject add(Object item) {

        if (item == null) {
            return new ReturnObjectImpl(null, INVALID_ARGUMENT);
        }
        insertItem(insertPosition, item);
        return new ReturnObjectImpl(null, NO_ERROR);
    }
    //
    //  
    //

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
            array = ArrayUtilities.extendArray(array, ARRAY_SEGMENT_SIZE);
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
            array = ArrayUtilities.extendArray(array, ARRAY_SEGMENT_SIZE);
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
