package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {
	private Random random;
	private int score, maxRows, maxCols;
	
	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.random = random;
		score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		boolean isGameOver = false;
		
		for(int i = 0; i < getMaxCols(); i++) {
			if(getBoardCell(board.length - 1, i) != BoardCell.EMPTY) {
				isGameOver = true;
			}
		}
		return isGameOver;
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		if (!this.isGameOver()) {
			for (int row = getMaxRows() - 1; row >= 0; row--) {
				for (int col = 0; col < getMaxCols(); col++) {
					if (getBoardCell(row, col) != BoardCell.EMPTY) {
						setBoardCell(row + 1, col, this.getBoardCell(row, col));
					}
					if (row == 0) {
						setBoardCell(row, col, BoardCell.getNonEmptyRandomBoardCell(random));
					}
				}
			}
		}
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {

		BoardCell wholeBoard = board[rowIndex][colIndex];

		if (!this.isGameOver()) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;

			int colLeft = colIndex;
			int colRight = colIndex;
			int rowUp = rowIndex;
			int rowDown = rowIndex;

			while (colLeft > 0) {
				if (board[rowIndex][--colLeft] == wholeBoard) {
					board[rowIndex][colLeft] = BoardCell.EMPTY;
					score++;
				}

				while (colRight < board[rowIndex].length - 1) {
					if (board[rowIndex][++colRight] == wholeBoard) {
						board[rowIndex][colRight] = BoardCell.EMPTY;
						score++;
					}
				}

				while (rowUp > 0) {
					if (board[--rowUp][colIndex] == wholeBoard) {
						board[rowUp][colIndex] = BoardCell.EMPTY;
						score++;
					}
				}

				while (rowDown < board.length - 1) {
					if (board[++rowDown][colIndex] == wholeBoard) {
						board[rowDown][colIndex] = BoardCell.EMPTY;
						score++;
					} else {
						rowDown = board.length;
					}
				}

				while (rowUp > 0 & colLeft > 0) {
					if (board[--rowUp][--colLeft] == wholeBoard) {
						board[rowUp][colLeft] = BoardCell.EMPTY;
						score++;
					}
				}

				while (rowDown < board.length - 1 & colRight < board[rowIndex].length - 1) {
					if (board[++rowDown][++colRight] == wholeBoard) {
						board[rowDown][colRight] = BoardCell.EMPTY;
						score++;
					} else {
						rowDown = board.length;
					}
				}
				
				BoardCell[][] newboard = new BoardCell[maxRows][maxCols];
				int row = 0;
				
				while (row < board.length - 1) {
					boolean collapseCells = true;
					for (int col = 0; col < board[row].length; col++) {
						if (board[row][col] != BoardCell.EMPTY) {
							collapseCells = false;
						}
					}
					
					if (collapseCells) {
						for (int newRow1 = 0; newRow1 < row; newRow1++) {
							for (int newCol1 = 0; newCol1 < board[row].length; newCol1++) {
								newboard[newRow1][newCol1] = board[newRow1][newCol1];
							}
						}
						
						for (int newRow2 = row; newRow2 < board.length; newRow2++) {
							for (int newCol2 = 0; newCol2 < board[row].length; newCol2++) {
								newboard[newRow2][newCol2] = BoardCell.EMPTY;
							}
						}
						
						for (int newRow3 = row; newRow3 < board.length - 1; newRow3++) {
							for (int newCol3 = 0; newCol3 < board[row].length; newCol3++) {
								newboard[newRow3][newCol3] = board[newRow3 + 1][newCol3];
							}
						}
						
						board = newboard;
					}
					
					row++;
				}
			}
		}
	}
}