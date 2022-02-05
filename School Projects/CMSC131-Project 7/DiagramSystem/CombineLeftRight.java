package system;

public class CombineLeftRight implements Diagram {
	private char[][] board;
	private int animationType;
	
	public CombineLeftRight(Diagram left, Diagram right, int animationType) {
		char[][] leftCompare = left.getBoard(), rightCompare = right.getBoard();
		if (leftCompare.length != rightCompare.length) {
			throw new IllegalArgumentException("Invalid");
		}
		this.animationType = animationType;
		board = TwoDimArrayUtil.appendLeftRight(left.getBoard(), right.getBoard());
		
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