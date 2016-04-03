/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import asgn1Election.Candidate;
import asgn1Election.ElectionException;

/**
 * @author n5372828 Ian Daniel
 *
 */
public class CandidateTests {

	Candidate candi;
	Candidate candiCopy;
	
	@Before
	public void setup() throws ElectionException {
		candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		candiCopy = candi.copy();
	}
	
	
	/*
	 *    Test Section for Candidate Constructor - Expected Working
	 */
	/**
	 * Test methods for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * @throws ElectionException 
	 */
	@Test
	public void testCandidatePassNotNull() throws ElectionException {
		assertNotNull(candi);
	}
	
	@Test
	public void testCandidatePassInstanceOf() {
		assertTrue(candi instanceof Candidate);
	}
	
	
	/*
	 *    Test Section for Candidate Constructor focusing on Name
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
	 *    Test Section for Candidate Constructor focusing on Party Name
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
	 *    Test Section for Candidate Constructor focusing on Acronym Field
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
	 *    Test Section for Candidate Constructor focusing on voteCount
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateVoteCountFailBorderCaseFailNegitive() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", -1);
		assertNull(candiCustom);
	}
	
	/*
	 * NOTE:  Other border case (voteCount = 0) is done by the other tests
	 */
	
	@Test(expected = ElectionException.class)
	public void testCandidateVoteCountFailExtremeCaseFailLargeNegitive() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", -100000000);
		assertNull(candiCustom);
	}
	
	@Test
	public void testCandidateVoteCountFailExtremeCasePassLargePositiveNonZero() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", 100000000);
		assertNotNull(candiCustom);
	}
	
	
	/*
	 *    Test Section for Candidate.candidateListing()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#candidateListing()}.
	 * @throws ElectionException 
	 */
	@Test
	public void testCandidateListingNormal() throws ElectionException {
		Candidate candiCustom = new Candidate("HOWARTH, Luke", 
				"Liberal National Party of Queensland", "LNP", 0);
		assertEquals("HOWARTH, Luke       Liberal National Party of Queensland(LNP)\n", 
				candiCustom.candidateListing());
	}

	@Test
	public void testCandidateListingExtraLongNames() throws ElectionException {
		String longName, longPartyName, longAbbr, answerString;
		longName = "xxxxxxxxxxxHOWARTHxxxxxxxxxxx, xxxxxxxxxxxLukexxxxxxxxxxx";
		longPartyName = "xxxxxxxxxxxxxxxLiberal National Party of Queenslandxxxxxxxxxxxxxxx";
		longAbbr = "xxxxxxxxxxxxxLNPxxxxxxxxxxxxxxx";
		answerString = longName + longPartyName + "(" + longAbbr + ")\n";
		
		Candidate candiCustom = new Candidate(longName, longPartyName, longAbbr, 0);
		assertEquals(answerString, candiCustom.candidateListing());
	}
	
	@Test
	public void testCandidateListingExtraShortNames() throws ElectionException {
		String longName, longPartyName, longAbbr, answerString, 
		      sixTeenSpaces, twentyNineSpaces;
		longName = "a, b";
		longPartyName = "c";
		longAbbr = "d";
		
		// SixTeenSpaces is found by the length of longName less than ElectionManager.NameField
		sixTeenSpaces = "                ";
		// twentyNineSpaces is found by the length of longName less than ElectionManager.FullPartyField
		twentyNineSpaces = "                             ";
		answerString = longName + sixTeenSpaces + longPartyName + 
				twentyNineSpaces +"(" + longAbbr + ")\n";
		
		Candidate candiCustom = new Candidate(longName, longPartyName, longAbbr, 0);
		assertEquals(answerString, candiCustom.candidateListing());
	}
	
	
	/*
	 *    Test Section for Candidate.Copy()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#copy()}.
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
	 *    Test Section for Candidate.getName()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getName()}.
	 * @throws ElectionException 
	 */
	@Test
	public void testGetNameNormal() throws ElectionException {
		assertEquals("MARSHALL, John", candi.getName());
	}
	
	@Test
	public void testGetNameShort() throws ElectionException {
		Candidate candiCustom = new Candidate("x", "The Greens", "GRN", 0);
		assertEquals("x", candiCustom.getName());
	}
	
	@Test
	public void testGetNameLong() throws ElectionException {
		Candidate candiCustom = new Candidate("xxxxxxxxxxxHOWARTHxxxxxxxxxxx, xxxxxxxxxxxLukexxxxxxxxxxx", 
				"The Greens", "GRN", 0);
		assertEquals("xxxxxxxxxxxHOWARTHxxxxxxxxxxx, xxxxxxxxxxxLukexxxxxxxxxxx", candiCustom.getName());
	}
	
	@Test
	public void testGetNameWithSpacesAtStart() throws ElectionException {
		Candidate candiCustom = new Candidate("   MARSHALL, John", "The Greens", "GRN", 0);
		assertEquals("MARSHALL, John", candiCustom.getName());
	}
	
	@Test
	public void testGetNameWithSpacesAtEnd() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John   ", "The Greens", "GRN", 0);
		assertEquals("MARSHALL, John", candiCustom.getName());
	}
	

	/*
	 *    Test Section for Candidate.getParty()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getParty()}.
	 * @throws ElectionException 
	 */
	@Test
	public void testGetPartyNormal() throws ElectionException {
		assertEquals("The Greens", candi.getParty());
	}
	
	@Test
	public void testGetPartyShort() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "x", "GRN", 0);
		assertEquals("x", candiCustom.getParty());
	}
	
	@Test
	public void testGetPartyLong() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", 
				"xxxxxxxxxxxxxxxxxxxxxxxThe Greensxxxxxxxxxxxxxxxxxxxxxxx", "GRN", 0);
		assertEquals("xxxxxxxxxxxxxxxxxxxxxxxThe Greensxxxxxxxxxxxxxxxxxxxxxxx", candiCustom.getParty());
	}
	
	@Test
	public void testGetPartyWithSpacesAtStart() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "   The Greens", "GRN", 0);
		assertEquals("The Greens", candiCustom.getParty());
	}
	
	@Test
	public void testGetPartyWithSpacesAtEnd() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens   ", "GRN", 0);
		assertEquals("The Greens", candiCustom.getParty());
	}


	/*
	 *    Test Section for Candidate.getVoteCount()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCount()}.
	 * @throws ElectionException 
	 */
	@Test
	public void testGetVoteCountNormal() throws ElectionException {
		assertEquals(0, candi.getVoteCount());
	}

	@Test
	public void testGetVoteCountHighNumber() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", 2147483647);
		assertEquals(2147483647, candiCustom.getVoteCount());
	}
	
	
	/*
	 *    Test Section for Candidate.GetVoteCountString()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCountString()}.
	 * @throws ElectionException 
	 */
	@Test
	public void testGetVoteCountStringNormal() throws ElectionException {
		assertEquals("0", candi.getVoteCountString());
	}
	
	@Test
	public void testGetVoteCountStringFive() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", 5);
		assertEquals("5", candiCustom.getVoteCountString());
	}
	
	@Test
	public void testGetVoteCountStringHighNumber() throws ElectionException {
		Candidate candiCustom = new Candidate("MARSHALL, John", "The Greens", "GRN", 2147483647);
		assertEquals("2147483647", candiCustom.getVoteCountString());
	}
	
	
	/*
	 *    Test Section for Candidate.incrementVoteCount()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#incrementVoteCount()}.
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
				
		for (int i = 0; i < maxInt; i++){
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

		for (int i = 0; i < AddSeventyMillion; i++){
			candiCustom.incrementVoteCount();
		}
		
		assertEquals(finalHighNumber, candiCustom.getVoteCount());
	}
	
	
	/*
	 *    Test Section for Candidate.ToString()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#toString()}.
	 * @throws ElectionException 
	 */
	@Test
	public void testToStringNormal() throws ElectionException {
		// ElectionManager.DisplayFieldWidth - length of both testName and testAbbr
		String emptySpace = "         "; // 30 - 21 = 9 Spaces 
		String answerText, testName, testAbbr;
		int testVote;
		
		testName = "MARSHALL, John";
		testAbbr = "GRN";
		testVote = 0;
		
		answerText = testName + " (" + testAbbr + ")" + emptySpace + testVote + "\n";
		
		Candidate candiCustom = new Candidate(testName, "The Greens", testAbbr, testVote);
		
		assertEquals(answerText, candiCustom.toString());
	}
	
	@Test
	public void testToStringExtraShortNames() throws ElectionException {
		// ElectionManager.DisplayFieldWidth - length of both testName and testAbbr
		String emptySpace = "                        "; // 30 - 6 = 24 Spaces
		String answerText, testName, testAbbr;
		int testVote;
		
		testName = "a";
		testAbbr = "b";
		testVote = 0;
		
		answerText = testName + " (" + testAbbr + ")" + emptySpace + testVote + "\n";
		
		Candidate candiCustom = new Candidate(testName, "The Greens", testAbbr, testVote);
		
		assertEquals(answerText, candiCustom.toString());
	}
	
	public void testToStringExtraLongNames() throws ElectionException {
		// ElectionManager.DisplayFieldWidth - length of both testName and testAbbr
		String emptySpace = ""; // 0 Spaces, the sum is much greater than 30
		String answerText, testName, testAbbr;
		int testVote;
		
		testName = "xxxxxxxxxxxxxMARSHALL, Johnxxxxxxxxxxxxx";
		testAbbr = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		testVote = 1234567890;
		
		answerText = testName + " (" + testAbbr + ")" + emptySpace + testVote + "\n";
		
		Candidate candiCustom = new Candidate(testName, "The Greens", testAbbr, testVote);
		
		assertEquals(answerText, candiCustom.toString());
	}
}
