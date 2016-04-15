/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1GUI;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import asgn1Election.Election;
import asgn1Election.ElectionException;
import asgn1Election.ElectionManager;
import asgn1Util.NumbersException;

/*
 * This class contains the <code>VotingWizard</code> main program, including 
 * the option of a GUI (here the default). Choose GUI or CLI via the command 
 * line arguments
 * 
 * @author hogan
 */
@SuppressWarnings("serial")
public class VotingWizard extends JFrame implements Runnable {
	ElectionManager elecManager; 
	 
	//GUI Display Parameters 
	public static final boolean USE_GUI = true; 
	public static final int TEXT_ROWS = 30; 
	public static final int TEXT_COLS = 80; 
	public static final int FONT_SIZE = 12;
	
	/**
	 * 
	 * @param em <code>ElectionManager</code> to run the process
	 * @throws ElectionException if <code>isNull(em)</code> 
	 */
	public VotingWizard(ElectionManager em) throws ElectionException {
		if (em==null) {
			throw new ElectionException("Null ElectionManager");
		}
		this.elecManager=em; 
	}
	
	/**
	 * Simple Getter for ElectionManager 
	 * 
	 * @return the elecManager
	 */
	public ElectionManager getElecManager() {
		return elecManager;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	 	// Terminate the program if the user closes the main frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Voting Wizard");
		// Add components to the frame
		try {
			this.getContentPane().add(new VotingComponents(this));
		} catch (ElectionException e) {
			//catch can never be reached here; 
			//Exception only if *this* is null 
			e.printStackTrace();
		}
		// Resize the main frame to fit its components
		this.pack();
        // Make the simulation visible
        this.setVisible(true);
	}

	/*
	 * Program entry point. Selects GUI or CLI from command line arg 
	 * options. 
	 * 
	 */
	public static void main(String[] args) {  	
		boolean voteGUI=processArguments(args); 
		ElectionManager em = null;
		try {
			/* Main Processing Loop */
			em = new ElectionManager();
			em.getElectionsFromFile(ElectionManager.ElectionFile);
		
			//GUI or not GUI 
			if (voteGUI) {
				SwingUtilities.invokeLater(new VotingWizard(em));
			} else {
				for (Election elec : em.getElectionList()) {
					em.setElection(elec);
					System.out.println(em.manageCount());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (ElectionException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (NumbersException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Helper method to process command line args
	 * Zero or one argument(s) are permissible; if one, we must select <code>gui</code>
	 * or <code>cli</code>; if zero, we default to <code>VotingWizard.USE_GUI</code>. 
	 * Otherwise, if invalid combination, we exit 
	 * 
	 * @param args <code>String[]</code> command line args from <code>main</code>
	 * @return not reached if <code>!isValid(args)</code>. Otherwise if <code>isValid(args)</code>, 
	 * return <code>true</code> if <code>useGui<code>; otherwise <code>false</code>  
	 */
	private static boolean processArguments(String[] args) {
		switch (args.length) {
			case 0: 
				return VotingWizard.USE_GUI;
			case 1: {
				String option = getValidOption(args);
				if (option==null) {
					usage();
					System.exit(-1);
				} else {
					return option.equalsIgnoreCase("gui");
				}
			}
			default: {
				usage(); 
				System.exit(-1);
				//for the compiler
			}
		}
		//for the compiler
		return false; 
	}
		
	/**
	 * Helper method to detect valid argument for the main program
	 * [Currently a max of one required] 
	 * 
	 * @param args <code>String[]</code> command line args from <code>main</code>
	 * @return <code>String</code> containing <code>"gui" OR "cli"<code>
	 */
	private static String getValidOption(String[] args) {
		String option=args[0].trim();
		boolean valid=(option.equalsIgnoreCase("gui")||option.equalsIgnoreCase("cli"));
		if (valid) {
			return option;
		} else {
			return null;
		}
	}

	/**
	 * Helper method to provide usage message to be invoked on argument error
	 */
	private static void usage() {
		System.err.println("Usage: VotingWizard [gui|cli]\n");
		System.err.println("       Not case sensitive; defaults to setting in VotingWizard.java\n\n");
	}
}

