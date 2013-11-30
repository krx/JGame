package jgame;

@SuppressWarnings("serial")
public class JGameException extends Exception {
	public JGameException(String message) {
		super(message);
	}
	
	public JGameException(String message, Throwable e) {
		super(message, e);
	}
}