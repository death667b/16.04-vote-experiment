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
	public void testCandidatePassNormal() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("MARSHALL, John", "The Greens", "GRN", 0);
	}
	@Test(expected = ElectionException.class)
	public void testCandidateFailMissingNameNoSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("", "The Greens", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateFailMissingNameWithSpaces() throws ElectionException {
		@SuppressWarnings("unused")
		Candidate candi = new Candidate("   ", "The Greens", "GRN", 0);
	}
	
	@Test(expected = ElectionException.class)
	public void testCandidateFailMissingNameWithNull() throws ElectionException {
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
