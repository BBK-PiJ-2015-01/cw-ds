public class ReturnObjectImpl implements ReturnObject {

    private final Object value;
    private final ErrorMessage message;

    /**
     * Create an empty ReturnObject
     *
     *
     */
//    public ReturnObjectImpl() {

//        this(null, null);
//    }

    /**
     * Create a ReturnObject with an object and a null message
     *
     * @param value An object of any type
     */
    private ReturnObjectImpl(Object value) {

        this(value, NO_ERROR);
    }

    /**
     * Create a ReturnObject with a message and a null object
     *
     * @param message the instance of ErrorMessage representing the error
     */
    private ReturnObjectImpl(ErrorMessage message) {

        this(null, message);
    }

    public ReturnObjectImpl(Object value, ErrorMessage message) {
        this.value = value;
        this.message = message == null ? NO_ERROR :message;
    }

    /**
     * Returns whether there has been an error
     *
     * @return whether there has been an error
     */
    @Override
    public boolean hasError() {

        return message != null && message != NO_ERROR;
    }

    /**
     * Returns the error message.
     *
     * This method must return NO_ERROR if and only if {
     *
     * @hasError} returns false.
     *
     * @return the error message
     */
    @Override
    public ErrorMessage getError() {
        
        return hasError() ? message : NO_ERROR;
    }

    /**
     * Returns the object wrapped in this ReturnObject, i.e. the result of the
     * operation if it was successful, or null if there has been error.
     *
     * Note that the output of this method must be null if {
     *
     * @see hasError} returns true, but the opposite is not true: if {
     * @see hasError} returns false, this method may or may not return null.
     *
     * @return the return value from the method or null if there has been an
     * error
     */
    @Override
    public Object getReturnValue() {

        return hasError() ? null : value;
    }

}
