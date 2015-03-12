package turtle;


public class BoardMaker {
	
	private boolean[][] board = new boolean[100][100];
	public int[] currentposition = new int[2];
	
	/**
	 * Receives the String parsed in and changes it to a String-Array with command and value. 
	 * 
	 * @param turtleProgram
	 * @return
	 * @throws OutOfBoundsException 
	 */
	public boolean[][] makeBoardFrom(String turtleProgram) throws OutOfBoundsException {
		TurtleParser parser = new TurtleParser();
		String[] command = new String[2];
		command = parser.parseIt(turtleProgram);
		String cmd = command[0];
		int value = Integer.parseInt(command[1]);
		
		if ( command[0] == "right" ) {
			if ( currentposition[0] + value >= 99 ) {
				throw new OutOfBoundsException();
			}
			for ( int i = 0; i < value; i++ ) {
				board[currentposition[0]+i][currentposition[1]] = true;
			}
			this.currentposition[0] = currentposition[0] + value;
		}	else if ( cmd == "left") {
			if ( currentposition[0] - value < 0 ) {
				throw new OutOfBoundsException();
			}
			for ( int i = 0; i < value; i++ ) {
				board[currentposition[0]-i][currentposition[1]] = true;
			}
			this.currentposition[0] = currentposition[0] - value;
		}	else if ( cmd == "jump" ) {
			if ( currentposition[1] - value >= 99 ) {
				throw new OutOfBoundsException();
			}
			this.currentposition[1] = currentposition[1] - value;
			board[currentposition[0]][currentposition[1]] = true;
		}	else if ( cmd == "down" ) {
			if ( currentposition[1] + value < 0 ) {
				throw new OutOfBoundsException();
			}
			for ( int i = 0; i < value; i++ ) {
				board[currentposition[0]][currentposition[1]+i] = true;
			}
			this.currentposition[1] = currentposition[1] + value;
			//board[currentposition[0]][currentposition[1]] = true;
		}
		
		return board;
				
	}

	/**
	 * Returns the initial board. And defines starting point for the turtle. 
	 * 
	 * @return
	 */
	public boolean[][] initialBoard() {
		board[0][99] = true;
		currentposition[0] = 30;
		currentposition[1] = 30;
		board[currentposition[0]][currentposition[1]] = true;
		return board;
		//throw new UnsupportedOperationException();
	}
	
	/**
	 * Changes the value with given params x,y of boolean inside the board. 
	 * @param x
	 * @param y
	 */
	public void setValue(int x, int y) {
		if ( board[x][y] == true ) {
			board[x][y] = false;
		}	else	{
			board[x][y] = true;
		}
	}
}
