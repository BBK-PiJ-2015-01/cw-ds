/**
 *
 * @author Simon Baird
 */
public class StackImpl extends AbstractStack {

    private final int FIFO = 0;

    public StackImpl(List list) {
        super(list);
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
