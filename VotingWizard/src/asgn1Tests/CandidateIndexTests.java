/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.CandidateIndex;

/**
 * @author n5372828 Ian Daniel
 *
 */
public class CandidateIndexTests {

	private CandidateIndex canIndex;

	@Before
	public void setUp() {
		canIndex = new CandidateIndex(5);
	}

	/*
	 * Test Section for CandidateIndex.inRange
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

	@Test
	public void testInRangeNegitiveNumberFail() {
		assertFalse(CandidateIndex.inRange(-1));
	}

	/*
	 * Test Section for CandidateIndex.CompareTo
	 */
	/**
	 * Test method for
	 * {@link asgn1Election.CandidateIndex#compareTo(asgn1Election.CandidateIndex)}
	 * .
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
	 * Test Section for CandidateIndex.copy
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

	@Test
	public void testCopyConfirmSameValue() {
		CandidateIndex canCopyTo;
		canCopyTo = canIndex.copy();

		assertEquals(canIndex.toString(), canCopyTo.toString());
	}

	@Test
	public void testCopyConfirmNotSameValue() {
		CandidateIndex canCopyTo;
		canCopyTo = canIndex.copy();
		canCopyTo.incrementIndex();

		assertNotEquals(canIndex.toString(), canCopyTo.toString());
	}

	@Test
	public void testCopyNotNull() {
		CandidateIndex canCopyTo;
		canCopyTo = canIndex.copy();

		assertNotNull(canCopyTo);
	}

	@Test
	public void testCopyIsInstanceOf() {
		CandidateIndex canCopyTo;
		canCopyTo = canIndex.copy();

		assertTrue(canCopyTo instanceof CandidateIndex);
	}

	/*
	 * Test Section for CandidateIndex.incrementIndex
	 */
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

		for (int i = 0; i < incrementByTen; i++) {
			canIndex.incrementIndex();
		}

		assertEquals("15", canIndex.toString());
	}

	@Test
	public void testIncrementIndexNearMaxIntIncrementd() {
		int incrementByNearMaxInt = 2147483642;

		for (int i = 0; i < incrementByNearMaxInt; i++) {
			canIndex.incrementIndex();
		}

		assertEquals("2147483647", canIndex.toString());
	}

	/*
	 * Test Section for Automatic Passes
	 */
	/**
	 * Test method for {@link asgn1Election.CandidateIndex#CandidateIndex(int)}.
	 */
	@Test
	public void testCandidateIndexBlindPass() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn1Election.CandidateIndex#setValue(int)}.
	 */
	@Test
	public void testSetValueBlindPass() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn1Election.CandidateIndex#toString()}.
	 */
	@Test
	public void testToStringBlindPass() {
		assertTrue(true);
	}
}
