/**
 *	The Snake is a singly linked list with snake behaviors.
 *	@author	
 *	@since	
 */
public class Snake extends SinglyLinkedList<Coordinate>{
	
	// Fields
	
	// Constructor, takes in coordinate and creates the rest of the snake 4 spaces under
	public Snake(Coordinate location) { 
		add(location);
		for (int i = 1; i <= 4; i++) {
			add(new Coordinate(location.getX(), location.getY() + i));
		}
	}
	
	// Move the snake in the given direction
	public void move(Coordinate newCoord){
		Coordinate oldVal = new Coordinate(get(0).getValue().getX(), get(0).getValue().getY());

		set(0, newCoord);
		// Move the snake
		for (int i = 1; i < size(); i++) {
			Coordinate temp = new Coordinate(get(i).getValue().getX(), get(i).getValue().getY());
			set(i, oldVal);
			oldVal = temp;
		}
	}

	// Grow the snake by one
	public void eat(Coordinate target) {
		add(0, target);
	}
}