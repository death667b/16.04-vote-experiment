/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.Collection;
import java.util.Iterator;

import asgn1Util.Strings;

/**
 * 
 * Subclass of <code>Election</code>, specialised to simple, first past the post voting
 * 
 * @author hogan
 * 
 */
public class SimpleElection extends Election {
	
	/**
	 * Simple Constructor for <code>SimpleElection</code>, takes name and also sets the 
	 * election type internally. 
	 * 
	 * @param name <code>String</code> containing the Election name
	 */
	public SimpleElection(String name) {
		super(name);
		type = Election.SimpleVoting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#findWinner()
	 */
	@Override
	public String findWinner() {
		String returnString = "";
		
		returnString += showResultHeader();
		returnString += "Counting primary votes;\n";
		vc.countPrimaryVotes(cds);
	
		winner = clearWinner(findWinningVotes());
		
		returnString += reportCountResult();
		returnString += reportWinner(winner);
		return returnString;
	}

	/* 
	 * (non-Javadoc)
	 * @see asgn1Election.Election#isFormal(asgn1Election.Vote)
	 */
	@Override
	public boolean isFormal(Vote v) {
		boolean returnValue = true;
		int voteValue;
		
		Iterator iter = v.iterator();
		
		while(iter.hasNext()){
			voteValue = (int) iter.next();
			if (voteValue > this.numCandidates){
				returnValue = false;
			}
		}
		
		return returnValue; //TODO
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = this.name + " - Simple Voting";
		return str;
	}
	
	// Protected and Private/helper methods below///

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#clearWinner(int)
	 */
	@Override
	protected Candidate clearWinner(int wVotes) {
		//winner and winIndex
		
		Candidate candWin = null;

		java.util.Collection<Candidate> coll = this.cds.values();

		do{
			for (Candidate cand : coll) {
				if (cand.getVoteCount() >= wVotes){
					candWin = cand;
					return candWin;
				}
			}
			
			wVotes--;
		} while (candWin == null && wVotes > 0);

		return candWin;
	}

	/**
	 * Helper method to create a string reporting the count result
	 * 
	 * @return <code>String</code> containing summary of the count
	 */
	private String reportCountResult() {
		String str = "\nSimple election: " + this.name + "\n\n"
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
	 * Find the number of winning votes required for a clear win.  
	 * Number of Votes divided by two plus one
	 * @return Minimum votes required for clear win.
	 */
	private int findWinningVotes(){
		int returnVoteRequired = 0;
		
		returnVoteRequired = numVotes / 2;
		returnVoteRequired++;
		return returnVoteRequired;
	}
}