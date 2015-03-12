package turtle;

public class OutOfBoundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception that shows that the turtle was off limits
	 * 
	 */
	public OutOfBoundsException() {
		super("turtle out of bounds");
	}
}
