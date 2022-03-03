package system;

public class CombineTopBottom implements Diagram {
	private char[][] board;
	private int animationType;
	
	public CombineTopBottom(Diagram top, Diagram bottom, int animationType) {
		char[][] topCompare = top.getBoard(), bottomCompare = bottom.getBoard();
		if (topCompare[0].length != bottomCompare[0].length) {
			throw new IllegalArgumentException("Invalid");
		}
		this.animationType = animationType;
		board = TwoDimArrayUtil.appendTopBottom(top.getBoard(), bottom.getBoard());
		
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
