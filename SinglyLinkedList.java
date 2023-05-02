import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - an implementation of a singly linked list in java
 *  This will feature all the important methods of a singly linked list
 *  adding the functionality requried to link node objects to each other
 * 
 *	@author	Rishabh Goel
 *	@since	4/25/23
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {
		head = tail = null;
	}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		if (oldList == null)
			throw new NullPointerException("ERROR: null list");
		if (oldList.isEmpty())
			head = tail = null;
		else {
			head = new ListNode<E>(oldList.head.getValue());
			ListNode<E> curr = head;
			ListNode<E> oldCurr = oldList.head.getNext();
			while (oldCurr != null) {
				curr.setNext(new ListNode<E>(oldCurr.getValue()));
				curr = curr.getNext();
				oldCurr = oldCurr.getNext();
			}
			tail = curr;
		}
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = null;
		tail = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		ListNode<E> newNode = new ListNode<E>(obj, null);
		if (tail == null){
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}
		if (tail == newNode)
			return true;
		else 
			return false;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		ListNode<E> newNode = new ListNode<E>(obj);
		if (index == 0){
			newNode.setNext(head);
			head = newNode;
		} else {
			ListNode<E> ptr = head;
			for (int i = 0; i < index - 1; i++){
				if(ptr == null){
					throw new NoSuchElementException();
				}
				ptr = ptr.getNext();
			}
			newNode.setNext(ptr.getNext());
			ptr.setNext(newNode);
			if (newNode.getNext() == null){
				tail = newNode;
			}
		}
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		int size = 0;
		ListNode<E> ptr = head;
		while(ptr != null){
			size++;
			ptr = ptr.getNext();
		}
		return size;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		ListNode<E> ptr = head;
		for (int i = 1; i <= index; i++){
			if(ptr == null){
				throw new NoSuchElementException();
			}
			ptr = ptr.getNext();
		}
		return ptr;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		ListNode<E> ptr = head;
		for (int i = 1; i <= index; i++){
			if(ptr == null){
				throw new NoSuchElementException();
			}
			ptr = ptr.getNext();
		}
		E temp = ptr.getValue();
		ptr.setValue(obj);
		return temp;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		ListNode<E> ptr = head;
		if (index == 0){
			E temp = head.getValue();
			head = head.getNext();
			return temp;
		}
		for (int i = 1; i < index; i++){
			if(ptr == null){
				throw new NoSuchElementException();
			}
			ptr = ptr.getNext();
		}
		E temp = ptr.getNext().getValue();
		ptr.setNext(ptr.getNext().getNext());
		if (ptr.getNext() == null){
			tail = ptr;
		}
		return temp;
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		if (head == null){
			return true;
		} else {
			return false;
		}
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		ListNode<E> ptr = head;
		while (ptr != null){
			if (ptr.getValue().equals(object)){
				return true;
			}
			ptr = ptr.getNext();
		}
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		ListNode<E> ptr = head;
		int index = 0;
		while(ptr != null){
			if (ptr.getValue().equals(element)){
				return index;
			}
			ptr = ptr.getNext();
			index++;
		}
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	
}
