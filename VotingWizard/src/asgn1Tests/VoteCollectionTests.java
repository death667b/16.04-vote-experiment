/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.CandidateIndex;
import asgn1Election.ElectionException;
import asgn1Election.Vote;
import asgn1Election.VoteCollection;
import asgn1Election.VoteList;

/**
 * @author Ian
 *
 */
public class VoteCollectionTests {

	VoteCollection instanceCreateTest, vc;
	
	int numberOfTestCandidates = 15;
	
	@Before
	public void setup() throws ElectionException{
		vc = new VoteCollection(numberOfTestCandidates);
		
		buildVoteCollection();
	}
	
	
	/*
	 *    Test Section for VoteCollection Constructor
	 */
	/**
	 * Test method for {@link asgn1Election.VoteCollection#VoteCollection(int)}.
	 * @throws ElectionException 
	 */
	@Test
	public void testVoteCollectionNormalNotNull() throws ElectionException {
		instanceCreateTest = new VoteCollection(5);
		assertNotNull(instanceCreateTest);
	}
	
	@Test
	public void testVoteCollectionNormalInstanceOf() throws ElectionException {
		instanceCreateTest = new VoteCollection(5);
		assertTrue(instanceCreateTest instanceof VoteCollection);
	}
	
	@Test(expected = ElectionException.class)
	public void testVoteCollectionLowerBounderyFail() throws ElectionException {
		instanceCreateTest = new VoteCollection(0);
		assertNull(instanceCreateTest);
	}
	
	@Test
	public void testVoteCollectionLowerBounderyPass() throws ElectionException {
		instanceCreateTest = new VoteCollection(1);
		assertNotNull(instanceCreateTest);
	}
	
	@Test(expected = ElectionException.class)
	public void testVoteCollectionUpperBounderyFail() throws ElectionException {
		instanceCreateTest = new VoteCollection(16);
		assertNull(instanceCreateTest);
	}
	
	@Test
	public void testVoteCollectionUpperBounderyPass() throws ElectionException {
		instanceCreateTest = new VoteCollection(15);
		assertNotNull(instanceCreateTest);
	}

	
	/*
	 *    Test Section for CountPrefVotes Constructor
	 */
	/**
	 * Test method for {@link asgn1Election.VoteCollection#countPrefVotes(java.util.TreeMap, asgn1Election.CandidateIndex)}.
	 */
	@Test
	public void testCountPrefVotes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn1Election.VoteCollection#countPrimaryVotes(java.util.TreeMap)}.
	 */
	/*@Test
	public void testCountPrimaryVotes() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link asgn1Election.VoteCollection#emptyTheCollection()}.
	 */
	/*@Test
	public void testEmptyTheCollection() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link asgn1Election.VoteCollection#getFormalCount()}.
	 */
	/*@Test
	public void testGetFormalCount() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link asgn1Election.VoteCollection#getInformalCount()}.
	 */
	/*@Test
	public void testGetInformalCount() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link asgn1Election.VoteCollection#includeFormalVote(asgn1Election.Vote)}.
	 */
	/*@Test
	public void testIncludeFormalVote() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link asgn1Election.VoteCollection#updateInformalCount()}.
	 */
	/*@Test
	public void testUpdateInformalCount() {
		fail("Not yet implemented");
	}*/
			
	private void buildVoteCollection(){
		vc.includeFormalVote(buildVote("15,10,5,13,9,8,4,6,7,11,1,12,3,14,2"));
		vc.includeFormalVote(buildVote("1,7,4,8,5,2,13,3,11,12,6,14,15,9,10"));
		vc.includeFormalVote(buildVote("10,15,4,3,7,14,13,5,1,9,11,12,6,8,2"));
		vc.includeFormalVote(buildVote("3,4,14,1,6,13,9,2,12,5,10,8,15,11,7"));
		vc.includeFormalVote(buildVote("4,14,10,1,15,13,11,2,3,7,6,9,12,5,8"));
		vc.includeFormalVote(buildVote("15,13,2,11,6,12,5,4,8,1,7,9,14,10,3"));
		vc.includeFormalVote(buildVote("14,11,9,10,15,2,13,4,3,7,8,12,5,6,1"));
		vc.includeFormalVote(buildVote("10,7,11,6,15,9,5,12,8,1,2,4,14,3,13"));
		vc.includeFormalVote(buildVote("10,12,6,1,11,15,13,5,8,3,7,4,14,2,9"));
		vc.includeFormalVote(buildVote("13,6,8,15,2,3,14,9,11,12,5,1,7,4,10"));
		vc.includeFormalVote(buildVote("5,13,11,2,10,3,9,14,12,6,1,15,4,8,7"));
		vc.includeFormalVote(buildVote("7,5,10,13,11,14,4,9,15,8,12,2,6,1,3"));
		vc.includeFormalVote(buildVote("14,7,9,1,5,3,8,12,10,4,11,13,6,2,15"));
		vc.includeFormalVote(buildVote("6,9,4,5,1,14,15,12,11,8,3,13,2,7,10"));
		vc.includeFormalVote(buildVote("9,11,13,1,4,5,15,7,10,3,6,2,14,12,8"));
		vc.includeFormalVote(buildVote("1,15,2,8,11,9,12,5,14,4,6,7,10,3,13"));
		vc.includeFormalVote(buildVote("7,15,8,6,2,5,3,10,1,14,13,12,9,4,11"));
		vc.includeFormalVote(buildVote("9,4,7,8,11,14,2,10,1,3,13,5,6,12,15"));
		vc.includeFormalVote(buildVote("6,14,8,15,2,5,3,7,10,1,12,9,4,13,11"));
		vc.includeFormalVote(buildVote("3,2,5,12,1,4,15,13,11,6,8,7,10,9,14"));
		vc.includeFormalVote(buildVote("15,4,10,9,14,8,13,7,3,6,2,1,5,12,11"));
		vc.includeFormalVote(buildVote("3,4,11,15,9,2,7,13,5,14,12,8,6,1,10"));
		vc.includeFormalVote(buildVote("7,6,3,15,1,11,9,13,10,14,2,8,5,4,12"));
		vc.includeFormalVote(buildVote("14,1,9,4,13,2,15,6,12,5,7,11,8,10,3"));
		vc.includeFormalVote(buildVote("3,2,5,10,11,12,1,4,7,8,6,15,14,13,9"));
		vc.includeFormalVote(buildVote("6,9,5,1,11,3,7,8,15,4,2,10,14,13,12"));
		vc.includeFormalVote(buildVote("4,2,8,6,7,10,13,1,9,15,14,12,11,5,3"));
		vc.includeFormalVote(buildVote("6,4,15,1,13,12,10,14,3,8,5,9,2,7,11"));
		vc.includeFormalVote(buildVote("11,6,10,3,14,4,9,13,1,2,5,15,12,7,8"));
		vc.includeFormalVote(buildVote("4,13,11,14,3,10,2,1,9,5,6,8,12,7,15"));
		vc.includeFormalVote(buildVote("9,3,5,13,14,2,6,4,10,15,11,1,8,12,7"));
		vc.includeFormalVote(buildVote("2,4,6,10,15,7,5,1,9,3,8,14,11,12,13"));
		vc.includeFormalVote(buildVote("3,9,5,15,4,10,1,7,8,14,2,13,11,6,12"));
		vc.includeFormalVote(buildVote("9,10,8,2,5,6,12,3,7,11,4,13,15,1,14"));
		vc.includeFormalVote(buildVote("15,6,1,12,7,4,2,10,8,13,5,3,9,11,14"));
		vc.includeFormalVote(buildVote("5,2,9,14,13,11,15,6,8,12,7,3,4,10,1"));
		vc.includeFormalVote(buildVote("2,15,10,12,1,14,8,13,3,6,7,4,9,5,11"));
		vc.includeFormalVote(buildVote("1,9,13,6,14,12,7,5,3,10,11,4,8,2,15"));
		vc.includeFormalVote(buildVote("2,15,4,6,13,11,12,14,9,10,5,1,8,3,7"));
		vc.includeFormalVote(buildVote("13,3,12,1,7,14,11,10,8,5,15,9,4,6,2"));
		vc.includeFormalVote(buildVote("10,11,3,4,13,6,14,8,7,9,5,1,12,15,2"));
		vc.includeFormalVote(buildVote("7,4,1,11,15,8,6,13,2,5,10,3,9,12,14"));
		vc.includeFormalVote(buildVote("2,14,9,5,7,3,1,15,8,13,10,12,6,4,11"));
		vc.includeFormalVote(buildVote("6,9,15,13,7,11,5,10,4,12,14,8,2,3,1"));
		vc.includeFormalVote(buildVote("1,9,13,4,2,5,15,3,7,8,11,6,10,12,14"));
		vc.includeFormalVote(buildVote("13,3,14,12,6,2,15,8,1,9,4,7,11,5,10"));
		vc.includeFormalVote(buildVote("14,11,2,6,5,1,13,3,15,12,7,4,9,10,8"));
		vc.includeFormalVote(buildVote("5,9,1,2,8,6,14,13,15,11,10,4,7,3,12"));
		vc.includeFormalVote(buildVote("7,8,15,14,1,12,10,3,4,6,9,11,5,2,13"));
		vc.includeFormalVote(buildVote("15,9,12,14,2,5,8,3,4,13,7,6,1,11,10"));
		vc.includeFormalVote(buildVote("3,9,10,6,4,2,8,15,1,11,5,14,12,13,7"));
		vc.includeFormalVote(buildVote("4,14,8,15,7,11,10,9,1,5,3,2,13,12,6"));
		vc.includeFormalVote(buildVote("15,3,9,13,7,6,8,4,10,14,12,5,2,1,11"));
		vc.includeFormalVote(buildVote("12,9,1,11,3,13,7,6,4,2,8,14,5,10,15"));
		vc.includeFormalVote(buildVote("15,1,10,9,12,6,2,7,5,11,3,8,4,14,13"));
		vc.includeFormalVote(buildVote("1,12,5,4,11,6,8,13,15,9,10,3,7,2,14"));
		vc.includeFormalVote(buildVote("6,4,1,14,11,13,9,10,5,15,7,2,8,3,12"));
		vc.includeFormalVote(buildVote("13,1,2,11,12,4,15,14,9,6,8,5,10,7,3"));
		vc.includeFormalVote(buildVote("6,12,3,7,14,4,5,9,10,2,15,8,13,1,11"));
		vc.includeFormalVote(buildVote("11,12,7,9,5,10,6,3,4,1,14,2,8,15,13"));
		vc.includeFormalVote(buildVote("8,14,3,5,7,6,13,9,1,12,4,10,15,11,2"));
		vc.includeFormalVote(buildVote("11,4,6,1,10,14,12,2,9,8,7,3,13,5,15"));
		vc.includeFormalVote(buildVote("15,8,13,1,4,14,6,12,7,3,5,9,10,11,2"));
		vc.includeFormalVote(buildVote("11,6,10,13,5,4,3,1,8,12,15,2,9,14,7"));
		vc.includeFormalVote(buildVote("8,3,10,14,7,13,5,12,4,2,6,11,9,1,15"));
		vc.includeFormalVote(buildVote("4,11,9,5,1,3,15,6,8,2,14,7,12,13,10"));
		vc.includeFormalVote(buildVote("14,9,4,10,15,12,5,2,6,8,13,3,7,1,11"));
		vc.includeFormalVote(buildVote("13,15,5,9,14,1,4,2,12,3,10,8,11,6,7"));
		vc.includeFormalVote(buildVote("14,6,11,8,13,7,1,3,12,5,2,10,15,9,4"));
		vc.includeFormalVote(buildVote("6,10,11,12,15,13,5,3,1,8,4,9,2,7,14"));
		vc.includeFormalVote(buildVote("3,10,15,1,12,11,9,8,14,5,6,13,4,2,7"));
		vc.includeFormalVote(buildVote("4,9,1,2,3,7,10,11,14,13,6,8,15,12,5"));
		vc.includeFormalVote(buildVote("4,5,7,9,15,6,11,14,12,13,3,1,2,10,8"));
		vc.includeFormalVote(buildVote("1,14,3,11,4,7,9,12,5,8,10,6,13,2,15"));
		vc.includeFormalVote(buildVote("4,7,9,10,11,15,5,13,6,8,1,3,2,12,14"));
		vc.includeFormalVote(buildVote("5,15,13,7,12,4,10,14,3,1,6,11,2,8,9"));
		vc.includeFormalVote(buildVote("2,1,13,4,11,15,10,12,7,6,9,14,8,5,3"));
		vc.includeFormalVote(buildVote("6,12,1,8,11,10,3,4,2,5,14,7,9,13,15"));
		vc.includeFormalVote(buildVote("15,11,12,3,6,7,8,5,14,2,9,1,13,10,4"));
		vc.includeFormalVote(buildVote("2,13,7,8,9,4,3,11,15,12,5,14,1,6,10"));
		vc.includeFormalVote(buildVote("15,1,11,9,5,8,12,3,4,7,2,10,14,13,6"));
		vc.includeFormalVote(buildVote("15,10,1,5,12,3,7,14,4,2,8,11,9,6,13"));
		vc.includeFormalVote(buildVote("4,7,1,6,13,2,10,5,14,9,3,8,11,12,15"));
		vc.includeFormalVote(buildVote("12,2,9,5,11,4,1,6,7,14,8,3,13,15,10"));
		vc.includeFormalVote(buildVote("1,9,10,12,11,14,8,5,6,2,7,3,4,13,15"));
		vc.includeFormalVote(buildVote("3,10,8,1,15,11,4,14,9,7,12,6,13,5,2"));
		vc.includeFormalVote(buildVote("4,13,8,14,6,1,10,9,5,12,15,11,7,3,2"));
		vc.includeFormalVote(buildVote("11,8,3,15,5,14,12,9,4,10,6,7,1,2,13"));
		vc.includeFormalVote(buildVote("3,4,10,2,1,7,15,9,11,13,14,6,12,8,5"));
		vc.includeFormalVote(buildVote("7,3,9,10,1,5,12,15,8,2,14,4,11,13,6"));
		vc.includeFormalVote(buildVote("12,5,13,7,9,10,3,14,11,4,8,15,1,2,6"));
		vc.includeFormalVote(buildVote("8,7,15,5,1,6,14,12,2,3,4,13,10,9,11"));
		vc.includeFormalVote(buildVote("7,14,12,8,13,11,1,15,4,9,5,3,10,6,2"));
		vc.includeFormalVote(buildVote("13,2,3,5,4,14,10,6,8,12,1,11,9,15,7"));
		vc.includeFormalVote(buildVote("15,14,3,6,11,8,9,13,5,12,4,2,10,7,1"));
		vc.includeFormalVote(buildVote("7,11,3,13,2,4,10,8,9,5,6,1,14,15,12"));
		vc.includeFormalVote(buildVote("4,11,3,13,8,9,6,5,1,2,14,7,10,15,12"));
		vc.includeFormalVote(buildVote("7,2,14,9,4,6,1,8,3,15,5,11,12,13,10"));
		vc.includeFormalVote(buildVote("4,2,10,1,15,8,3,11,5,13,12,6,9,7,14"));
		vc.includeFormalVote(buildVote("12,10,5,3,2,1,7,4,6,13,15,14,8,11,9"));
		vc.includeFormalVote(buildVote("13,10,11,4,8,5,7,12,1,3,2,14,9,6,15"));
		vc.includeFormalVote(buildVote("5,12,8,11,7,2,3,15,13,6,1,4,9,14,10"));
		vc.includeFormalVote(buildVote("15,6,8,2,11,1,5,3,7,12,13,14,9,10,4"));
		vc.includeFormalVote(buildVote("9,2,11,4,10,6,1,13,3,5,7,8,14,12,15"));
		vc.includeFormalVote(buildVote("14,11,3,7,15,10,13,8,2,1,12,4,9,6,5"));
		vc.includeFormalVote(buildVote("11,15,4,10,5,6,3,13,14,7,8,2,12,9,1"));
		vc.includeFormalVote(buildVote("10,6,11,12,5,14,9,15,7,3,1,4,2,8,13"));
		vc.includeFormalVote(buildVote("6,7,10,14,1,13,3,5,12,11,9,2,15,4,8"));
		vc.includeFormalVote(buildVote("10,8,11,12,14,2,9,6,15,1,4,3,13,5,7"));
		vc.includeFormalVote(buildVote("5,6,4,12,9,14,7,3,15,8,1,10,13,2,11"));
		vc.includeFormalVote(buildVote("15,8,14,10,2,13,11,3,12,1,7,6,9,5,4"));
		vc.includeFormalVote(buildVote("14,3,8,9,4,13,10,6,2,12,5,11,7,15,1"));
		vc.includeFormalVote(buildVote("2,7,4,14,1,6,12,8,10,3,15,5,13,9,11"));

	}
	
	private Vote buildVote(String voteString){
		Vote vote = new VoteList(numberOfTestCandidates);
		
		String[] votes = voteString.trim().split(",");
		int singleVote;
		
		for (String singleString : votes){
			singleVote = Integer.parseInt(singleString);
			vote.addPref(singleVote);
		}
		
		return vote;
	}
	
}
