/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.CandidateIndex;
import asgn1Election.Vote;
import asgn1Election.VoteList;

/**
 * @author n5372828
 *
 */
public class VoteListTests {

	private VoteList testVoteList;
	private Vote copyList;
	private Vote vote;
	
	@Before
	public void setup(){
		//Setup main test object
		testVoteList = new VoteList(3);
		
		//Setup vote for other testing
		vote = new VoteList(5);
		vote.addPref(3);
		vote.addPref(1);
		vote.addPref(5);
		vote.addPref(2);
		vote.addPref(4);
	}
	
	
	/*
	 *    Test Section for VoteList Constructor - Expected Working
	 */
	/**
	 * Test method for {@link asgn1Election.VoteList#VoteList(int)}.
	 */
	@Test
	public void testVoteListNotNull() {
		assertNotNull(testVoteList);
	}
	
	@Test
	public void testVoteListPassInstanceOf() {
		assertTrue(testVoteList instanceof VoteList);
	}

	
	/*
	 *    Test Section for VoteList.addPref()
	 */
	/**
	 * Test method for {@link asgn1Election.VoteList#addPref(int)}.
	 */
	@Test
	public void testAddPrefReturnsTrueOnSuccessful() {				
		assertTrue(testVoteList.addPref(1));
	}
	
	@Test
	public void testAddPrefReturnsFalseOnExceedingInit() {				
		testVoteList.addPref(1);
		testVoteList.addPref(2);
		testVoteList.addPref(3);
		
		assertFalse(testVoteList.addPref(4));
	}

	
	/*
	 *    Test Section for VoteList.copyVote()
	 */
	/**
	 * Test method for {@link asgn1Election.VoteList#copyVote()}.
	 */
	@Test
	public void testCopyVoteNoDataNotNull() {
		copyList = testVoteList.copyVote();
		
		assertNotNull(copyList);
	}
	
	@Test
	public void testCopyVoteNoDataIsInstanceOf() {
		copyList = testVoteList.copyVote();
		
		assertTrue(copyList instanceof VoteList);
	}
	
	@Test
	public void testCopyVoteNoDataNotSame() {
		copyList = testVoteList.copyVote();
		
		assertNotSame(copyList, testVoteList);
	}
	
	@Test
	public void testCopyVoteWithData() {		
		testVoteList.addPref(1);
		testVoteList.addPref(3);
		testVoteList.addPref(2);
		
		copyList = testVoteList.copyVote();
		
		assertEquals("1 3 2 ", copyList.toString());
	}

	
	/*
	 *    Test Section for VoteList.getPreference()
	 */
	/**
	 * Test method for {@link asgn1Election.VoteList#getPreference(int)}.
	 */
	@Test
	public void testGetPreference() {
		CandidateIndex testIndex = null;
		
		testIndex = new CandidateIndex(1);
		
		assertEquals(testIndex.toString() ,vote.getPreference(3).toString());
		
	}

	
	/*
	 *    Test Section for VoteList.InvertVote()
	 */
	/**
	 * Test method for {@link asgn1Election.VoteList#invertVote()}.
	 */
	@Test
	public void testInvertVote() {
		Vote vl;
		
		vl = vote.invertVote();
		
		assertEquals("2 4 1 5 3 ", vl.toString());
	}

	/*
	 *    Test Section for VoteList.Iterator()
	 */
	/**
	 * Test method for {@link asgn1Election.VoteList#iterator()}.
	 */
	@Test
	public void testIteratorNormal() {
		Iterator<Integer> iterator;
		Integer holder;
		String stringTest = "";
		
		iterator = vote.iterator();

		while(iterator.hasNext()){
			holder = iterator.next();
			
			stringTest += holder.toString() + " ";
		}
		
		assertEquals(stringTest, vote.toString());
	}
	
	
	/*
	 *    Test Section for VoteList.toString()
	 */
	/**
	 * Test method for {@link asgn1Election.VoteList#toString()}.
	 */
	@Test
	public void testToStringAddZeroPreferences() {				
		assertEquals("", testVoteList.toString());
	}

	@Test
	public void testToStringAddOnePreference() {		
		testVoteList.addPref(1);
		
		assertEquals("1 ", testVoteList.toString());
	}
	
	@Test
	public void testToStringAddThreePreference() {		
		testVoteList.addPref(1);
		testVoteList.addPref(2);
		testVoteList.addPref(3);
		
		assertEquals("1 2 3 ", testVoteList.toString());
	}
	
	@Test
	public void testToStringAddTooManyPreferences() {		
		testVoteList.addPref(1);
		testVoteList.addPref(2);
		testVoteList.addPref(3);
		testVoteList.addPref(4);
		
		assertEquals("1 2 3 ", testVoteList.toString());
	}

	@Test
	public void testToStringAddThreePreferenceReordered() {		
		testVoteList.addPref(2);
		testVoteList.addPref(1);
		testVoteList.addPref(3);
		
		assertEquals("2 1 3 ", testVoteList.toString());
	}
}
