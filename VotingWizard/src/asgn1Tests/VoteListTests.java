/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn1Election.Vote;
import asgn1Election.VoteList;

/**
 * @author n5372828
 *
 */
public class VoteListTests {

	/**
	 * Test method for {@link asgn1Election.VoteList#VoteList(int)}.
	 */
	@Test
	public void testVoteList() {
		@SuppressWarnings("unused")
		VoteList testVoteList = new VoteList(3);
	}

	/**
	 * Test method for {@link asgn1Election.VoteList#addPref(int)}.
	 */
	@Test
	public void testAddPref() {
		VoteList testVoteList = new VoteList(3);
		
		assertTrue(testVoteList.addPref(1));
		assertTrue(testVoteList.addPref(2));
		assertTrue(testVoteList.addPref(3));
		assertFalse(testVoteList.addPref(4));
		assertEquals("1 2 3 ", testVoteList.toString());
	}

	/**
	 * Test method for {@link asgn1Election.VoteList#copyVote()}.
	 */
	@Test
	public void testCopyVote() {
		VoteList testVoteList = new VoteList(3);
		Vote copy;
		
		assertTrue(testVoteList.addPref(1));
		assertTrue(testVoteList.addPref(3));
		
		copy = testVoteList.copyVote();
		
		assertTrue(testVoteList.addPref(4));
		
		assertEquals("", copy.toString());
	}

	/**
	 * Test method for {@link asgn1Election.VoteList#getPreference(int)}.
	 */
	@Test
	public void testGetPreference() {
		VoteList testVoteList = new VoteList(3);
		
		assertTrue(testVoteList.addPref(1));
		assertTrue(testVoteList.addPref(2));
		assertTrue(testVoteList.addPref(3));
		
		//assertEquals(2, testVoteList.getPreference(2));
	}

	/**
	 * Test method for {@link asgn1Election.VoteList#invertVote()}.
	 */
	@Test
	public void testInvertVote() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn1Election.VoteList#iterator()}.
	 */
	@Test
	public void testIterator() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn1Election.VoteList#toString()}.
	 */
	@Test
	public void testToString() {
		//fail("Not yet implemented");
	}

}
