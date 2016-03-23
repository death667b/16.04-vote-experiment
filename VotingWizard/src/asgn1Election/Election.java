/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

import asgn1Util.Numbers;
import asgn1Util.NumbersException;
import asgn1Util.Strings;

/**
 * 
 * <p>Abstract class to specify the workings of a seat based election. <code>Election</code>
 * lies at the root of a hierarchy of concrete elections, here <code>SimpleElection</code>
 * and <code>PrefElection</code>, each of which must implement specific versions of the abstract
 * methods {@link #findWinner()}{@link #isFormal(Vote)} and {@link #clearWinner(int)}. Note that 
 * <code>clearWinner</code> is used only in the context of <code>findWinner</code>, but is nevertheless
 * crucial and specific to the given system</p>
 * 
 * <p>Some private methods here are used to parse particular parameters from the definition 
 * and vote files. These are provided for you as is.</p>
 * 
 * @author hogan
 * 
 */
public abstract class Election {
	/** Election Types */
	public static final int SimpleVoting = 0;
	public static final int PrefVoting = 1;

	/** File Extensions */
	public static final String DefExt = ".elc";
	public static final String VotExt = ".vot";

	/**
	 * Simple boolean method to check whether we have a supported election type. 
	 *
	 * @param num <code>int</code> type to be tested
	 * @return <code>boolean status, true if valid; false otherwise
	 */
	public static boolean isValidType(int num) {
		switch (num) {
		case Election.SimpleVoting:
			return true;
		case Election.PrefVoting:
			return true;
		default:
			return false;
		}
	}

	/** Name of Election */
	protected String name;

	/** Type */
	protected int type;

	/** Collection to contain the votes */
	protected Collection vc;

	protected int numVotes;

	protected int enrolment;

	/** Candidate Information */
	protected TreeMap<CandidateIndex, Candidate> cds;

	protected int numCandidates;

	/** winner for current seat */
	protected Candidate winner;

	protected CandidateIndex winIndex;

	/**
	 * Simple Constructor for the <code>Election</code> superclass.
	 * 
	 * @param name <code>String</code> name of election
	 */
	public Election(String name) {
		this.name = name;
	}

	/**
	 * Perform a count of the votes for the current election according to the rules in 
	 * operation for that election type and construct a string containing a
	 * display summary of the count. 
	 * 
	 * @return String containing summary of the count
	 */
	public abstract String findWinner();

	/**
	 * Simple Getter to return a reference to the candidate collection
	 *  
	 * @return <code>Collection</code> containing cds
	 */
	public java.util.Collection<Candidate> getCandidates() {
		return this.cds.values();
	}

	/**
     * Simple Getter for the name of the election
	 *  
	 * @return <code>String</code> containing name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Simple Getter to return number of candidates for the Election
	 *  
	 * @return <code>int</code> containing number of candidates in this election
	 */
	public int getNumCandidates() {
		return this.numCandidates;
	}

	/**
     * Simple Getter for the election type
     * Values: SimpleVoting = 0; or PrefVoting = 1;
	 *  
	 * @return <code>int</code> containing current election type
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * String version of simple Getter for the election type
     * Values: SimpleVoting = 0; or PrefVoting = 1;
	 *  
	 * @return <code>String</code> containing current election type
	 */
	public String getTypeString() {
		switch (this.type) {
		case Election.SimpleVoting:
			return "Simple Election";
		case Election.PrefVoting:
			return "Preferential Election";
		default:
			return "";
		}
	}

	/**
	 * Simple boolean to determine whether vote is formal according to the 
	 * rules in operation for this election type. 
	 *  
	 * @param v <code>Vote</code> whose formality is to be determined
	 * @return <code>isFormal(v)</code>: true if vote is formal; otherwise false.
	 */
	public abstract boolean isFormal(Vote v);

	/**
	 * Loads electoral definitions from the file specified by the Election name.
	 * This includes name, enrolment, numCandidates and the candidates themselves
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@SuppressWarnings("resource")
	public void loadDefs() throws ElectionException, FileNotFoundException,
			IOException, NumbersException {
		String fname = this.name + Election.DefExt;
		String path = ElectionManager.DataDir + fname;
		BufferedReader br = null; String line;

		// We are loading election definitions line by line.
		// Get buffered reader to begin with
		br = new BufferedReader(new FileReader(path));

		// Each of the definition lines prior to the candidates has
		// a label and value, e.g seatName: Morgul Vale
		// First line - name check
		line = br.readLine();
		if (!("Electorate Summary".equalsIgnoreCase(line.trim()))) {
			throw new ElectionException("Invalid Electorate File");
		}

		// Next grab the election parameters
		this.name = parseStringFromLine(br.readLine(), "seatName");
		this.enrolment = parseIntFromLine(br.readLine(), "enrolment");
		this.numCandidates = parseIntFromLine(br.readLine(),"numCandidates");

		// Now we grab the candidates themselves.
		this.cds = new TreeMap<CandidateIndex, Candidate>();

		// One candidate per line
		for (int count = 1; count <= this.numCandidates; count++) {
			Candidate tempCand = parseCandidateFromLine(br.readLine());

			/* Include in store */
			this.cds.put(new CandidateIndex(count), tempCand);
		}

		// Clean up
		br.close();
	}

	/**
     * Loads votes from the file specified by the Election name.
	 * Formality is checked according to the election type. Formal votes are recorded 
	 * in the election vote collection. Informal votes are discarded but counted. 
	 * 
	 * @throws ElectionException if invalid lines in vote file 
	 * @throws FileNotFoundException if vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails
	 */
	public void loadVotes() throws ElectionException, FileNotFoundException,
			IOException, NumbersException {
		this.vc = new VoteCollection(this.numCandidates);

		String fname = this.name + Election.VotExt;
		String path = ElectionManager.DataDir + fname;
		BufferedReader br = null;
		String line;

		// Lines according to specification
		// Get buffered reader to begin with
		br = new BufferedReader(new FileReader(path));

		// Process votes in turn
		while ((line = br.readLine()) != null) {
			Vote vote = parseVoteFromLine(line, this.numCandidates);
			if ((vote == null) || (!isFormal(vote))) {
				this.vc.updateInformalCount();
			} else {
				this.vc.includeFormalVote(vote);
			}
		}

		// Votes - both informal and not
		this.numVotes = this.vc.getFormalCount() + this.vc.getInformalCount();

		// Clean up
		br.close();
	}

	// Protected and Private/helper methods below///

	/*
	 * Helper method to produce a display list of candidates based on 
	 * the <code>Candidate.candidateListing</code> method.
	 * 
	 * @return <code>String</code> containing candidate list 
	 */
	protected String candidateListing() {
		String str = "";
		java.util.Collection<Candidate> coll = this.cds.values();

		for (Candidate cand : coll) {
			str += cand.candidateListing();
		}
		return str;
	}

    /**
	 * Helper method to produce a display list of candidates for vote 
	 * reporting. This is based on the <code>Candidate.toString</code> method.
	 * 
	 * @return <code>String</code> containing candidate list 
	 */
	protected String candidateVoteSummary() {
		String str = "";
		java.util.Collection<Candidate> coll = this.cds.values();

		for (Candidate cand : coll) {
			str += cand.toString();
		}
		return str;
	}

	/**
	 * Important method to determine if a clear winner exists among the currently active
	 * candidates according to the rules of the current election type. Method is used only 
	 * in the public method {@link #findWinner()} in each subclass of <code>Election</code>
	 * 
	 * @param win <code>int</code> votes required by a winning candidate
	 * @return Winning <code>Candidate</code> if one exists; otherwise null
	 */
	protected abstract Candidate clearWinner(int win);

	/**
	 * Helper method to provide display string about the winning candidate 
	 * 
	 * @param wc Winning <code>Candidate</code>
	 * @return <code>String</code> containing display string for winning candidate
	 */
	protected String reportWinner(Candidate wc) {
		String str = "\nCandidate " + wc.getName();
		String party = wc.getParty();
		if (party.length() > 0) {
			str += " (" + party + ")";
		}
		str += " is the winner with " + wc.getVoteCount() + " votes...\n";
		return str;
	}
	
	/**
	 * Helper method to provide display header string for election 
	 * 
	 * @param wc Winning <code>Candidate</code>
	 * @return <code>String</code> containing seat-specific display string 
	 */
	protected String showResultHeader() {
		String str = "Results for election: " + this.name + "\n"
				+ "Enrolment: " + this.enrolment + "\n\n" 
				+ candidateListing() + "\n\n";
		return str;
	}

	/**
	 * Helper method to parse candidate information from a line in the definitions file 
	 * 
	 * @param line <code>String</code> containing the current line from the file 
	 * @return <code>Candidate</code> containing the information parsed 
	 * @throws <code>ElectionException</code> if <code>isNull(line) OR isInvalid(line) OR isIncomplete(line)</code>
	 */
	private Candidate parseCandidateFromLine(String line) throws ElectionException {
		if (line == null) {
			throw new ElectionException("Null Candidate line");
		} else {
			// trim the parentheses from the line
			int open = line.lastIndexOf('(');
			int close = line.lastIndexOf(')');
			String trimLine = line.substring(open + 1, close);
			String[] tokens = trimLine.split(",");

			if (tokens.length != 3) {
				throw new ElectionException("Invalid Candidate Line");
			}

			boolean validTokens = Strings.checkTokensValid(tokens);

			if (!validTokens) {
				throw new ElectionException("Missing Candidate Values");
			} else {
				return new Candidate(tokens[0], tokens[1], tokens[2], 0);
			}
		}
	}

    /**
	 * <p>Helper method to parse <code>int</code> value from a line according 
	 * to a specific format in the file.</p>
	 * 
	 * <p>Line has the format: <code>label: <int-value></code></p>
	 * 
	 * @param line <code>String</code> containing the current line from the file 
	 * @param label <code>String</code> containing the label
	 * @return <code>int</code> containing the value parsed 
     * @throws NumbersException if parsing error
	 * @throws <code>ElectionException</code> if <code>isNull(line) OR isInvalid(line) OR isIncomplete(line)</code>
	 */
	private int parseIntFromLine(String line,String label) throws ElectionException, NumbersException {
		if (line == null) {
			throw new ElectionException("Null Parameter Line at: " + label);
		} else {
			//Parse line if we can 
			String[] tokens = line.trim().split(":");
			
			if ((tokens.length != 2) || (tokens[0].trim().compareTo(label) !=0)) {
				throw new ElectionException("Invalid Parameter Line at: " + label);
			} 
			return Numbers.parseIntFromToken(tokens[1],"Invalid Value at label: " + label);
		}
	}
	
	/**
	 * <p>Helper method to parse <code>String</code> value from a line according 
	 * to a specific format in the file.</p>
	 * 
	 * <p>Line has the format: <code>label: <string-value></code></p>
	 * 
	 * @param line <code>String</code> containing the current line from the file 
	 * @param label <code>String</code> containing the label
	 * @return <code>String</code> containing the value parsed 
	 * @throws <code>ElectionException</code> if <code>isNull(line) OR isInvalid(line) OR isIncomplete(line)</code>
	 */
	private String parseStringFromLine(String line, String label)
			throws ElectionException {
		if (line == null) {
			throw new ElectionException("Null Election Line at: " + label);
		} else {
			// Parse line if we can
			String[] tokens = line.trim().split(":");

			if ((tokens.length != 2)
					|| (tokens[0].trim().compareTo(label) != 0)) {
				throw new ElectionException("Invalid Election Line at: "
						+ label);
			}

			// Grab value if we can
			if (Strings.nullOrEmpty(tokens[1])) {
				throw new ElectionException("Missing value at: " + label);
			} else {
				return tokens[1].trim();
			}
		}
	}

	
	/**
	 * <p>Helper method to parse full <code>Vote</code>from a line in the votes 
	 * file. The line should include <code>numCandidates<p> integers separated by 
	 * whitespace. 
	 * 
	 * @param line <code>String</code> containing the current line from the file 
	 * @param numCandidates <code>int</code> specifying the number of integers
	 * @return <code>Vote</code> containing the value parsed 
	 * @throws <code>ElectionException</code> if <code>isNull(line) OR isInvalid(line) OR isIncomplete(line)</code>
	 * @throws <code>NumbersException</code> if there is a numeric parsing error 
	 */
	private Vote parseVoteFromLine(String line, int numCandidates) 
			            throws ElectionException, NumbersException {
		Vote vote = null;
		if (line == null) {
			throw new ElectionException("Null Vote line");
		} else {
			String[] tokens = line.trim().split(" ");
			if (tokens.length != numCandidates) {
				throw new ElectionException("Invalid Vote Line");
			}

			boolean validTokens = Strings.checkTokensValid(tokens);
			if (!validTokens) {
				throw new ElectionException("Missing Vote Values");
			} else {
				vote = new VoteList(this.numCandidates);

				for (String token : tokens) {
					int value = Numbers.parseIntFromToken(token,
							"Invalid vote value on line");
					vote.addPref(value);
				}
			}
			return vote;
		}
	}
}