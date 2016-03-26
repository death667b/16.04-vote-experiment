/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.CandidateIndex;

/**
 * @author n5372828 Ian Daniel
 *
 */
public class CandidateIndexTests {

	CandidateIndex canIndex;
	
	@Before
	public void setUp(){
		canIndex = new CandidateIndex(5);
	}
	
	
	/*
	 *    Test Section for CandidateIndex.inRange
	 */
	/**
	 * Test method for {@link asgn1Election.CandidateIndex#inRange(int)}.
	 */
	@Test
	public void testInRangeNormal() {
		assertTrue(CandidateIndex.inRange(5));
	}
	
	@Test
	public void testInRangeLowerBounderyFail() {
		assertFalse(CandidateIndex.inRange(0));
	}
	
	@Test
	public void testInRangeLowerBounderyPass() {
		assertTrue(CandidateIndex.inRange(1));
	}
	
	@Test
	public void testInRangeUpperBounderyFail() {
		assertFalse(CandidateIndex.inRange(16));
	}
	
	@Test
	public void testInRangeUpperBounderyPass() {
		assertTrue(CandidateIndex.inRange(15));
	}
	/*
	 *    *END* Test Section for CandidateIndex.inRange *END*
	 */
	

	/**
	 * Test method for {@link asgn1Election.CandidateIndex#CandidateIndex(int)}.
	 */
	@Test
	public void testCandidateIndexNormal() {
		assertTrue(canIndex instanceof CandidateIndex);
	}
	
	
	/*
	 *    Test Section for CandidateIndex.CompareTo
	 */
	/**
	 * Test method for {@link asgn1Election.CandidateIndex#compareTo(asgn1Election.CandidateIndex)}.
	 */
	@Test
	public void testCompareToSameValue() {
		CandidateIndex canCompareTo = new CandidateIndex(5);
		
		assertEquals(0, canIndex.compareTo(canCompareTo));
	}
	
	@Test
	public void testCompareToLowerValue() {
		CandidateIndex canCompareTo = new CandidateIndex(10);
		
		assertEquals(-1, canIndex.compareTo(canCompareTo));
	}
	
	@Test
	public void testCompareToHigherValue() {
		CandidateIndex canCompareTo = new CandidateIndex(1);
		
		assertEquals(1, canIndex.compareTo(canCompareTo));
	}
	/*
	 *    *END* Test Section for CandidateIndex.CompareTo *END*
	 */
	

	/**
	 * Test method for {@link asgn1Election.CandidateIndex#copy()}.
	 */
	@Test
	public void testCopyConfirmNotSame() {
		CandidateIndex canCopyTo;
		
		canCopyTo = canIndex.copy();
		
		assertNotSame(canIndex, canCopyTo);
	}

	/**
	 * Test method for {@link asgn1Election.CandidateIndex#incrementIndex()}.
	 */
	@Test
	public void testIncrementIndexNormal() {
		canIndex.incrementIndex();
		assertEquals("6", canIndex.toString());
	}
	
	@Test
	public void testIncrementIndexTenIncrement() {
		int incrementByTen = 10;
		
		for (int i = 0; i < incrementByTen; i++){
			canIndex.incrementIndex();
		}
		
		assertEquals("15", canIndex.toString());
	}

	/**
	 * Test method for {@link asgn1Election.CandidateIndex#setValue(int)}.
	 */
	@Test
	public void testSetValueNormal() {
		canIndex.setValue(2);
		assertEquals("2", canIndex.toString());
	}
	
	@Test
	public void testSetValueMultiChanges() {
		Random random = new Random();
		int numberOfSetValues = 500000;
		int randomNumber = 0;
		String randomNumberAsString;
		
		for (int i = 0; i < numberOfSetValues; i++){
			randomNumber = random.nextInt(15);
			canIndex.setValue(randomNumber);
		}
		
		randomNumberAsString = randomNumber + "";

		assertEquals(randomNumberAsString, canIndex.toString());
	}

	/**
	 * Test method for {@link asgn1Election.CandidateIndex#toString()}.
	 */
	@Test
	public void testToStringNormal() {
		canIndex = new CandidateIndex(12);
		
		assertEquals("12", canIndex.toString());
	}

}
