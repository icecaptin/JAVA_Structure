package Chapter02;

public class IntStack {
	private int[] stk;
	private int capacity;
	private int ptr;
	
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {}
	}
	
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() {}
	}
	
	public IntStack(int maxlen) {
		ptr = 0;
		capacity = maxlen;
		try {
			stk = new int[capacity];
		}catch (OutOfMemoryError e){
			capacity = 0;
		}
	}
}
