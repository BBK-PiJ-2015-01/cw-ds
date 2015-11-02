/**
 *  Implementation of the FunctionalList backed by an ArrayList;
 * 
 * @author Simon Baird
 */
public class FunctionalArrayList extends ArrayList implements FunctionalList {

    private final int HEAD_INDEX = 0;

    public FunctionalArrayList() {
    }

    public FunctionalArrayList(int initialSize) {
        super(initialSize);
    }

    public FunctionalArrayList(Object[] fromArray) {
        super(fromArray);
    }

    @Override
    public ReturnObject head() {

        return get(HEAD_INDEX);
    }

    @Override
    public FunctionalList rest() {

        int size = size();
        int copyFrom = size == 0 ? 0 : 1; 
        Object[] constructorArray = ArrayUtilities.copy(asArray(), copyFrom);
        return new FunctionalArrayList(constructorArray);
    }
}
