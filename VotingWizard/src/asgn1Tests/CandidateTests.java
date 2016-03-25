/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.Candidate;
import asgn1Election.ElectionException;
import asgn1Election.ElectionManager;

/**
 * @author n5372828 Ian Daniel
 *
 */
public class CandidateTests {

	/**
	 * Test methods for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * @throws ElectionException 
	 */
	@Test
	/*
	 *    Test Section for Candidate Constructor - Expected Working
	 */
	public void testCandidatePassNormal() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
	}
	
	
	/*
	 *    Test Section for Candidate Constructor focusing on Name
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateMissingNameFailWithNoSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("", "The Greens", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateMissingNameFailWithSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("   ", "The Greens", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateMissingNameFailWithNull() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate(null, "The Greens", "GRN", 0);
	}
	
	/*@Test
	public void testCandidateNameLengthBorderCasePassWith20() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("12345678901234567890", "The Greens", "GRN", 0);
	}*/
	
	/*@Test(expected = ElectionException.class)
	public void testCandidateNameLengthBorderCaseFailWith21() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("123456789012345678901", "The Greens", "GRN", 0);
	}*/
	
	/*@Test
	public void testCandidateNameLengthBorderCasePassWith20AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("   12345678901234567890   ", "The Greens", "GRN", 0);
	}*/

	/*@Test(expected = ElectionException.class)
	public void testCandidateNameLengthBorderCaseFailWith21AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("   123456789012345678901   ", "The Greens", "GRN", 0);
	}*/
	/*
	 *    *END* Test Section for Candidate Constructor focusing on Name *END*
	 */
	
	
	/*
	 *    Test Section for Candidate Constructor focusing on Party Name
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateMissingPartyNameFailNoSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateMissingPartyNameFailWithSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "   ", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateMissingPartyNameFailWithNull() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", null, "GRN", 0);
	}
	
	/*@Test
	public void testCandidatePartyNameLengthBorderCasePassWith30() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "123456789012345678901234567890", "GRN", 0);
	}*/
	
	/*@Test(expected = ElectionException.class)
	public void testCandidatePartyNameLengthBorderCaseFailWith31() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "1234567890123456789012345678901", "GRN", 0);
	}*/
	
	/*@Test
	public void testCandidatePartyNameLengthBorderCasePassWith30AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "   123456789012345678901234567890   ", "GRN", 0);
	}*/

	/*@Test(expected = ElectionException.class)
	public void testCandidatePartyNameLengthBorderCaseFailWith31AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "   1234567890123456789012345678901   ", "GRN", 0);
	}*/
	/*
	 *    *END* Test Section for Candidate Constructor focusing on Party Name *END*
	 */
	
	
	/*
	 *    Test Section for Candidate Constructor focusing on Acronym Field
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateMissingAcronymFailWithNoSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateMissingAcronymFailWithSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "   ", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateMissingAcronymFailWithNull() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", null, 0);
	}
	/*
	 *    *END* Test Section for Candidate Constructor focusing on Acronym Field *END*
	 */
	
	
	/*
	 *    Test Section for Candidate Constructor focusing on voteCount
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateVoteCountFailBorderCaseFailNegitive() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", -1);
	}
	
	/*
	 * NOTE:  Other border case (voteCount = 0) is done by the other tests
	 */
	
	
	/*@Test
	public void testCandidateVoteCountFailBorderCasePassPositiveNonZero() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 1);
	}*/
	
	@Test(expected = ElectionException.class)
	public void testCandidateVoteCountFailExtremeCaseFailLargeNegitive() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", -100000000);
	}
	
	@Test
	public void testCandidateVoteCountFailExtremeCasePassLargePositiveNonZero() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 100000000);
	}
	/*
	 *    *END* Test Section for Candidate Constructor focusing on voteCount *END*
	 */
	
	
	/*
	 *    Test Section for Candidate.candidateListing()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#candidateListing()}.
	 */
	@Test
	public void testCandidateListingNormal() throws ElectionException {
		Candidate candi = new Candidate("HOWARTH, Luke", 
				"Liberal National Party of Queensland", "LNP", 0);
		assertEquals("HOWARTH, Luke       Liberal National Party of Queensland(LNP)\n", 
				candi.candidateListing());
	}

	@Test
	public void testCandidateListingExtraLongNames() throws ElectionException {
		String longName, longPartyName, longAbbr, answerString;
		longName = "xxxxxxxxxxxHOWARTHxxxxxxxxxxx, xxxxxxxxxxxLukexxxxxxxxxxx";
		longPartyName = "xxxxxxxxxxxxxxxLiberal National Party of Queenslandxxxxxxxxxxxxxxx";
		longAbbr = "xxxxxxxxxxxxxLNPxxxxxxxxxxxxxxx";
		answerString = longName + longPartyName + "(" + longAbbr + ")\n";
		
		Candidate candi = new Candidate(longName, longPartyName, longAbbr, 0);
		assertEquals(answerString, candi.candidateListing());
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
		
		Candidate candi = new Candidate(longName, longPartyName, longAbbr, 0);
		assertEquals(answerString, candi.candidateListing());
	}
	/*
	 *    *END* Test Section for Candidate.candidateListing() *END*
	 */
	
	
	/*
	 *    Test Section for Candidate.Copy()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#copy()}.
	 */
	@Test
	public void testCopyGetName() throws ElectionException {
		Candidate candiOne = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		Candidate candiTwo;
		
		candiTwo = candiOne.copy();
		
		assertTrue(candiOne.getName() == candiTwo.getName());
	}
	
	@Test
	public void testCopyGetParty() throws ElectionException {
		Candidate candiOne = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		Candidate candiTwo;
		
		candiTwo = candiOne.copy();
		
		assertTrue(candiOne.getParty() == candiTwo.getParty());
	}
	
	@Test
	public void testCopyGetVoteCount() throws ElectionException {
		Candidate candiOne = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		Candidate candiTwo;
		
		candiTwo = candiOne.copy();
		
		assertTrue(candiOne.getVoteCount() == candiTwo.getVoteCount());
	}
	
	@Test
	public void testCopyGetVoteCountString() throws ElectionException {
		Candidate candiOne = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		Candidate candiTwo;
		
		candiTwo = candiOne.copy();

		assertTrue(candiOne.getVoteCountString().equals(candiTwo.getVoteCountString()));
	}
	
	@Test
	public void testCopyCandidateListing() throws ElectionException {
		Candidate candiOne = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		Candidate candiTwo;
		
		candiTwo = candiOne.copy();
		
		assertTrue(candiOne.candidateListing().equals(candiTwo.candidateListing()));
	}
	
	@Test
	public void testCopyToString() throws ElectionException {
		Candidate candiOne = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		Candidate candiTwo;
		
		candiTwo = candiOne.copy();
		
		assertTrue(candiOne.toString().equals(candiTwo.toString()));
	}
	
	@Test
	public void testCopyConfirmDifferentObjects() throws ElectionException {
		Candidate candiOne = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		Candidate candiTwo;
		
		candiTwo = candiOne.copy();
		assertNotSame(candiOne, candiTwo);		
	}
	/*
	 *    *END* Test Section for Candidate.copy() *END*
	 */
	
	
	/*
	 *    Test Section for Candidate.getName()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getName()}.
	 */
	@Test
	public void testGetNameNormal() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		assertEquals("MARSHALL, John", candi.getName());
	}
	
	@Test
	public void testGetNameShort() throws ElectionException {
		Candidate candi = new Candidate("x", "The Greens", "GRN", 0);
		assertEquals("x", candi.getName());
	}
	
	@Test
	public void testGetNameLong() throws ElectionException {
		Candidate candi = new Candidate("xxxxxxxxxxxHOWARTHxxxxxxxxxxx, xxxxxxxxxxxLukexxxxxxxxxxx", 
				"The Greens", "GRN", 0);
		assertEquals("xxxxxxxxxxxHOWARTHxxxxxxxxxxx, xxxxxxxxxxxLukexxxxxxxxxxx", candi.getName());
	}
	
	@Test
	public void testGetNameWithSpacesAtStart() throws ElectionException {
		Candidate candi = new Candidate("   MARSHALL, John", "The Greens", "GRN", 0);
		assertEquals("MARSHALL, John", candi.getName());
	}
	
	@Test
	public void testGetNameWithSpacesAtEnd() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John   ", "The Greens", "GRN", 0);
		assertEquals("MARSHALL, John", candi.getName());
	}
	/*
	 *    *END* Test Section for Candidate.getName() *END*
	 */
	

	/*
	 *    Test Section for Candidate.getParty()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getParty()}.
	 */
	@Test
	public void testGetPartyNormal() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		assertEquals("The Greens", candi.getParty());
	}
	
	@Test
	public void testGetPartyShort() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "x", "GRN", 0);
		assertEquals("x", candi.getParty());
	}
	
	@Test
	public void testGetPartyLong() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", 
				"xxxxxxxxxxxxxxxxxxxxxxxThe Greensxxxxxxxxxxxxxxxxxxxxxxx", "GRN", 0);
		assertEquals("xxxxxxxxxxxxxxxxxxxxxxxThe Greensxxxxxxxxxxxxxxxxxxxxxxx", candi.getParty());
	}
	
	@Test
	public void testGetPartyWithSpacesAtStart() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "   The Greens", "GRN", 0);
		assertEquals("The Greens", candi.getParty());
	}
	
	@Test
	public void testGetPartyWithSpacesAtEnd() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens   ", "GRN", 0);
		assertEquals("The Greens", candi.getParty());
	}
	/*
	 *    *END* Test Section for Candidate.getParty() *END*
	 */


	/*
	 *    Test Section for Candidate.getVoteCount()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCount()}.
	 */
	@Test
	public void testGetVoteCountNormal() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		assertEquals(0, candi.getVoteCount());
	}

	@Test
	public void testGetVoteCountHighNumber() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 1234567890);
		assertEquals(1234567890, candi.getVoteCount());
	}
	/*
	 *    *END* Test Section for Candidate.getVoteCount() *END*
	 */
	
	
	/*
	 *    Test Section for Candidate.GetVoteCountString()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCountString()}.
	 */
	@Test
	public void testGetVoteCountStringNormal() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 5);
		assertEquals("5", candi.getVoteCountString());
	}
	
	@Test
	public void testGetVoteCountStringHighNumber() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 1234567890);
		assertEquals("1234567890", candi.getVoteCountString());
	}
	/*
	 *    *END* Test Section for Candidate.GetVoteCountString() *END*
	 */
	
	
	/*
	 *    Test Section for Candidate.incrementVoteCount()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#incrementVoteCount()}.
	 */
	@Test
	public void testIncrementVoteCountNormal() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
		candi.incrementVoteCount();
		assertEquals(1, candi.getVoteCount());
	}
	
	@Test
	public void testIncrementVoteCountAddOneToHighNumber() throws ElectionException {
		int highNumber = 1234567890;
		int finalHighNumber = highNumber + 1;
		
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", highNumber);
		candi.incrementVoteCount();
		assertEquals(finalHighNumber, candi.getVoteCount());
	}
	
	@Test
	public void testIncrementVoteCountAddSeventyMillionVotes() throws ElectionException {
		int AddSeventyMillion = 70000000;
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
				
		for (int i = 0; i < AddSeventyMillion; i++){
			candi.incrementVoteCount();
		}
		
		assertEquals(AddSeventyMillion, candi.getVoteCount());
	}
	
	@Test
	public void testIncrementVoteCountAddSeventyMillionVotesToHighNumber() throws ElectionException {
		int AddSeventyMillion = 70000000;
		int highNumber = 1234567890;
		int finalHighNumber = AddSeventyMillion + highNumber;
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", highNumber);

		for (int i = 0; i < AddSeventyMillion; i++){
			candi.incrementVoteCount();
		}
		
		assertEquals(finalHighNumber, candi.getVoteCount());
	}
	/*
	 *    *END* Test Section for Candidate.incrementVoteCount() *END*
	 */
	
	
	/*
	 *    Test Section for Candidate.ToString()
	 */
	/**
	 * Test method for {@link asgn1Election.Candidate#toString()}.
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
		
		Candidate candi = new Candidate(testName, "The Greens", testAbbr, testVote);
		
		assertEquals(answerText, candi.toString());
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
		
		Candidate candi = new Candidate(testName, "The Greens", testAbbr, testVote);
		
		assertEquals(answerText, candi.toString());
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
		
		Candidate candi = new Candidate(testName, "The Greens", testAbbr, testVote);
		
		assertEquals(answerText, candi.toString());
	}
	/*
	 *    *END* Test Section for Candidate.ToString() *END*
	 */
}
