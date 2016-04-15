/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1GUI;

import static javax.swing.border.TitledBorder.BOTTOM;
import static javax.swing.border.TitledBorder.CENTER;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import asgn1Election.ElectionException;
import asgn1Election.ElectionManager;


/*
 * Class containing GUI components used to run the <code>VotingWizard</code> display system.  
 * 
 * @author hogan
 */
@SuppressWarnings("serial") // We don't care about binary i/o here
public class VotingComponents extends JPanel implements ActionListener {	
	// Places where we'll add components to a frame
	private enum Position {MIDDLELEFT, TOPCENTRE, MIDDLECENTRE, BOTTOMCENTRE}
	
	//We use the VotingWizard object to pass across required information
	private VotingWizard voteWizard;      
	
	// Election Components (Bottom)
	private JButton countButton;
	private JButton selectButton;
	private JPanel elecPanel;   
	
	// Display for text results (Middle)
	private JTextArea display; 
	private JScrollPane textScrollPane;
	
	// Margin to allow for the main frame
	private final Integer mainMargin = 20; // pixels;
	
	/**
	 * Create a new Window and initialise all of the contained GUI components
	 * @param vw <code>VotingWizard</code> to ensure access
	 * @throws ElectionException if <code>isNull(vw)</code>
	 */
	public VotingComponents(VotingWizard vw) throws ElectionException {
		if (vw==null) {
			throw new ElectionException("VotingWizard object is null");
		}
		//Access to the VW objects 
		this.voteWizard=vw; 
		// Initialize the GUI Components
		initialiseComponents();
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		
		// Get event's source 
		Object source = event.getSource(); 

		//Consider the alternatives 
		if (source == countButton) {
			processElection();
		} else if (source == selectButton) {
			ElectionListDialog.showDialog(this.voteWizard,this.voteWizard.getElecManager());
			this.countButton.setEnabled(true);
			resetDisplay("Press the Count Votes button to see election results");
		}
	}

	/**
	 * Convenience method for adding text to the display area without
	 * overwriting what's already there
	 * @param newText <code>String</code> containing text to be added 
	 */
	private void appendDisplay(String newText) {
		display.setText(display.getText() + newText);
	}
	
	/**
	 * Convenience method to update a <code>JTextField</code> via <code>appendDisplay</code>
	 * @param text <code>String<code> existing content  
	 * @param results <code>String</code> containing new content
	 */
	private void displayResults(String text,String results) {
		if (results!=null) {
			appendDisplay(text+results+"\n");
		} else {
			appendDisplay("Election results not found\n");
		}
	}

	/*
	 * Helper method to initialise all the GUI components [Overkill control here]
	 */
	private void initialiseComponents() {
		// Choose a grid layout for the main frame
		this.setLayout(new GridBagLayout());

		//MIDDLE LEVEL 
		// Create a scrollable text area for displaying instructions and messages
		display = new JTextArea(VotingWizard.TEXT_ROWS,VotingWizard.TEXT_COLS); // lines by columns
		display.setEditable(false);
		display.setLineWrap(true);
		Font font = new Font("Courier New", Font.BOLD,VotingWizard.FONT_SIZE);
        //set font for JTextField
        display.setFont(font);
		textScrollPane = new JScrollPane(display);
		this.add(textScrollPane, positionConstraints(Position.MIDDLECENTRE, mainMargin));
		resetDisplay("Use the selection button to see an election list\n\n");
	
		
		//BOTTOM LEVEL 
		// Panel to contain the query components 
		//Simple flow layout for the bottom panel 
		elecPanel = new JPanel(new FlowLayout());
		elecPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Run Elections",
				CENTER, BOTTOM));
		this.add(elecPanel, positionConstraints(Position.BOTTOMCENTRE, mainMargin));
		elecPanel.setVisible(true);
		
		// Button for processing the query
		countButton = new JButton("Count Votes");
		countButton.addActionListener(this);
		countButton.setEnabled(false);
		elecPanel.add(countButton);
		// Button for getting 
		selectButton = new JButton("Choose Election");
		selectButton.addActionListener(this);
		selectButton.setEnabled(true);
		elecPanel.add(selectButton);
		this.repaint();
	}

	/**
	 * Convenience method for creating a set of positioning constraints for the
	 * specific layout we want for components of our GUI
	 * 
	 * @param location <code>Position> w.r.t <code>GridBagLayout</code> 
	 * @param margin <code>Integer</code> margin size (pixels)
	 * @return <code>GridBagConstraints</code> object consistent with requirements 
	 */
	private GridBagConstraints positionConstraints(Position location, Integer margin) {
		
		GridBagConstraints constraints = new GridBagConstraints();
		switch (location) {
		case TOPCENTRE:
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets = new Insets(margin, margin, 0, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.REMAINDER; // component occupies whole row
			break;
		case MIDDLECENTRE:
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets = new Insets(margin, margin, margin, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.REMAINDER; // component occupies whole row
			constraints.weighty = 100; // give extra vertical space to this object
			break;
		case BOTTOMCENTRE:
			constraints.anchor = GridBagConstraints.SOUTH;
			constraints.insets = new Insets(margin, margin, margin, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.REMAINDER; // component occupies whole row
			constraints.weighty = 100; // give extra vertical space to this object
			break;
		case MIDDLELEFT:
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets = new Insets(0, margin, 0, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.RELATIVE; // component occupies whole row
			constraints.weightx = 100; // give extra horizontal space to this object
			break;
		}
		return constraints;
	}

	/*
	 * Helper method to implement action in response to Count Votes command 
	 */
	private void processElection() {
		resetDisplay("");
		ElectionManager em = this.voteWizard.getElecManager();
		displayResults("",em.manageCount());
		this.countButton.setEnabled(false);
	}

	/**
	 * Convenience method for resetting the text in the display area
	 * 
	 * @param initialText <code>String</code> text to be used 
	 */
	private void resetDisplay(String initialText) {
		display.setText(initialText);
	}
}