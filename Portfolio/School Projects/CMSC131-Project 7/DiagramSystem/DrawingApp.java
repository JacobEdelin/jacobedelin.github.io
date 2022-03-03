package app;

import java.util.Random;

public class DrawingApp {
	/*
	 * For every method remove the line with "throw ..." and implement the method.
	 * We are using "throw..." so your code does not display any compilation errors
	 * when you import the zip file. Also, if you don't implement a method you will
	 * see a white square (instead of green) in the submit server
	 */
	public static String getRectangle(int maxRows, int maxCols, char symbol) {
		if (maxRows < 1 || maxCols < 1) {
			return null;
		} else {
			String result = "";
			for (int row = 1; row <= maxRows; row++) {
				for (int col = 1; col <= maxCols; col++) {
 					result += symbol;
				}
				if (row < maxRows) {
					result += "\n";
				}
			}
			return result;
		}
	}


	public static String getFlag(int size, char color1, char color2, char color3) {
		if (size < 3) {
			return null;
		}
		int numRows = size * 2, numCols = size * 5, rowCounter = 1, flagSize = 1;
		String flag = "";

		while (rowCounter <= numRows) {
			if (rowCounter <= size) {
				for (int col = 0; col < flagSize; col++) {
					flag += color1;
				}
				for (int i = 0; i < numCols - flagSize; i++) {
					if (rowCounter == 1 || rowCounter == numRows || rowCounter == size || rowCounter == size + 1) {
						flag += color2;
					} else {
						flag += color3;
					}
				}
				
				flagSize++;

				flag += "\n";

			} else if (rowCounter >= size) {
				for (int i = 1; i < flagSize; i++) {
					flag += color1;
				}
				for (int i = 0; i < numCols - flagSize + 1; i++) {
					if (rowCounter == 1 || rowCounter == numRows || rowCounter == size || rowCounter == size + 1) {
						flag += color2;
					} else {
						flag += color3;
					}
				}
				
				flagSize--;
				
				flag += "\n";
			}
			rowCounter++;
		}
		return flag.trim();
	}
	
	public static String getHorizontalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {
		int barSize = maxRows / bars;

		if (barSize < 1 || color1 == ' ' || color2 == ' ' || color3 == ' ') {
			return null;
		}
		String result = "";
		for (int barCount = 0; barCount < bars; barCount++) {
			for (int row = 0; row < barSize; row++) {
				for (int col = 0; col < maxCols; col++) {
					if (barCount % 3 == 0) {
						result += color1;
					} else if (barCount % 3 == 1) {
						result += color2;
					} else {
						result += color3;
					}
				}
				result += "\n";
			}
		}
		return result.trim();
	}

	public static String getVerticalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {
		int verticalBars = maxCols / bars;
		String result = "", coloredResult = "";

		if (verticalBars < 1 || color1 == ' ' || color2 == ' ' || color3 == ' ') {
			return null;
		}
		for (int row = 0; row <= bars;) {
			for (int col = 0; col < verticalBars; col++) {
				coloredResult += color1;
			}
			row++;

			if (row == bars)
				break;
			for (int col = 0; col < verticalBars; col++) {
				coloredResult += color2;
			}
			row++;

			if (row == bars)
				break;
			for (int col = 0; col < verticalBars; col++) {
				coloredResult += color3;
			}
			row++;
			
			if (row == bars)
				break;
		}
		coloredResult += '\n';

		for (int row = 0; row < maxRows; row++) {
			result += coloredResult;
		}
		return result.trim();
	}

	public static char getRandomColor(Random random) {
		int upperBound = 6, colorChoice;
		colorChoice = (random.nextInt(upperBound));
		
		if (colorChoice == 0) {
			return 'R';
		} else if (colorChoice == 1) {
			return 'G';
		} else if (colorChoice == 2) {
			return 'B';
		} else if (colorChoice == 3) {
			return 'Y';
		} else if (colorChoice == 4) {
			return '*';
		} else if (colorChoice == 5) {
			return '.';
		} else {
			return 'X';
		}
	}
}