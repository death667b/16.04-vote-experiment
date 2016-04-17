/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

/**
 * 
 * Simple class to provide an Integer representation of the index of a candidate
 * in an election, and to restrict it to a sensible range.
 * 
 * @author hogan
 * 
 */
public class CandidateIndex implements Comparable<CandidateIndex> {
	/** Minimum index for the candidate */
	public static final int MinCandidates = 1;

	/** Maximum value of the candidateIndex */
	public static final int MaxCandidates = 15;

	/**
	 * Simple static boolean method to check that the index lies within the
	 * valid range
	 * 
	 * @param value <code>int</code> to be tested
	 * @return <code>(MinCandidates <= value <= MaxCandidates)</code>
	 */
	public static boolean inRange(int value) {
		return ((value >= CandidateIndex.MinCandidates) && (value <= CandidateIndex.MaxCandidates));
	}

	/** Actual value of the candidate index */
	private int value;

	/**
	 * Constructor for the <code>CandidateIndex</code> class
	 *
	 * @param index <code>int</code> index of candidate
	 */
	public CandidateIndex(int index) {
		this.value = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CandidateIndex other) {
		int oValue = other.value;
		if (this.value == oValue) {
			return 0;
		} else if (this.value < oValue) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * Simple method to create a deep copy of the candidate index
	 * 
	 * @return a deep copy of this <code>CandidateIndex</code>
	 */
	public CandidateIndex copy() {
		return new CandidateIndex(this.value);
	}

	/**
	 * Simple method to increment the <code>CandidateIndex</code>
	 */
	public void incrementIndex() {
		++this.value;
	}

	/**
	 * Simple Setter for the <code>CandidateIndex</code>
	 * 
	 * @param newValue
	 *            <code>int</code> containing the chosen value
	 */
	public void setValue(int newValue) {
		this.value = newValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + this.value;
	}
}
