package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import system.TwoDimArrayUtil;

public class StudentTests {

	@Test
	public void test01IsRagged() {
		char[][] arrRagged = {{0,1,2,3,4}, {5, 6}, {7,8,9}};
		assertTrue(TwoDimArrayUtil.isRagged(arrRagged) == true); //Tests if array is ragged
		
		
		char[][] arrNotRagged = {{0,1,2}, {3,4,5}, {6,7,8}};
		assertTrue(TwoDimArrayUtil.isRagged(arrNotRagged) == false); //Tests if array is not ragged
	}
	
	@Test
	public void test04AppendTopBottom() {
		char[][] arrTop = {{'A', 'B', 'C'}, {'D', 'E'}};
		char[][] arrBottom = {{'F'}, {'G', 'H'}};
		char[][] expectedResult = {{'A', 'B', 'C'}, {'D', 'E'}, {'F'}, {'G', 'H'}};
		assertArrayEquals(TwoDimArrayUtil.appendTopBottom(arrTop, arrBottom), expectedResult);
		
	}
	
	@Test
	public void test05AppendLeftRight() {
		char[][] arrLeft = {{'G', 'H'}, {'I'}, {'K', 'L'}};
		char[][] arrRight = {{'M'}, {'O', 'P'}, {'Q', 'R'}, {'X', 'Y'}};
		char[][] expectedResult = {{'G', 'H', 'M'}, {'I', 'O', 'P'}, {'K', 'L', 'Q', 'R'}, {'X', 'Y'}};
		
		assertArrayEquals(TwoDimArrayUtil.appendLeftRight(arrLeft, arrRight), expectedResult);
	}

}
