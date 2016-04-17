package asgn1Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * Main test suite to run all tests at once
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CandidateIndexTests.class, 
	CandidateTests.class,
	VoteCollectionTests.class,
	VoteListTests.class,
	SimpleElectionTests.class,
	PrefElectionTests.class})

/**
 * @author n5372828 Ian Daniel
 *
 */
public class asgn1Tests {

}