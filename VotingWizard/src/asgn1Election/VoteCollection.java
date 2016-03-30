/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
import java.util.Iterator;
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

		
		/*
		**get last pref number (numCandidates - CandidatesRemaining + 1) = (3 - 3 + 1 = 1)
		**get elim position number
		**remove elim from cds
		
		while(lastPref = elimPos) { maybe for(vote : votes) ??
		
			reorder the vote(invertVote)
			clone cds
			
			-compare current vote order to inverted vote
			-adjust a copy of candidate list to match inverted vote
			
			find new pref number (numCandidates - CandidatesRemaining + 1) = (3 - 2 + 1 = 2)
		
			(in getPrefthKey(vote,cdsClone,nextPrefNumber) ) based of the votes from the elim candidate find the candiListing for the newPrefNumber (2)
			
			incrementVote for that candidate
		
		} 

		*/
		
		int nextPrefNumber, previousPrefNumber = getPrefNumber(cds);
		int positionCounter, elimPostionNumber = Integer.parseInt(elim.toString());
		Vote orderedVote;
		TreeMap<CandidateIndex, Candidate> cdsReordered = null;
		
		for (Vote vote : voteList){
			positionCounter = 0;
			for (int vl : vote){  // So only working for eliminated votes
				positionCounter++;
				if ((elimPostionNumber == positionCounter) && (previousPrefNumber == vl)){
					orderedVote = vote.invertVote();
					cdsReordered = sortCandidatesAndVotes(vote, orderedVote, cds);
					
					//cds.remove(elim);
					nextPrefNumber = getPrefNumber(cds);
				}
			}

		}
		
		
		
		
		
		
		
		/**********/
		
		
		
		/*int candiToRemove = Integer.parseInt(elim.toString().trim());
		CandidateIndex canIndex;
		Candidate candi;
		Vote vote;
		int voteCounter = 0;
		
		elim.setValue(2);
		
		

		voteList.
		
		for (Vote vl: voteList){
			
			vote = vl.invertVote();
			voteList.set(voteCounter, vote);
			
			canIndex = getPrefthKey(vote, cds, candiToRemove);
			candi = cds.get(canIndex);
			candi.incrementVoteCount();
			
			cds.put(canIndex, candi);
			
			voteCounter++;
		}*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Collection#countPrimaryVotes(java.util.TreeMap)
	 */
	@Override
	public void countPrimaryVotes(TreeMap<CandidateIndex, Candidate> cds) {
		CandidateIndex canIndex;
		Candidate candi;
		
		for (Vote vl : voteList){
			canIndex = getPrimaryKey(vl);
			candi = cds.get(canIndex);
			candi.incrementVoteCount();
			
			cds.put(canIndex, candi);
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
		
		
		
		
		
		return null; //TODO
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
		int voteValue, voteCounter = 1;
		CandidateIndex newCandi = null;
		
		Iterator<Integer> iter = v.iterator();
		
		while(iter.hasNext()){
			voteValue = (int) iter.next();
			if (voteValue == 1){
				newCandi = new CandidateIndex(voteCounter);
			}
			
			voteCounter++;
		}
		
		return newCandi;
    }
	
	/**
	 * Find the preference number based on the original number of candidates and
	 * the current active list of candidates after preferences have been counted.
	 * @return int numCandidates - CandidateCollection.size() + 1
	 */
	private int getPrefNumber(TreeMap<CandidateIndex, Candidate> cds){
		int currentCandidateSize = cds.size();
		int returnCount = numCandidates - currentCandidateSize + 1;
		
		return returnCount;
	}
	
	/**
	 * 
	 * @param vote
	 * @param orderedVote
	 * @param cds
	 * @return
	 */
	private TreeMap<CandidateIndex, Candidate> sortCandidatesAndVotes(Vote vote, Vote orderedVote, 
			TreeMap<CandidateIndex, Candidate> cds){
		
		CandidateIndex newCanIndex = null, oldCanIndex = null;
		Candidate newCandidate = null;
		TreeMap<CandidateIndex, Candidate> newCandidateList = new TreeMap<CandidateIndex, Candidate>();
		int voteCounter;
		
		for (int newVote : orderedVote){
			voteCounter = 0;
			
			for (int oldVote : vote){
				voteCounter++;
				
				if (newVote == oldVote){
					oldCanIndex = new CandidateIndex(voteCounter);
					newCanIndex = new CandidateIndex(newVote);
					newCandidate = cds.get(oldCanIndex);
					newCandidateList.put(newCanIndex, newCandidate);
				}		
			}
		}
		
		
		//TODO Fix up description
		
		//cdsClone.
		
		/*
			candi = cds.get(canIndex);
			candi.incrementVoteCount();
			
			cds.put(canIndex, candi);
		 * 
		 * Would the idea of invertVote simply be create a new Vote object and assign the votes 1, 2, 3,...,n.

Then in the prefcount method, where I would be calling invertVote it then compares the orig vote to the new  vote and move the candidates in the list according to the new position of the new ordered vote object.

For example
Original Vote  [3, 1, 2]
New Vote  [1, 2, 3]

So the candidate in the list that was in position 1 would move to position 3
Candidate in position 2 would move to position 1
Candidate in position 3 moves to position 2

The moving of the candidates position would be on a copy of candidatelisting.
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		
		return newCandidateList;
	}
}
