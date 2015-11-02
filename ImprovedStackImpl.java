/**
 * 
 * @author Simon Baird
 */
public class ImprovedStackImpl implements ImprovedStack {

    private final List list;
    //
    private final int FIFO = 0;

    public ImprovedStackImpl(List list) {
        //trigger an exception if the list is null
        list.size();
        this.list = list;
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
