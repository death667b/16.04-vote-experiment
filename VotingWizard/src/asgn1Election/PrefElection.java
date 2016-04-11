/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.ArrayList;
// import java.util.BitSet;  Commented out to remove warning
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
		CandidateIndex candidateToEliminate = null;
		String returnString;
		
		infiniteLoopProtection = 0;
		winningVotesRequired = findWinningVotesRequired();
		
		returnString = "";
		returnString += showResultHeader();
		returnString += "Counting primary votes; ";
		returnString += getNumCandidates() + " alternatives available\n";
		
		vc.countPrimaryVotes(cds);
		
		do{
			returnString += reportCountStatus();			
			candidateToEliminate = selectFirstLowestCandidate();
			returnString += prefDistMessage(cds.get(candidateToEliminate)) + "\n";
			
			cds.remove(candidateToEliminate);
			vc.countPrefVotes(cds, candidateToEliminate);
			
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
		int innerLoopCounter, outerLoopCounter, zeroPreference;
		
		outerLoopCounter = 0;
		zeroPreference = 0;
		
		for (int outerPreference : v){
			outerLoopCounter++;
			innerLoopCounter = 0;
			
			// Test to see if there is a duplicate vote
			// Skipping the current vote being tested
			for (int innerPreference : v){
				innerLoopCounter++;
				if (outerPreference == innerPreference && outerLoopCounter != innerLoopCounter){
					return false;
				}
			}
			
			//Test to see if preference exceeds max and min range
			if (outerPreference <= zeroPreference || outerPreference > numCandidates){
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
		int candidateOneVotes, candidateTwoVotes, firstCandidate, secondCandidate, numberNeededForClearWin, twoOrLessCandidates;
		java.util.Collection<Candidate> candidateCollection;
		ArrayList<Candidate> candidateList;
		Candidate candidateWinner = null;
		
		candidateList = new ArrayList<Candidate>();
		candidateCollection = this.cds.values();
		twoOrLessCandidates = 2;
		numberNeededForClearWin = 1;
		firstCandidate = 0;
		secondCandidate = 1;

		if (candidateCollection.size() <= twoOrLessCandidates){
			for (Candidate candidate : candidateCollection) {
				// Build candidate list for those over or equal to the winVotes value
				if (candidate.getVoteCount() >= winVotes){
					candidateList.add(candidate);
				}
			}
	
			if (candidateList.size() == numberNeededForClearWin){
				candidateWinner = candidateList.get(firstCandidate);
			} else {
				candidateOneVotes = candidateList.get(firstCandidate).getVoteCount();
				candidateTwoVotes = candidateList.get(secondCandidate).getVoteCount();
				
				// If tie, eliminate one more candidate (most likely)
				if (candidateOneVotes == candidateTwoVotes){
					candidateWinner = null;
				// In case one candidate has high votes than the other and over the winVote threshold
				} else if (candidateOneVotes > candidateTwoVotes){
					candidateWinner = candidateList.get(firstCandidate);
				} else {
					candidateWinner = candidateList.get(secondCandidate);
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
	private CandidateIndex selectFirstLowestCandidate() {
		
		CandidateIndex indexToReturn = null;
		int voteCount, sameVoteValueLastTrue;
		
		voteCount = numCandidates;
		sameVoteValueLastTrue = -1;

		for (Map.Entry<CandidateIndex, Candidate> candidateMap : cds.entrySet()) {
			Candidate candidateValue = candidateMap.getValue();
			CandidateIndex candidateKey = candidateMap.getKey();
			
			// Find the lowest vote && Make sure it is the first of the lowest if multiple
			if (voteCount > candidateValue.getVoteCount() && candidateValue.getVoteCount() != sameVoteValueLastTrue){
				voteCount = candidateValue.getVoteCount();
				sameVoteValueLastTrue = voteCount;
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
		
		return returnVoteRequired;
	}
}