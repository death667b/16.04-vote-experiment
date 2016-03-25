/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import asgn1Util.Strings;

/**
 *
 * <p>Class defines information about a single candidate in an election: the name, 
 * full party name, party acronym and the number of votes received. This 
 * implementation is a simple record type.</p>
 * 
 * <p>Two methods are supplied for display - the standard <code>toString()</code> -  which 
 * includes name, party acronym and vote count - and <code>candidateListing()</code>, which 
 * includes the full party name.</p>
 * 
 * @author hogan
 * 
 */
public class Candidate {
	/** Name of the candidate */
	private String name;

	/** Name of the party to which the candidate belongs */
	private String party;

	/** Name of the party to which the candidate belongs */
	private String abbrev;

	/** Number of votes the candidate accumulated so far */
	private int voteCount;

	/**
	 * Constructor for the <code>Candidate</code> class 
	 * <code>numCandidates</code> has meaning only w.r.t. a full 
	 * <code>asgn1Collection</code> lexicon, and 
	 * so requires keywords from across the collection for initialisation.  
	 * 
	 * @param candName <code>String</code> containing candidate name
	 * @param candParty <code>String</code> containing full party name
	 * @param candAbbrev <code>String</code> containing party acronym
	 * @param voteCount <code>int</code> containing initial number of votes (usually 0). 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount < 0 </code>
	 */
	public Candidate(String candName, String candParty, String candAbbrev, int voteCount) throws ElectionException {
		name = candName;
		party = candParty;
		abbrev = candAbbrev;
		this.voteCount = voteCount;
	}

	/**
	 * Display method to show candidate name, full party and party acronym.
	 * Note this representation does not include <code>voteCount</code>.  
	 * See also {@link #toString()}
	 * 
	 * @return <code>String</code> with formatted candidate display 
	 */
	public String candidateListing() {
		String nameStr = this.name;
		String partyStr = this.party;
		String abbrevStr = "(" + this.abbrev + ")" + "\n";

        int len = ElectionManager.NameField - nameStr.length();
		String str = nameStr + Strings.createPadding(' ',len);
		len = ElectionManager.FullPartyField - partyStr.length();
		str += partyStr + Strings.createPadding(' ',len);
		str += abbrevStr;
		return str;
	}

	/**
	 * Simple method to create a deep copy of the candidate
	 * 
	 * @return a deep copy of this <code>Candidate</code>
	 * @throws ElectionException - see {@link #Candidate(String,String,String,int)}. 
	 */
	public Candidate copy() throws ElectionException {
		
	}

	/**
	 * Simple Getter to return the name field
	 * 
	 * @return <code>String</code> containing <code>name</code> 
	 */
	public String getName() {
		
	}

	/**
	 * Simple Getter to return the full party name field
	 * 
	 * @return <code>String</code> containing <code>party</code> 
	 */
	public String getParty() {
		
	}

	/**
	 * Simple Getter to return the vote count for the candidate
	 * 
	 * @return <code>int</code> containing <code>voteCount</code> 
	 */
	public int getVoteCount() {
		
	}

	/**
	 * String version of the vote count Getter
	 * 
	 * @return <code>String</code> containing text version of <code>voteCount</code> 
	 */
	public String getVoteCountString() {
		
	}

	/**
	 * Simple method to increment the vote count for the candidate
	 */
	public void incrementVoteCount() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = this.name + " (" + this.abbrev + ")";
		String voteStr = ("" + this.voteCount).trim();
		int length = ElectionManager.DisplayFieldWidth - str.length()
				- voteStr.length();
		str += Strings.createPadding(' ', length) + voteStr + "\n";
		return str;
	}
}
