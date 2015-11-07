/**
 * 
 * @author Simon Baird
 */
public class ImprovedStackImpl implements ImprovedStack {

    private final List list;
    //
    private final int FIFO = 0;

    /**
    * Creates a new abstract stack using the provided list as the
    * underlying data structure. If the supplied list is null then
    * a the underlying list will be implemented using an ArrayList.
    * 
    * @param list the list to be used 
    */
    public ImprovedStackImpl(List list) {

        this.list = list == null ? new ArrayList() : list;
    }

    @Override
    public ImprovedStack reverse() {

        List reverseList = new LinkedList();
        if (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                reverseList.add(list.get(i).getReturnValue());
            }
        }
        return new ImprovedStackImpl(reverseList);
    }

    @Override
    public void remove(Object object) {

        if (list.isEmpty() || object == null) {
            return;
        }
        int index = 0;
        while (true) {
            ReturnObject ro = list.get(index);
            if (ro.hasError()) {
                break;
            }
            if (object.equals(ro.getReturnValue())) {
                list.remove(index);
            } else {
                index++;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void push(Object item) {
        list.add(FIFO, item);
    }

    @Override
    public ReturnObject top() {
        return list.get(FIFO);
    }

    @Override
    public ReturnObject pop() {
        return list.remove(FIFO);
    }
}
