/**
 *
 * @author Simon Baird
 */
public class StackImpl extends AbstractStack {

    private final int FIFO = 0;

    /**
     * Creates a new abstract stack using the provided list as the
     * underlying data structure. If the supplied list is null then
     * a the underlying list will be implemented using an ArrayList.
     * 
     * @param list the list to be used 
     */
    public StackImpl(List list) {

        super(list == null ? new ArrayList() : list);
    }

    @Override
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public int size() {
        return internalList.size();
    }
    /**
    * Adds an element at the top of the stack. If the item is null then the request is ignored.
    */
    @Override
    public void push(Object item) {
        internalList.add(FIFO, item);
    }

    @Override
    public ReturnObject top() {
        return internalList.get(FIFO);
    }

    @Override
    public ReturnObject pop() {
        return internalList.remove(FIFO);
    }
}
