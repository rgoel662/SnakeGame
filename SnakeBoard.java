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

	public SnakeBoard(char[][] board) {
		this.board = board;
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		// Set the appropriate characters
		board[target.getY()][target.getX()] = '+';
		for (int i = 0; i < snake.size(); i++) {
			if (i == 0){
				System.out.println("this ran");
				board[snake.get(i).getValue().getY()][snake.get(i).getValue().getX()] = '@';
				System.out.println(board[snake.get(i).getValue().getY()][snake.get(i).getValue().getX()]);
				System.out.println(snake.get(i).getValue().getY() + " " + snake.get(i).getValue().getX());
			}else
				board[snake.get(i).getValue().getY()][snake.get(i).getValue().getX()] = '*';
		}

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
				System.out.print(board[i][j] + " ");
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