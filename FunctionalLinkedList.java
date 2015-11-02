public class FunctionalLinkedList extends LinkedList implements FunctionalList {

    @Override
    public ReturnObject head() {

        return get(0);
    }

    @Override
    public FunctionalList rest() {

        int size = size();
        int copyFrom = size == 0 ? 0 : 1;
        Object[] constructorArray = ArrayUtilities.copy(asArray(), copyFrom);
        return new FunctionalArrayList(constructorArray);
    }
}
