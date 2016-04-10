/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * <p>Implementing class for the {@link asgn1Election.Vote} interface. <code>Vote</code> 
 * should be implemented as some sort of <code>List</code>, with 
 * <code>ArrayList<Integer></code> the default choice.</p>
 * 
 * @author hogan
 * 
 */
public class VoteList implements Vote {
	/** Holds the information that comprises a single vote */
	private List<Integer> vote;

	/** Number of candidates in the election */
	private int numCandidates;

	/**
	 * <p>Simple Constructor for the <code>VoteList</code> class. <code>numCandidates</code> 
	 * is known to be in range through check on <code>VoteCollection</code>. 
	 * 
	 * @param numCandidates <code>int</code> number of candidates competing for 
	 * this seat. 
	 */
	public VoteList(int numCandidates) {
		
		this.numCandidates = numCandidates;
		vote = new ArrayList<Integer>();
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#addPref(asgn1Election.CandidateIndex)
	 */
	@Override
	public boolean addPref(int index) {
		
		if (vote.size() < numCandidates){
			vote.add(index);
			return true;
		} else {
			return false;
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#copyVote()
	 */
	@Override
	public Vote copyVote() {
		
		Vote returnVote;
		
		returnVote = new VoteList(numCandidates);
		
		for (Integer index : this.vote) {
			returnVote.addPref(index);
		}
		
		return returnVote;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#getPreference(int)
	 */
	@Override
	public CandidateIndex getPreference(int cand) {	
		
		int indexFound, indexAlignment, candidateNumber;
		CandidateIndex returnIndex = null;
		
		indexAlignment = 1;
		indexFound = vote.indexOf(cand);
		candidateNumber = indexFound + indexAlignment;
		
		returnIndex = new CandidateIndex(candidateNumber);
		
		return returnIndex;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#invertVote()
	 */
	@Override
	public Vote invertVote() {
		
		int arrayAlignment, preferenceToAdd;
		Vote invertedVote;
		
		invertedVote = new VoteList(numCandidates);
		arrayAlignment = 1;
		
		// Index becomes the value and value becomes the index
		// 3 1 2 -> 2 3 1
		for (int counter = 1; counter <= numCandidates; counter++){
			preferenceToAdd = vote.indexOf(counter) + arrayAlignment;
			invertedVote.addPref(preferenceToAdd);
		}
		
		return invertedVote;
	}

	
	/* 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Integer> iterator() {
		
		return vote.iterator();
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		for (Integer index : this.vote) {
			str += index.intValue() + " ";
		}
		return str;
	}
}
