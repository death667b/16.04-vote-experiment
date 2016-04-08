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
		
		int elimPostionNumber = Integer.parseInt(elim.toString());
		
		CandidateIndex addVoteToIndex = null;
		Candidate addVotetoCandidate = null;

		for (Vote vote : voteList){		
			addVoteToIndex = getPrefthKey(vote,cds,elimPostionNumber);
			
			if (addVoteToIndex != null){
				addVotetoCandidate = cds.get(addVoteToIndex);
				addVotetoCandidate.incrementVoteCount();
				
				cds.put(addVoteToIndex, addVotetoCandidate);
			}

		}
		cds.remove(elim);
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
		Candidate nextCandidate = null;
		CandidateIndex candiIndex;
		
		nextCandidate = processVote(v, pref, cds);
		
		candiIndex = getProcessedCandidateIndex(cds, nextCandidate);
		
		return candiIndex;
	}

	/**
	 * Using the found Candidate from preferences, search the original TreeMap for the CandidateIndex
	 * @param cds Candidate TreeMap 
	 * @param find Candidate Candidate to search index from
	 * @return Index of the candidate that needs up vote for
	 */
	private CandidateIndex getProcessedCandidateIndex(TreeMap<CandidateIndex, Candidate> cds, Candidate foundCandidate) {
		CandidateIndex candidateIndex = null;
		Candidate originalCandidate = null;
		
		if (foundCandidate != null){
			for (Map.Entry<CandidateIndex, Candidate> treeMap : cds.entrySet()){
				originalCandidate = treeMap.getValue();
				if (originalCandidate.equals(foundCandidate)){
					candidateIndex = treeMap.getKey();
				}
			}
		}
		
		return candidateIndex;
	}

	/**
	 * Process vote looking for the next preference to count based from the eliminated candidate
	 * @param v Unordered list of votes
	 * @param cds TreeMap set of all active candidates
	 * @param pref The current preference of the eliminated candidate
	 * @return CandidateIndex Next preference to apply a vote to
	 */
	private Candidate processVote(Vote v, int pref,	TreeMap<CandidateIndex, Candidate> cds) {
		TreeMap<CandidateIndex, Candidate> cdsReordered = null, cdsDeepCopy = null;
		int positionCounter, previousPrefNumber, firstPreference;
		CandidateIndex removeCandiIndex = null;
		ArrayList<Integer> previousPrefList;
		Candidate nextCandidate = null;
		int[] candidateActiveList;
		
		//Setup
		positionCounter = 0;
		firstPreference = 1;
		
		previousPrefNumber = getPrefNumber(cds);
		
		removeCandiIndex = new CandidateIndex(pref);
		previousPrefList = new ArrayList<Integer>();
		
		for (; previousPrefNumber >= firstPreference; previousPrefNumber--){
			previousPrefList.add(previousPrefNumber);
		}

		cdsDeepCopy = deepCopyCandidateList(cds);
		cdsDeepCopy.remove(removeCandiIndex);

		candidateActiveList = new int[numCandidates];
		candidateActiveList = activeCandidateList(cdsDeepCopy);
		
		// Find the Candidate if applicable
		for (int votePref : v){
			positionCounter++;
			if ((pref == positionCounter) && (previousPrefList.contains(votePref))){ 
				if(checkNotFalsePositive(candidateActiveList, v, votePref)){ 
					cdsReordered = reorderCandidateList(v, cdsDeepCopy);
					nextCandidate = getNextAvailableCandidate(votePref, cdsReordered);
				}
			}
		}
		
		return nextCandidate;
	}
	
	private Vote getDonkeyVote() { //TODO Add comments
		Vote donkeyVote = new VoteList(numCandidates);
		int firstPreference;
		
		firstPreference = 1;
		
		for (int votePreference = firstPreference; votePreference <= numCandidates; votePreference++){
			donkeyVote.addPref(votePreference);
		}
		
		return donkeyVote;
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
	 * Create a deep copy of a candidate TreeMap
	 * @param cds Original candidate TreeMap
	 * @return Deep copied candidate TreeMap
	 */
	private TreeMap<CandidateIndex, Candidate> deepCopyCandidateList (TreeMap<CandidateIndex, Candidate> cds){
		TreeMap<CandidateIndex, Candidate> deepCopy = new TreeMap<CandidateIndex, Candidate>();
		
		for (Map.Entry<CandidateIndex, Candidate> cloneIter : cds.entrySet()){
			deepCopy.put(cloneIter.getKey(), cloneIter.getValue());
		}
		
		return deepCopy;
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
	
	/**
	 * Find the next preference number based on the original number of candidates and
	 * the current active list of candidates after preferences have been counted.
	 * @return int numCandidates - CandidateCollection.size() + 1
	 */
	private int getPrefNumber(TreeMap<CandidateIndex, Candidate> cds){
		int currentCandidateSize, returnCount, addOneForNextPref;
		
		addOneForNextPref = 1;
		
		currentCandidateSize = cds.size();
		returnCount = numCandidates - currentCandidateSize + addOneForNextPref;
		
		return returnCount;
	}
	
	/**
	 * Reorder the list of candidates to align with a new ordered list of votes 
	 * this is sorted by preference order
	 * @param vote Original collection of vote
	 * @param orderedVote Reordered collection of votes eg. 1,2,...,n = numCandidates
	 * @param cds Original list of candidates
	 * @return Reordered list of candidates sorted by preference
	 */
	private TreeMap<CandidateIndex, Candidate> reorderCandidateList
			(Vote vote, TreeMap<CandidateIndex, Candidate> cds){
		
		CandidateIndex newCandidateIndex = null, oldCandidateIndex = null;
		Candidate newCandidate = null;
		TreeMap<CandidateIndex, Candidate> newCandidateList = new TreeMap<CandidateIndex, Candidate>();
		Vote orderedVote = null, donkeyVote = null;
		int counter;
		
		//Setup to find the Candidate
		orderedVote = getDonkeyVote();  //TODO Change to donkeyVote
		//orderedVote = vote.invertVote();
		
		
		for (int newVote : orderedVote){
			counter = 0;
			
			for (int oldVote : vote){
				counter++;
				
				if (newVote == oldVote){
					oldCandidateIndex = new CandidateIndex(counter);
					newCandidateIndex = new CandidateIndex(newVote);
					newCandidate = cds.get(oldCandidateIndex);
					newCandidateList.put(newCandidateIndex, newCandidate);
				}		
			}
		}
	
		return newCandidateList;
	}
	
	/**
	 * Get the next preference available from an ordered candidate list.
	 * Based on the current preference select the next not null candidate.
	 * @param currentPref Preferred next candidateIndex number
	 * @return Next available Candidate object or Null
	 */
	private Candidate getNextAvailableCandidate(int currentPref, TreeMap<CandidateIndex, Candidate> cdsReordered){
		int nextPrefNumber, addOneForNextPreference = 1;
		CandidateIndex findIndex = null;
		Candidate candiHolder = null;
		
		nextPrefNumber = currentPref + addOneForNextPreference;
		
		for (;nextPrefNumber <= numCandidates; nextPrefNumber++){
			findIndex = new CandidateIndex(nextPrefNumber);
			candiHolder = cdsReordered.get(findIndex);
			if (candiHolder != null){
				//Return on first not null candidateIndex
				return candiHolder;
			}
		}	
		
		return null;
	}
}
