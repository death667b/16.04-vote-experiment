/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
import java.util.Iterator;
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
		
		Vote invertedVote = null;
		int[] candidateActiveList;
		int candidateToEliminate, numberOfCandidatesRemoved;
		CandidateIndex foundIndex;
		Candidate addVotetoCandidate = null;
		
		candidateToEliminate = Integer.parseInt(elim.toString());
		
		cds.remove(elim);
		numberOfCandidatesRemoved = numCandidates - cds.size();

		candidateActiveList = new int[numCandidates];
		candidateActiveList = activeCandidateList(cds);
		
		for (Vote vote : voteList){ 
			invertedVote = vote.invertVote();
			
			for (int counter = 1; counter <= numberOfCandidatesRemoved; counter++){
				if ((counter == getVoteIndex(invertedVote, candidateToEliminate)) && 
						checkNotFalsePositive(candidateActiveList, vote, counter)){
					foundIndex = getPrefthKey(vote, cds, counter+1); //TODO Fix magic
					
					if (foundIndex != null){
						addVotetoCandidate = cds.get(foundIndex);
						addVotetoCandidate.incrementVoteCount();
					}
				}
			}
		}
	}

	private int getVoteIndex(Vote vote, int candidateToEliminate){ //TODO Add comments if keeping 
		int returnCounter = 0, counter = 1;
		
		for (int v : vote){
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
			
			cds.put(candidateIndex, candidate);
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
		int protectionCounter = numCandidates;
		
		do {
			findCandidate = v.getPreference(pref);
			testCandidateForNull = cds.get(findCandidate);
			if (testCandidateForNull == null){
				pref++;
			}
			protectionCounter--;
		} while (testCandidateForNull == null && protectionCounter > 0);
		
		return findCandidate;
	}
	

	/**
	 * Creates array containing a 1 if the candidate is still active or 0 if the candidate has been eliminated.
	 * @param cds Candidate list TreeMap
	 * @return Int Array containing 1 for active or 0 for eliminated
	 */
	private int[] activeCandidateList(TreeMap<CandidateIndex, Candidate> cds) {
		int[] candidateActiveList = new int[numCandidates];
		CandidateIndex currentIndex = null;
		int currentCandidateList, addOneForAlignment = 1, oneForActiveCandidate = 1;
		
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
	private boolean checkNotFalsePositive(int[] activeCandidates, Vote vote, int compareVotePref){
		int counter = 0, candidateIsActive = 1;
		boolean returnBool = true;
		
		for (int votePref : vote){
			if (votePref < compareVotePref && activeCandidates[counter] == candidateIsActive){
				//Immediate return on failure
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
		int voteValue, voteCounter, primaryVote;
		CandidateIndex newCandidateIndex = null;
		Iterator<Integer> iterator;
		
		primaryVote = 1;
		voteCounter = 1;
		iterator = v.iterator();
		
		while(iterator.hasNext()){
			voteValue = (int) iterator.next();
			if (voteValue == primaryVote){
				newCandidateIndex = new CandidateIndex(voteCounter);
			}
			
			voteCounter++;
		}
		
		return newCandidateIndex;
    }
}
