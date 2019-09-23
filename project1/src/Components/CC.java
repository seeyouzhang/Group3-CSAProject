package Components;
/**
 * 
 * @version 1.0 
 * @author Yukang Li
 *
 */
public class CC {
	private int size = 4;
	private int value;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public CC(int size, int value) {
		super();
		this.size = size;
		this.value = value;
	}
	public CC() {
		super();
	}
}