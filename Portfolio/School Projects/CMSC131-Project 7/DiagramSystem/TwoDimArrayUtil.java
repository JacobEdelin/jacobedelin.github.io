package system;

public class TwoDimArrayUtil {

	public static boolean isRagged(char[][] array) {
		boolean result = false;

		for (char[] newArr : array) {
			if (newArr.length != array[0].length) {
				result = true;
			}
		}
		return result;
	}

	public static void rotateTopOneRow(char[][] array) {
		boolean result = false;

		for (char[] newArr : array) {
			if (newArr.length != array[0].length) {
				result = true;
			}
		}
		if (result == true || array == null) {
			throw new IllegalArgumentException("Invalid");
		}
		char[] temp;
		if (array.length != 1) {
			temp = array[0];
			for (int j = 0; j < array.length - 1; j++) {
				array[j] = array[j + 1];
			}
			array[array.length - 1] = temp;
		}
	}

	public static void rotateLeftOneColumn(char[][] array) {
		boolean result = false;

		for (char[] newArr : array) {
			if (newArr.length != array[0].length) {
				result = true;
			}
		}
		if (result == true || array == null) {
			throw new IllegalArgumentException("Invalid");
		}
		char temp;

		if (array.length != 1) {
			for (int row = 0; row < array.length; row++) {
				temp = array[row][0];
				for (int col = 0; col < array[row].length - 1; col++) {
					array[row][col] = array[row][col + 1];
				}
				array[row][array[row].length - 1] = temp;
			}
		}
	}

	public static char[][] appendTopBottom(char[][] top, char[][] bottom) {
		char[][] result = new char[top.length + bottom.length][];
		int i = 0;
		for (; i < top.length; i++) {
			result[i] = top[i]; // shallow copy
		}
		for (int j = 0; j < bottom.length; j++) {
			result[i++] = bottom[j];
		}
		return result;
	}

	public static char[][] appendLeftRight(char[][] left, char[][] right) {
		char[][] result;
		int numRow = 0;

		if (left.length > right.length) {
			numRow = left.length;
		} else {
			numRow = right.length;
		}
		result = new char[numRow][];

		for (int i = 0; i < numRow; i++) {
			if (left.length > i && right.length > i) {
				result[i] = new char[left[i].length + right[i].length];
			} else if (left.length > i) {
				result[i] = new char[left[i].length];
			} else if (right.length > i) {
				result[i] = new char[right[i].length];
			}
			if (left.length > i && left[i] != null) {
				for (int j = 0; j < left[i].length; j++) {
					result[i][j] = left[i][j];
				}
			}

			if (right.length > i && right[i] != null) {
				for (int k = 0; k < right[i].length; k++) {
					int j;
					if (left.length - 1 < i) {
						j = 0;
					} else {
						j = left[i].length;
					}
					result[i][j + k] = right[i][k];
				}
			}
		}
		return result;
	}
}