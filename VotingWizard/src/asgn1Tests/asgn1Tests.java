package asgn1Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * This is an example of how to construct a test suite
 * comprising two separate unit test classes
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CandidateIndexTests.class, 
	CandidateTests.class,
	VoteCollectionTests.class,
	VoteListTests.class,
	SimpleElectionTests.class,
	PrefElectionTests.class})

public class asgn1Tests {

}