/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import asgn1Util.Numbers;
import asgn1Util.NumbersException;
import asgn1Util.Strings;

/**
 * 
 * <p>Class to run a seat based election. The class hierarchy from {@link asgn1Election.Election}
 * provides the definitions and holds the vote collection; this class obtains a list of elections 
 * and provides a convenient wrapper of the counting methods for use in GUI applications.</p>
 * 
 * <p>A command line application is provided in {@link #main(String[])}</p>
 * 
 * @author hogan
 * 
 */
public class ElectionManager {
	/** Width of candidate and count information display */
	public static final int DisplayFieldWidth = 30;
	
	/** Width of the vote count field */
	public static final int VoteField = 5;

	/** Width of the candidate listing display */
	public static final int CandidateFieldWidth = 60;

	/** Width of the field for the candidate name */
	public static final int NameField = 20;

	/** Width of the field for the full party */
	public static final int FullPartyField = 30;

	/** File specifying list of elections */
	public static final String ElectionFile = ".//data//elections.lst";

	/** Data directory specification */
	public static final String DataDir = ".//data//";

	/**
	 * <p>Simple command line application - does not control GUI launch. Reads an election 
	 * list and works through the results one by one</p> 
	 * 
	 * @param args <code>String[]</code> unused
	 */
	public static void main(String args[]) {
		try {
			/* Main Processing Loop */
			ElectionManager em = new ElectionManager();
			em.getElectionsFromFile(ElectionManager.ElectionFile);

			for (Election elec : em.electionList) {
				em.setElection(elec);
				System.out.println(em.manageCount());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	/* ArrayList containing list of elections */
	private ArrayList<Election> electionList;
	
	/* Current Election */
	private Election currElection;

	/**
	 * Simple Constructor for <code>ElectionManager</code>
	 */
	public ElectionManager() {
		this.currElection = null;
		this.electionList = new ArrayList<Election>();
	}

	/**
	 * Simple Getter for the current election
	 * 
	 * @return reference to current <code>Election</code>
	 */
	public Election getElection() {
		return this.currElection;
	}

	/**
	 * Simple Getter for the election list
	 * 
	 * @return reference to <code>ArrayList<Election></code>
	 */
	public ArrayList<Election> getElectionList() {
		return this.electionList;
	}

	/**
	 * Method to load list of elections from file specification
	 * 
	 * @throws FileNotFoundException if <code>srcFile</code> not found 
	 * @throws IOException if problems from <code>BufferedReader</code> 
	 * @throws ElectionException from {@link #parseElectionFromLine(String, int)}
	 * @throws NumbersException from {@link #parseElectionFromLine(String, int)}
	 * 
	 */
	public void getElectionsFromFile(String srcFile) throws FileNotFoundException, IOException,
	                                                       ElectionException, NumbersException {
		FileInputStream fstream = new FileInputStream(srcFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		// Read File Line By Line
		String line = "";
		int count = 0;

		while ((line = br.readLine()) != null) {
			count++;
			this.electionList.add(parseElectionFromLine(line, count));
		}

		// Clean up
		fstream.close();
		br.close();
	}

	/**
	 * Wrapper method to manage count of election, reporting results. 
	 * 
	 * @return <code>String</code> containing report for all electorates. 
	 */
	public String manageCount() {
		// Then we can determine the winner, clean up and return
		String result = this.currElection.findWinner();
		return result;
	}

	/**
	 * <p>Setter for the current election, but one which loads its definitions 
	 * and votes from the files specified by the election name.</p>
	 * 
	 * <p>Throws numerous exceptions propagated up from the <code>Election</code> 
	 * methods</p>
	 * 
	 * @param elec <code>Election</code> to be loaded and set as the current election
	 * @throws ElectionException from {@link asgn1Election.Election#loadDefs()},
	 * {@link asgn1Election.Election#loadVotes()}
	 * @throws IOException from {@link asgn1Election.Election#loadDefs()},
	 * {@link asgn1Election.Election#loadVotes()}
	 * @throws FileNotFoundException from {@link asgn1Election.Election#loadDefs()},
	 * {@link asgn1Election.Election#loadVotes()}
	 * @throws NumbersException from {@link asgn1Election.Election#loadDefs()},
	 * {@link asgn1Election.Election#loadVotes()}
	 */
	public void setElection(Election elec) throws ElectionException,
			FileNotFoundException, IOException, NumbersException {
		this.currElection = elec;
		this.currElection.loadDefs();
		this.currElection.loadVotes();
	}

	/**
	 * <p>Helper method to parse <code>Election</code> specification from a line 
	 * election list file. The line includes the election name and an integer 
	 * indicating its type.</p>
	 * 
	 * @param line <code>String</code> containing the current line from the file 
	 * @param lineNum <code>int</code> count to provide location of errors 
	 * @return <code>Election</code> containing the value parsed 
	 * @throws <code>ElectionException</code> if <code>isNull(line) OR isInvalid(line) OR isIncomplete(line)</code>
	 * @throws <code>NumbersException</code> if there is a numeric parsing error 
	 */
	private Election parseElectionFromLine(String line, int lineNum)
			throws ElectionException, NumbersException {
		String[] tokens = line.trim().split(" ");
		if (tokens.length != 2) {
			throw new ElectionException("Invalid Election Line: " + lineNum);
		}

		String name = tokens[0].trim();
		String msg = "Bad or Missing Election Name at: " + lineNum;
		if (Strings.nullOrEmpty(name)) {
			throw new ElectionException(msg);
		}
		msg = "Bad or missing Election type for: " + name;
		int electType = Numbers.parseIntFromToken(tokens[1], msg);

		switch (electType) {
		case Election.SimpleVoting:
			return new SimpleElection(name);
		case Election.PrefVoting:
			return new PrefElection(name);
		default:
			throw new ElectionException("Invalid election type");
		}
	}

}