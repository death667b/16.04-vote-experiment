/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Tests;


import asgn1Election.Collection;
import asgn1Election.PrefElection;

/**
 * 
 * Subclass of <code>PrefElection</code> to facilitate testing
 * 
 * @author hogan
 * 
 */
public class ExtendedElection extends PrefElection {
	
	/* 
	 * 	Example usage follows. Please use in your PrefElectionTests class 
	 *  Either use this as a separate object to use just for the loadVotes() case, or alternatively 
	 *  run all your PrefElection methods tests off this object. As it is a subclass, all such methods 
	 *  will be available. 
	 *  

	private ExtendedElection elec;
	

	@Before
	public void setup() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		elec = new ExtendedElection("MorgulVale");
    	elec.loadDefs();
		elec.loadVotes();
	}
	
	@Test
	public void testLoadVotesMorgulValeThirtyVotes() {
		VoteCollection vc = (VoteCollection) elec.getVoteCollection(); 
		assertTrue((vc.getFormalCount()==30)&&(vc.getInformalCount()==0));
	}
	 */

	/**
	 * Simple Constructor inheriting from Election and PrefElection
	 * 
	 * @param name <code>String</code> Election name 
	 */
	public ExtendedElection(String name) {
		super(name);
	}
	
	/**
	 * Getter for Vote Collection
	 * 
	 * @return <code>VoteCollection</code> holding the formal votes
	 */
	public Collection getVoteCollection() {
		return this.vc;
	}
}
