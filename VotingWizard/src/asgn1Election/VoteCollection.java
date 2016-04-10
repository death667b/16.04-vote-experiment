/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * <p>Class to manage a collection of <code>Vote</code>s as specified by the 
 * {@link asgn1Election.Collection} interface. This implementation is based on  
 * a <code>ArrayList<E></code> data structure to enable convenient additions to the 
 * list.</p>
 * 
 * <p>The private methods {@link #getPrimaryKey(Vote) } and 
 * {@link #getPrefthKey(Vote, TreeMap, int)} are crucial to your success. Some guidance 
 * is given for these methods through comments in situ, but this is generous, and well 
 * beyond what would be provided in real practice.</p>
 * 
 * <p>As before, please note the name clash between <code>asgn1Election.Collection</code>
 * and <code>java.util.Collection</code>. 
 * 
 * @author hogan
 *
 */
public class VoteCollection implements Collection {
	/** Holds all the votes in this seat */
	private ArrayList<Vote> voteList;

	/** Number of candidates competing for this seat */
	private int numCandidates;

	/** Number of formal votes read during the election and stored in the collection */
	private int formalCount;

	/** Number of invalid votes received during the election */
	private int informalCount;

	/**
	 * Simple Constructor for the <code>VoteCollection</code> class.
	 * Most information added through mutator methods. 
	 * 
	 * @param numCandidates <code>int</code> number of candidates competing for 
	 * this seat
	 * @throws ElectionException if <code>NOT inRange(numCandidates)</code>
	 */
	public VoteCollection(int numCandidates) throws ElectionException {
		if (CandidateIndex.inRange(numCandidates)){
			this.numCandidates = numCandidates;
		} else {
			throw new ElectionException("Number of candidates is out of range.");
		}
		
		voteList = new ArrayList<Vote>();
	}
	
	
	/* 
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#countPrefVotes(java.util.TreeMap, asgn1Election.CandidateIndex)
	 */
	@Override
	public void countPrefVotes(TreeMap<CandidateIndex, Candidate> cds,
			CandidateIndex elim) {

		int numberOfCandidatesRemoved, nextPrefrenceNumber, moveToNextPreference;
		CandidateIndex foundIndex;
		Candidate addVotetoCandidate = null;
		
		moveToNextPreference = 1;
		numberOfCandidatesRemoved = numCandidates - cds.size();
		
		for (Vote vote : voteList){ 
			for (int eliminateIndex = 1; eliminateIndex <= numberOfCandidatesRemoved; eliminateIndex++){
				if (voteContainsElimination(cds, elim, vote, eliminateIndex)){
					nextPrefrenceNumber = eliminateIndex + moveToNextPreference;
					foundIndex = getPrefthKey(vote, cds, nextPrefrenceNumber);
					
					if (foundIndex != null){
						addVotetoCandidate = cds.get(foundIndex);
						addVotetoCandidate.incrementVoteCount();
					}
				}
			}
		}
	}

	
	/**
	 * Test current vote for an eliminated preference.
	 * @param cds - TreeMap containing the candidates still active in this election.
	 * @param elim - CandidateIndex being eliminated
	 * @param vote - Vote being processed
	 * @param eliminateIndex - Index of the eliminated preference
	 * @return True if vote valid for preference count, False if no preference re-allocation is required
	 */
	private boolean voteContainsElimination(TreeMap<CandidateIndex, Candidate> cds, 
			CandidateIndex elim, Vote vote, int eliminateIndex){
		
		int[] candidateActiveList;
		int candidateToEliminate;
		boolean returnBool = false;
		
		candidateActiveList = new int[numCandidates];
		candidateActiveList = activeCandidateList(cds);
		candidateToEliminate = Integer.parseInt(elim.toString());
		
		if ((eliminateIndex == getVoteIndex(vote, candidateToEliminate)) && 
				checkForFalsePositive(candidateActiveList, vote, eliminateIndex)){
			// Only return true if the index and the candidate to eliminate are true AND
			// To prevent false positives, check if the vote has already been counted
			returnBool = true;
		}
		
		return returnBool;
	}
	
	
	/**
	 * Inverts the vote and then gets the index of the preference to eliminate
	 * @param vote - Vote list to invert and index
	 * @param candidateToEliminate - Current candidate preference that is being eliminated
	 * @return Returns 0 based index of the candidate to eliminate, returns -1 if no index found
	 */
	private int getVoteIndex(Vote vote, int candidateToEliminate){
		
		int returnCounter, counter;
		Vote invertedVote = null;
		
		returnCounter = -1; 
		counter = 1;
		invertedVote = vote.invertVote();
		
		for (int v : invertedVote){
			if (v == candidateToEliminate){
				returnCounter = counter;
			}
			
			counter++;
		}
		
		return returnCounter;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#countPrimaryVotes(java.util.TreeMap)
	 */
	@Override
	public void countPrimaryVotes(TreeMap<CandidateIndex, Candidate> cds) {
		
		CandidateIndex candidateIndex;
		Candidate candidate;
		
		for (Vote vl : voteList){
			candidateIndex = getPrimaryKey(vl);
			candidate = cds.get(candidateIndex);
			candidate.incrementVoteCount();
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#emptyTheVoteList()
	 */
	@Override
	public void emptyTheCollection() {
		
		voteList.clear();
		formalCount = 0;
		informalCount = 0;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#getFormalCount()
	 */
	@Override
	public int getFormalCount() {
		
		return formalCount;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#getInformalCount()
	 */
	@Override
	public int getInformalCount() {
		
		 return informalCount; 
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#includeFormalVote(asgn1Election.Vote)
	 */
	@Override
	public void includeFormalVote(Vote v) {
		
		voteList.add(v);
		formalCount++;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#updateInformalCount()
	 */
	@Override
	public void updateInformalCount() {
		
		this.informalCount++;
	}
	
	
	/**
	 * 
	 * <p>Important helper method to find the candidate in the current vote 
	 * corresponding to a given preference. Ideally we seek the candidate who is 
	 * preference <emphasis>pref</emphasis>, but often some of the higher preferences
	 * for a given vote may already have been eliminated. So this method finds not 
	 * the <emphasis>pref-th</emphasis> candidate, but the pref-th 
	 * <emphasis>active</emphasis> candidate</p>
	 * 
	 * <p>You must therefore find a way to deal with the candidate set, to work out 
	 * which ones are still active and which aren't. This method is quite specific 
	 * to the preferential election and so does not get used for the simple ballot.</p>
	 * 
	 * @param v <code>Vote</code> to be examined to find the pref-th active candidate
	 * @param cds <code>TreeMap</code> set of all active candidates
	 * @param pref <code>int</code> specifies the preference we are looking for
	 * @return <code>(key = prefth preference still active) OR null</code>
	 * 
	 */
	private CandidateIndex getPrefthKey(Vote v,TreeMap<CandidateIndex, Candidate> cds, int pref) {

		CandidateIndex findCandidate = null;
		Candidate testCandidateForNull = null;
		int protectionCounter, failAtZero;
		
		protectionCounter = numCandidates;
		failAtZero = 0;
		
		do {
			findCandidate = v.getPreference(pref);
			testCandidateForNull = cds.get(findCandidate);
			
			if (testCandidateForNull == null){
				pref++;
			}
			
			protectionCounter--;
		//Add a protectionCounter to prevent an infinite loop
		} while (testCandidateForNull == null && protectionCounter > failAtZero);
		
		return findCandidate;
	}
	

	/**
	 * Creates array containing a 1 if the candidate is still active or 0 if the candidate has been eliminated.
	 * @param cds Candidate list TreeMap
	 * @return Int Array containing 1 for active or 0 for eliminated
	 */
	private int[] activeCandidateList(TreeMap<CandidateIndex, Candidate> cds) {
		
		int currentCandidateList, addOneForAlignment, oneForActiveCandidate;
		CandidateIndex currentIndex = null;
		int[] candidateActiveList;
		
		candidateActiveList = new int[numCandidates];
		oneForActiveCandidate = 1;
		addOneForAlignment = 1;
		
		for (Map.Entry<CandidateIndex, Candidate> findActive : cds.entrySet()){
			currentIndex = findActive.getKey();
			currentCandidateList = Integer.parseInt(currentIndex.toString());
			
			for (int counter = 0; counter <= numCandidates; counter++){
				if ((counter + addOneForAlignment) == currentCandidateList){
					candidateActiveList[counter] = oneForActiveCandidate;
				}
			}
		}

		return candidateActiveList;
	}

	
	/**
	 * Checks that a vote is actually a vote that needs to be counted.
	 * Method is a fail safe to ensure votes are not counted multiple times.
	 * 
	 * If the preference is less than the current vote being eliminated and
	 * the particular preference has an active candidate - return false.
	 * 
	 * @param activeCandidates int array.  1 for active 0 for eliminated
	 * @param vote List of unordered votes
	 * @param compareVotePref The single preference vote to compare.
	 * @return True if safe to count false if 'false positive'
	 */
	private boolean checkForFalsePositive(int[] activeCandidates, Vote vote, int compareVotePref){
		
		int counter, candidateIsActive;
		boolean returnBool;
		
		candidateIsActive = 1;
		counter = 0;
		returnBool = true;
		
		for (int votePref : vote){
			if (votePref < compareVotePref && activeCandidates[counter] == candidateIsActive){
				returnBool = false;
				return returnBool; 
			}
			counter++;
		}

		return returnBool;
	}
	
	
	/**
	 * <p>Important helper method to find the first choice candidate in the current 
	 * vote. This is always undertaken prior to distribution of preferences and so it 
	 * is not necessary to test whether the candidate remains in the set.</p>
	 * 
	 * @param v <code>Vote</code> from which first pref is to be obtained
	 * @return <code>CandidateIndex</code> of the first preference candidate
	 */
	private CandidateIndex getPrimaryKey(Vote v) {
		
		int voteCounter, primaryVote;
		CandidateIndex newCandidateIndex = null;
		
		primaryVote = 1;
		voteCounter = 1;
		
		// Return the candidateIndex with the voteCounter with the preference equals one
		for (int preference : v){
			if (preference == primaryVote){
				newCandidateIndex = new CandidateIndex(voteCounter);
			}
			
			voteCounter++;
		}
		
		return newCandidateIndex;
    }
}
