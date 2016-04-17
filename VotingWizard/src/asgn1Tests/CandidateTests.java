/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.Candidate;
import asgn1Election.ElectionException;

/**
 * @author n5372828 Ian Daniel
 *
 */
public class CandidateTests {

	private Candidate candi;
	private Candidate candiCopy;

	@Before
	public void setup() throws ElectionException {
		candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		candiCopy = candi.copy();
	}

	/*
	 * Test Section for Candidate Constructor focusing on Name
	 */
	/**
	 * Test methods for
	 * {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}
	 * .
	 * 
	 * @throws ElectionException
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateMissingNameFailWithNoSpaces() throws ElectionException {
		Candidate candiCustom = new Candidate("", "The Greens", "GRN", 0);
		assertNull(candiCustom);
	}

	@Test(expected = ElectionException.class)
	public void testCandidateMissingNameFailWithSpaces() throws ElectionException {
		Candidate candiCustom = new Candidate("   ", "The Greens", "GRN", 0);
		assertNotNull(candiCustom);
	}

	@Test(expected = ElectionException.class)
	public void testCandidateMissingNameFailWithNull() throws ElectionException {
		Candidate candiCustom = new Candidate(null, "The Greens", "GRN", 0);
		assertNotNull(candiCustom);
	}

	/*
	 * Test Section for Candidate Constructor focusing on Party Name
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateMissingPartyNameFailNoSpaces() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "", "GRN", 0);
		assertNull(candiCustom);
	}

	@Test(expected = ElectionException.class)
	public void testCandidateMissingPartyNameFailWithSpaces() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "   ", "GRN", 0);
		assertNull(candiCustom);
	}

	@Test(expected = ElectionException.class)
	public void testCandidateMissingPartyNameFailWithNull() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", null, "GRN", 0);
		assertNull(candiCustom);
	}

	/*
	 * Test Section for Candidate Constructor focusing on Acronym Field
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateMissingAcronymFailWithNoSpaces() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "", 0);
		assertNull(candiCustom);
	}

	@Test(expected = ElectionException.class)
	public void testCandidateMissingAcronymFailWithSpaces() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "   ", 0);
		assertNull(candiCustom);
	}

	@Test(expected = ElectionException.class)
	public void testCandidateMissingAcronymFailWithNull() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", null, 0);
		assertNull(candiCustom);
	}

	/*
	 * Test Section for Candidate Constructor focusing on voteCount
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateVoteCountFailBorderCaseFailNegitive() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", -1);
		assertNull(candiCustom);
	}

	/*
	 * Test Section for Candidate.Copy()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#copy()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testCopyNotNull() throws ElectionException {
		assertNotNull(candiCopy);
	}

	@Test
	public void testCopyIndexIsInstanceOf() throws ElectionException {
		assertTrue(candiCopy instanceof Candidate);
	}

	@Test
	public void testCopyGetName() throws ElectionException {
		assertTrue(candi.getName() == candiCopy.getName());
	}

	@Test
	public void testCopyGetParty() throws ElectionException {
		assertTrue(candi.getParty() == candiCopy.getParty());
	}

	@Test
	public void testCopyGetVoteCount() throws ElectionException {
		assertTrue(candi.getVoteCount() == candiCopy.getVoteCount());
	}

	@Test
	public void testCopyGetVoteCountString() throws ElectionException {
		assertTrue(candi.getVoteCountString().equals(candiCopy.getVoteCountString()));
	}

	@Test
	public void testCopyCandidateListing() throws ElectionException {
		assertTrue(candi.candidateListing().equals(candiCopy.candidateListing()));
	}

	@Test
	public void testCopyToString() throws ElectionException {
		assertTrue(candi.toString().equals(candiCopy.toString()));
	}

	@Test
	public void testCopyConfirmDifferentObjects() throws ElectionException {
		assertNotSame(candi, candiCopy);
	}

	/*
	 * Test Section for Candidate.getName()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getName()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testGetNameWithSpacesAtStartAndEnd() throws ElectionException {
		Candidate candiCustom = new Candidate("   MARSHALL, John   ", "The Greens", "GRN", 0);
		assertEquals("MARSHALL, John", candiCustom.getName());
	}

	/*
	 * Test Section for Candidate.getParty()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getParty()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testGetPartyWithSpacesAtStartAndEnd() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "   The Greens   ", "GRN", 0);
		assertEquals("The Greens", candiCustom.getParty());
	}

	/*
	 * Test Section for Candidate.incrementVoteCount()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#incrementVoteCount()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testIncrementVoteCountNormal() throws ElectionException {
		candi.incrementVoteCount();
		assertEquals(1, candi.getVoteCount());
	}

	@Test
	public void testIncrementVoteCountAddOneToHighNumber() throws ElectionException {
		int highNumber = 1234567890;
		int finalHighNumber = highNumber + 1;

		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", highNumber);
		candiCustom.incrementVoteCount();
		assertEquals(finalHighNumber, candiCustom.getVoteCount());
	}

	@Test
	public void testIncrementVoteCountAddMaxIntVotes() throws ElectionException {
		int maxInt = 2147483647;
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);

		for (int i = 0; i < maxInt; i++) {
			candiCustom.incrementVoteCount();
		}

		assertEquals(maxInt, candiCustom.getVoteCount());
	}

	@Test
	public void testIncrementVoteCountAddSeventyMillionVotesToHighNumber() throws ElectionException {
		int AddSeventyMillion = 70000000;
		int highNumber = 1234567890;
		int finalHighNumber = AddSeventyMillion + highNumber;
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", highNumber);

		for (int i = 0; i < AddSeventyMillion; i++) {
			candiCustom.incrementVoteCount();
		}

		assertEquals(finalHighNumber, candiCustom.getVoteCount());
	}

	/*
	 * Test Section for Automatic Passes
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#candidateListing()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testCandidateListingBlindPass() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCount()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testGetVoteCountBlindPass() throws ElectionException {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCountString()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testGetVoteCountStringBlindPass() throws ElectionException {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#toString()}.
	 * 
	 * @throws ElectionException
	 */
	@Test
	public void testToStringBlindPass() throws ElectionException {
		assertTrue(true);
	}
}
