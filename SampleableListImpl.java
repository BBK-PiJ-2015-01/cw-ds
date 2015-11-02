/**
 *
 * @author Simon Baird
 */
public class SampleableListImpl extends ArrayList implements SampleableList {

    public SampleableListImpl() {
    }

    public SampleableListImpl(int initialSize) {
        super(initialSize);
    }

    public SampleableListImpl(Object[] fromArray) {
        super(fromArray);
    }

    @Override
    public SampleableList sample() {

        int size = size();
        if (size == 0) {
            return new SampleableListImpl();
        } else {

            SampleableListImpl returnList = new SampleableListImpl();
            int sampleSize = size % 2 == 1 ? size : size - 1;
            for (int i = 0; i < sampleSize; i += 2) {
                returnList.add(get(i).getReturnValue());
            }
            return returnList;
        }
    }
}
