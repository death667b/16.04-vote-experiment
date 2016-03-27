/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
import java.util.Arrays;
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
		return new VoteList(numCandidates);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#getPreference(int)
	 */
	
	@Override
	public CandidateIndex getPreference(int cand) {
		return new CandidateIndex(cand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Vote#invertVote()
	 */
	
	/**
	 * Method to invert the vote to yield the preference order of candidates.
	 * 
	 * @return <code>Vote newVote</code> such that for all entries <code>i</code> of 
	 * <code>this</code>, <code>newVote[this[i]] = i</code>
	 */
	@Override
	public Vote invertVote() {
		Vote newVote;
		
		for (int i : this){
			newVote[this[i]] = i;
		}
		
		
		return new VoteList(5);

	}

	/* 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Integer> iterator() {
		Iterator<Integer> listInt = new Iterator<Integer>(){
			private int currentSize = vote.size();
			private int currentPosition = 0;
			
			@Override
			public boolean hasNext(){
				return (currentSize > currentPosition);
			}
			
			@Override
			public Integer next(){
				currentPosition++;
				return vote.get(currentPosition - 1);
			}
			
			@Override
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
		
		return listInt;
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
