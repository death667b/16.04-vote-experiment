package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.CandidateIndex;

public class CandidateIndexTests {
	private CandidateIndex candi;
	
	@Before
	public void setUp() {
		candi = new CandidateIndex(5);
	}
	
	@Test
	public void testInRange() {
		assertTrue(candi.inRange(12));
	}

	@Test
	public void testCandidateIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
