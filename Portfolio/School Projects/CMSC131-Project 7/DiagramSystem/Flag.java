package system;

import app.DrawingApp;
import gui.BoardCell;

public class Flag implements Diagram {
	private char[][] board;
	private int animationType;
	
	public Flag(int size, char color1, char color2, char color3, int animationType) {
		this.animationType = animationType;
		String diagramAsString = DrawingApp.getFlag(size, color1, color2, color3);
		
		board = BoardCell.getCharArray(diagramAsString);
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public char[][] nextAnimationStep() {
		if (animationType == 1) {
			TwoDimArrayUtil.rotateLeftOneColumn(board);
		}
		if (animationType == 2) {
			TwoDimArrayUtil.rotateTopOneRow(board);
		}
		return board;
	}
	
	public int getNumberRows() {
		return board.length;
	}
	
	public int getNumberCols() {
		return board[0].length;
	}

}
