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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

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
	
	@Test
	public void testCandidateNameLengthBorderCasePassWith20() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("12345678901234567890", "The Greens", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateNameLengthBorderCaseFailWith21() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("123456789012345678901", "The Greens", "GRN", 0);
	}
	
	@Test
	public void testCandidateNameLengthBorderCasePassWith20AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("   12345678901234567890   ", "The Greens", "GRN", 0);
	}

	@Test(expected = ElectionException.class)
	public void testCandidateNameLengthBorderCaseFailWith21AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("   123456789012345678901   ", "The Greens", "GRN", 0);
	}
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
	
	@Test
	public void testCandidatePartyNameLengthBorderCasePassWith30() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "123456789012345678901234567890", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidatePartyNameLengthBorderCaseFailWith31() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "1234567890123456789012345678901", "GRN", 0);
	}
	
	@Test
	public void testCandidatePartyNameLengthBorderCasePassWith30AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "   123456789012345678901234567890   ", "GRN", 0);
	}

	@Test(expected = ElectionException.class)
	public void testCandidatePartyNameLengthBorderCaseFailWith31AndSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "   1234567890123456789012345678901   ", "GRN", 0);
	}
	/*
	 *    *END* Test Section for Candidate Constructor focusing on Party Name *END*
	 */
	
	
	/*
	 *    Test Section for Candidate Constructor focusing on voteCount
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateVoteCountFailBorderCaseFailNegitive() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", -1);
	}
	
	@Test
	public void testCandidateVoteCountFailBorderCasePassPositiveNonZero() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 1);
	}
	
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
	
	
	/**
	 * Test method for {@link asgn1Election.Candidate#candidateListing()}.
	 */
	@Test
	public void testCandidateListing() {
		//fail("Not yet implemented"); // TODO
		/*
		 * max length for return string is name(20)+fullpartyfield(30)+abbr 
		 * 
		 */
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#copy()}.
	 */
	@Test
	public void testCopy() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getName()}.
	 */
	@Test
	public void testGetName() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getParty()}.
	 */
	@Test
	public void testGetParty() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCount()}.
	 */
	@Test
	public void testGetVoteCount() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#getVoteCountString()}.
	 */
	@Test
	public void testGetVoteCountString() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#incrementVoteCount()}.
	 */
	@Test
	public void testIncrementVoteCount() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn1Election.Candidate#toString()}.
	 */
	@Test
	public void testToString() {
		//fail("Not yet implemented"); // TODO
		
		/*
		 * displayfieldwidth max 30 - maybe
		 * 
		 * 
		 */
	}

}
