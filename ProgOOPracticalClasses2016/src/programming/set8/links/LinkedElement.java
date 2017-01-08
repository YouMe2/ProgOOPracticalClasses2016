package programming.set8.links;

public class LinkedElement<T> {

	private T t = null;

	private LinkedElement<T> nextElement = null;

	public LinkedElement(T t) {
		this.t = t;
	}

	/**
	 * Returns the value of the i-th linked element.
	 * 
	 * @param i
	 *            0-based index of the element whose value to return.
	 * @return the i-th element's value, or {@code null} if there is no element
	 *         with that index.
	 */
	public T get(int i) {

		if (i == 0) {
			return t;
		} else if (nextElement != null){
			return nextElement.get(i - 1);
		} else {
			return null;
		}
	}

	/**
	 * Sets the value of the i-th linked element to the given value. If there is
	 * no i-th linked element, nothing happens.
	 * 
	 * @param i
	 *            0-based index of the element whose value to return.
	 * @param newVal
	 *            the new value to set.
	 */
	public void set(int i, T newVal) {

		if (i == 0) {
			this.t = newVal;
		} else if(nextElement != null){
			nextElement.set(i - 1, newVal);
		}
		
	}

	/**
	 * Returns the index of the first occurrence of a linked element carrying
	 * the given value in the list.
	 * 
	 * @param val
	 *            the value to search for.
	 * @return index where the value was found, or -1 if it's not in any of the
	 *         linked elements.
	 */
	public int firstIndexOf(T val) {

		if (t == val) {
			return 0;
		} else if(nextElement == null){
			return -1;
		} else {
			int re = nextElement.firstIndexOf(val);
			if (re != -1){
				return re + 1;
			} else {
				return -1;
			}
		} 

	}

	/**
	 * Adds a new linked element holding the given value at the end of the linked elements.
	 * 
	 * @param newVal
	 *            the new value.
	 */
	public void add(T newVal) {
		
		if( nextElement == null){
			nextElement = new LinkedElement<T>(newVal);
		} else {
			nextElement.add(newVal);
		}
	}

	/**
	 * Removes the i-th element from the linked elements. If {@code i == 0},
	 * this will effectively remove the head element. Thus, this method returns
	 * the linked element that is the new head element.
	 * 
	 * @param i
	 *            index of the element to remove.
	 * @return the new head element.
	 */
	public LinkedElement<T> remove(int i) {
		
		if (i == 0) {
			
			return nextElement;
			
		} else if (i == 1){
			
			nextElement = nextElement.nextElement;
			return this;
		} else if(nextElement != null){
			nextElement.remove(i-1);
			return this;
		} else {
			return this;
		}
	}
}
