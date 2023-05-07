/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	
 *	@since	
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		board = new char[height][width];
		// initialize board to have spaces
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {

		// Print the top border
		System.out.print("+ ");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print("- ");
		}
		System.out.println("+");

		// Print the board
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				Coordinate temp = new Coordinate(j, i);
				if (snake.contains(temp)) {
					if (temp.equals(snake.get(0).getValue())) {
						System.out.print("@ ");
					} else {
						System.out.print("* ");
					}
				} else if (target.equals(temp)) {
					System.out.print("+ ");
				} else {
					System.out.print(board[i][j] + " ");
				}
			}
			System.out.println("|");
		}

		//print the bottom border
		System.out.print("+ ");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print("- ");
		}
		System.out.println("+");
	}
	
	/* Helper methods go here	*/
	
	/*	Accessor methods	*/
	public char[][] getBoard() { return board; }
	public int getHeight() { return board.length; }
	public int getWidth() { return board[0].length; }
	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(new Coordinate(3, 3));
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}