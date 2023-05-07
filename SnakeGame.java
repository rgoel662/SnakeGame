import java.io.PrintWriter;
import java.util.Scanner;

/**
 *	Snake Game - <Description goes here>
 *	
 *	@author	
 *	@since	
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game
	private boolean endGame;	// whether the game is over or not

	/*	Constructor	*/
	public SnakeGame() { 
		board = new SnakeBoard(10, 15);
		snake = new Snake(new Coordinate(3, 3));
		target = new Coordinate(1, 7);
		score = 0;
		endGame = false;
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame game = new SnakeGame();
		game.run();
	}
	
	/**
	 * Calls all the methods to run the game
	 */
	public void run(){
		printIntroduction();
		play();
		endGame();
	}

	/**
	 * Plays the game
	 */

	public void play(){
		while (!endGame){
			board.printBoard(snake, target);
			Coordinate newLoc = getInput();

			if (newLoc == null)
				continue;

			if (snake.contains(newLoc) || newLoc.getX() < 0 || newLoc.getX() >= board.getWidth() || 
				newLoc.getY() < 0 || newLoc.getY() >= board.getHeight()){
				endGame = true;
			}

			if (newLoc.equals(target)){
				snake.eat(target);
				chooseTarget();
				score++;
			} else {
				snake.move(newLoc);
			}

		}
	}

	/**
	 * Chooses a new target for the snake
	 */
	private void chooseTarget(){
		Coordinate newTarget = new Coordinate((int)(Math.random() * board.getWidth()), 
												(int)(Math.random() * board.getHeight()));
		while (snake.contains(newTarget)){
			newTarget = new Coordinate((int)(Math.random() * board.getWidth()), 
										(int)(Math.random() * board.getHeight()));
		}
		target = newTarget;
	}

	/**
	 * Gets the input from the user using Prompt
	 * 
	 * @return the coordinate the user wants to move to
	 */
	private Coordinate getInput(){
		boolean gotten = false;
		while (!gotten){
			String input = Prompt.getString("Score: "+ score +" (w - North, s - South, d - East, a - West, h - Help)");
			if (input.equals("w")){
				return new Coordinate(snake.get(0).getValue().getX(), snake.get(0).getValue().getY() - 1);
			} else if (input.equals("s")){
				return new Coordinate(snake.get(0).getValue().getX(), snake.get(0).getValue().getY() + 1);
			} else if (input.equals("d")){
				return new Coordinate(snake.get(0).getValue().getX() + 1, snake.get(0).getValue().getY());
			} else if (input.equals("a")){
				return new Coordinate(snake.get(0).getValue().getX() - 1, snake.get(0).getValue().getY());
			} else if (input.equals("h")) {
				helpMenu();
				gotten = true;
			} else if (input.equals("f")) {
				endGame = true;
				saveGame();
				gotten = true;
			} else if (input.equals("r")) {
				restoreGame();
				gotten = true;
			} else if (input.equals("q")) {
				String quit = Prompt.getString("Are you sure you want to quit? (y/n)");
				if (quit.equals("y")) {
					System.exit(0);
				} else {
					System.out.println("Invalid command");
				}
			}
		}
		return null;
	}
	
	/**
	 * Saves the game, then ends it.
	 */
	public void saveGame(){
		String decision = Prompt.getString("Save game [y/n]");

		if (decision.equals("n")) return;

		System.out.println("\nSaving game to snakeGameSave.txt");
		
		PrintWriter output = FileUtils.openToWrite("snakeGameSave.txt");

		output.println(board.getHeight() + " " + board.getWidth());

		output.println(target.getX() + " " + target.getY());
		for (int i = 0; i < snake.size(); i++) {
			output.println(snake.get(i).getValue().getX() + " " + snake.get(i).getValue().getY());
		}

		output.close();
	}

	/**
	 * Restores the game
	 */
	public void restoreGame(){
		Scanner reader = FileUtils.openToRead("snakeGameSave.txt");

		board = new SnakeBoard(reader.nextInt(), reader.nextInt());
		target = new Coordinate(reader.nextInt(), reader.nextInt());
		snake.clear();

		while (reader.hasNext()) {
			snake.add(new Coordinate(reader.nextInt(), reader.nextInt()));
		}

		score = snake.size() - 5;
	}

	public void endGame(){
		System.out.println("\nGame is over");
		System.out.println("\nScore = " + score);
		System.out.println("\nThanks for playing SnakeGame!");
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
}