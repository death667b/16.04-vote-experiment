/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
// import java.util.BitSet;  Commented out to remove warning
import java.util.Iterator;
import java.util.Map;

import asgn1Util.Strings;

/**
 * 
 * Subclass of <code>Election</code>, specialised to preferential, but not optional
 * preferential voting.
 * 
 * @author hogan
 * 
 */
public class PrefElection extends Election {

	/**
	 * Simple Constructor for <code>PrefElection</code>, takes name and also sets the 
	 * election type internally. 
	 * 
	 * @param name <code>String</code> containing the Election name
	 */
	public PrefElection(String name) {
		super(name);
		type = Election.PrefVoting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#findWinner()
	 */
	@Override
	public String findWinner() {
		int infiniteLoopProtection, winningVotesRequired;
		CandidateIndex candidateIndex = null;
		String returnString = "";
		
		infiniteLoopProtection = 0;
		winningVotesRequired = findWinningVotesRequired();
		
		returnString += showResultHeader();
		returnString += "Counting primary votes; ";
		returnString += getNumCandidates() + " alternatives available\n";
		
		vc.countPrimaryVotes(cds);
		
		do{
			returnString += reportCountStatus();			
			candidateIndex = selectLowestCandidate();
			returnString += prefDistMessage(cds.get(candidateIndex)) + "\n";
			
			vc.countPrefVotes(cds, candidateIndex);
			
			infiniteLoopProtection++;
			winner = clearWinner(winningVotesRequired);
		} while(winner == null && infiniteLoopProtection <= numCandidates);
		
	
		returnString += reportCountStatus();
		returnString += reportWinner(winner);
		return returnString;
	}

	/* 
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#isFormal(asgn1Election.Vote)
	 */
	@Override
	public boolean isFormal(Vote v) {
		int voteValue, loopCounter, objCounter;
		Object voteObject;
		
		objCounter = 0;
		Iterator<Integer> iter = v.iterator();
		
		
		while(iter.hasNext()){
			voteObject = iter.next();
			voteValue = (int) voteObject;
			objCounter++;
			loopCounter = 0;			
			
			// Test to see if there is a duplicate vote
			// Skipping the current vote being tested
			for (Object obj : v){
				loopCounter++;
				if (obj.equals(voteObject) && objCounter != loopCounter){
					return false;
				}
			}
			
			//Test to see if the voted number is higher than the available candidates
			if (voteValue > this.numCandidates){
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
        String str = this.name + " - Preferential Voting";
		return str;
	}
	
	// Protected and Private/helper methods below///


	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#clearWinner(int)
	 */
	@Override
	protected Candidate clearWinner(int winVotes)  {
		int candidateOneVotes, candidateTwoVotes, oneCandidate, twoCandidates, nillVotes;
		java.util.Collection<Candidate> candidateCollection = this.cds.values();
		ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidateWinner = null;
		
		nillVotes = 0;
		oneCandidate = 1;
		twoCandidates = 2;

		if (candidateCollection.size() <= twoCandidates){
			do{
				for (Candidate candidate : candidateCollection) {
					if (candidate.getVoteCount() >= winVotes){
						candidateList.add(candidate);
					}
				}
				
				winVotes--;
			} while (candidateList == null && winVotes > nillVotes);
	
			if (candidateList.size() == oneCandidate){
				candidateWinner = candidateList.get(oneCandidate);
			} else {
				candidateOneVotes = candidateList.get(oneCandidate).getVoteCount();
				candidateTwoVotes = candidateList.get(twoCandidates).getVoteCount();
				
				if (candidateOneVotes >= candidateTwoVotes){
					candidateWinner = candidateList.get(oneCandidate);
				} else {
					candidateWinner = candidateList.get(twoCandidates);
				}
			}
			
			//Return with winner found
			return candidateWinner;
		}
		
		//Return null if there is 3 or more candidates left
		return candidateWinner;
	}

	/**
	 * Helper method to create a preference distribution message for display 
	 * 
	 * @param c <code>Candidate</code> to be eliminated
	 * @return <code>String</code> containing preference distribution message 
	 */
	private String prefDistMessage(Candidate c) {
		String str = "\nPreferences required: distributing " + c.getName()
				+ ": " + c.getVoteCount() + " votes";
		return str;
	}

	/**
	 * Helper method to create a string reporting the count progress
	 * 
	 * @return <code>String</code> containing count status  
	 */
	private String reportCountStatus() {
		String str = "\nPreferential election: " + this.name + "\n\n"
				+ candidateVoteSummary() + "\n";
		String inf = "Informal";
		String voteStr = "" + this.vc.getInformalCount();
		int length = ElectionManager.DisplayFieldWidth - inf.length()
				- voteStr.length();
		str += inf + Strings.createPadding(' ', length) + voteStr + "\n\n";

		String cast = "Votes Cast";
		voteStr = "" + this.numVotes;
		length = ElectionManager.DisplayFieldWidth - cast.length()
				- voteStr.length();
		str += cast + Strings.createPadding(' ', length) + voteStr + "\n\n";
		return str;
	}

	/**
	 * Helper method to select candidate with fewest votes
	 * 
	 * @return <code>CandidateIndex</code> of candidate with fewest votes
	 */
	private CandidateIndex selectLowestCandidate() {
		int voteCount = -1;
		CandidateIndex indexToReturn = null;

		for (Map.Entry<CandidateIndex, Candidate> candidateMap : cds.entrySet()) {
			Candidate candidateValue = candidateMap.getValue();
			CandidateIndex candidateKey = candidateMap.getKey();
			
			if (voteCount == -1 || voteCount > candidateValue.getVoteCount()){
				voteCount = candidateValue.getVoteCount();
				indexToReturn = candidateKey.copy();
			}
			
		}

		return indexToReturn;

	}
	
	/**
	 * Find the number of winning votes required for a clear win.  
	 * Number of Votes divided by two plus one
	 * @return Minimum votes required for clear win.
	 */
	private int findWinningVotesRequired(){
		int returnVoteRequired, formalCount, divideByTwo;
		
		returnVoteRequired = 0;
		divideByTwo = 2;
		formalCount = vc.getFormalCount();
			
		returnVoteRequired = formalCount / divideByTwo;
		returnVoteRequired++;
		
		return returnVoteRequired;
	}
}