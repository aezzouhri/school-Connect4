package game.components;

import main.GridInsertException;

public class Grid {
	
	private int width;
	private int height;
	private char grid[][];
	
	// Grid constructor
	public Grid(int width, int height) {
		this.height = height;
		this.width = width;
		this.grid = new char[height][width];
	}
	
	public int getHeight() {
		return this.height;		
	}
	public int getWidth() {
		return this.width;		
	}
	
	// Fill the grid with dot to seem empty
	public void initGrid() {
		int i,j;
		for (i=0; i<width; i++) {
			for (j=0; j<height ; j++) {
				grid[j][i]='.';
			}		
		}	
	}
	
	// This method displays the gird
	public void displayGrid() {
		int i,j,k;
		
		for (k=1; k<width+1; k++) {
			System.out.print(k +"  ");
		}
		System.out.print("\n");
		
		for (j=0; j<height; j++) {
			for(i=0; i<width; i++) {
				System.out.print(grid[j][i]+"  ");
			}
			System.out.print("\n");
		}	
	}
	
	// Insert the token in the column chosen
	public int insertToken(int column, Token token) throws GridInsertException {
		int i;
		char c = token.getChar();
		for(i=height-1; i>=0; i--) {
			if(grid[i][column] == '.') {
				grid[i][column] = c;
				return i;
			}
		}
		throw new GridInsertException(column+1);
	}
	
	// In order to check if one player won
	// this method checks if the token specified fills the condition for the victory
	public int checkWin(int line, int column, Token token, int nbToken2Win) {
		char c = token.getChar();
		int i,j;
		int counter = 1;
		
		// LINE
		for (i=column-1; i>=0; i--) {
			if (grid[line][i] == c)
				counter ++;
			else
				break;
		}
		for (i=column+1; i<width; i++) {
			if (grid[line][i] == c)
				counter ++;
			else
				break;
		}
		if (counter >= nbToken2Win)
			return 1;
		
		
		// COLUMN
		counter = 1;
		for (i=line-1; i>=0; i--) {
			if (grid[i][column] == c)
				counter ++;
			else 
				break;
		}
		for (i=line+1; i<height; i++) {
			if (grid[i][column] == c)
				counter ++;
			else
				break;
		}
		if (counter >= nbToken2Win)
			return 1;
		
	// DIAGONAL 1
		counter=1;
		for (i=column-1, j=1; i>=0 && line-j>=0; i--, j++) {
			if (grid[line-j][i] == c)
				counter ++;
			else
				break;
		}
		for (i=column+1, j=1; i<width && line+j<height; i++, j++) {
			if (grid[line+j][i] == c)
				counter ++;
			else
				break;
		}
		if (counter >= nbToken2Win)
			return 1;
		
		// DIAGONAL 2
		counter=1;
		for (i=column-1, j=1; i>=0 && line+j<height ; i--, j++) {
			if (grid[line+j][i] == c)
				counter ++;
			else
				break;
		}
		for (i=column+1, j=1; i<width && line-j>=0; i++, j++) {
			if (this.grid[line-j][i] == c)
				counter ++;
			else
				break;
		}
		if (counter >= nbToken2Win)
			return 1;

		return 0;		
		
	}

	// Check if the grid is full
	public int checkEquality() {
		for (int i=0; i<this.width; i++) {
			if (this.grid[0][i] == '.')
				return 0;			
		}
		return 1;
	}
	
}
