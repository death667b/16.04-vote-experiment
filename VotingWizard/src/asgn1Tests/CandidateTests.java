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
	
	
	
	/**
	 * Test method for {@link asgn1Election.Candidate#copy()}.
	 */
	@Test
	public void testCopy() throws ElectionException {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getName()}.
	 */
	@Test
	public void testGetName() throws ElectionException {
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", -100000000);

	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getParty()}.
	 */
	@Test
	public void testGetParty() throws ElectionException {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCount()}.
	 */
	@Test
	public void testGetVoteCount() throws ElectionException {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCountString()}.
	 */
	@Test
	public void testGetVoteCountString() throws ElectionException {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#incrementVoteCount()}.
	 */
	@Test
	public void testIncrementVoteCount() throws ElectionException {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#toString()}.
	 */
	@Test
	public void testToString() throws ElectionException {
		//fail("Not yet implemented"); // TODO
		
		/*
		 * displayfieldwidth max 30 - maybe
		 * 
		 * 
		 */
	}

}
