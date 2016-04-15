/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import asgn1Election.Election;
import asgn1Election.ElectionManager;


/**
 * Creates a dialog box allowing selection of a particular election.
 *
 * @author CAB302
 */
@SuppressWarnings("serial")
public class ElectionListDialog extends AbstractDialog implements ActionListener, ItemListener {

    private static final int HEIGHT = 200;
    private static final int WIDTH = 350;


    private JComboBox<String> elecListBox;
    private Election selectedElection;

    /**
     * Constructs a modal dialog box that gathers information required for loading a container.
     *
     * @param parent The parent <code>JFrame</code> to which we attach the dialog box
     * @param em The <code>ElectionManager</code> object used to hold election info
     */
    private ElectionListDialog(JFrame parent,ElectionManager em) {
        super(parent,em,"Available Elections", WIDTH, HEIGHT);
        setResizable(false);
        setName("Available Elections");
    }

    /**
     * @see AbstractDialog.createContentPanel()
     */
    @Override
    protected JPanel createContentPanel() {
        // add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        JPanel pnlContent = new JPanel();
        pnlContent.setLayout(new GridBagLayout());
        addToPanel(pnlContent, createCommonControls(), constraints, 0, 0,2,2);
        constraints.weighty = 10;
        return pnlContent;
    }

    /**
     * Helper method to simplify panel creation 
     * 
     * @return <code>JPanel</code> containing controls 
     */
    private JPanel createCommonControls() {
        JPanel pnlCommonControls = new JPanel();
        pnlCommonControls.setLayout(new GridBagLayout());

        // add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridheight=5;
        constraints.gridwidth=4;
        constraints.weightx = 100;
        constraints.weighty = 100;
       
        String[] electionNames = getElectionNames(this.electionManager.getElectionList());
        elecListBox = new JComboBox<String>(electionNames);
        elecListBox.setEditable(false);
        elecListBox.addItemListener(this);  
        elecListBox.setName("Available Elections");
        addToPanel(pnlCommonControls, elecListBox, constraints, 0, 0, 4, 2);

        return pnlCommonControls;
    }

    /**
     * Helper method to extract list of election names for list component
     * 
     * @param electionList <code>ArrayList<Election></code> containing elections 
     * @return <code>String</code> array of election names 
     */
    private String[] getElectionNames(ArrayList<Election> electionList) {
		String[] list = new String[electionList.size()];
		int index=0; 
		for (Election elec : electionList) {
			list[index] = elec.getName();
			index++;
		}
		return list;
	}

    /**
     * @see java.awt.ItemListener.itemStateChanged(ItemEvent e)
     */
    @Override
    public void itemStateChanged(ItemEvent event) {
    	//We do nothing until the confirmation comes 
    }

    /**
     * @see AbstractDialog.dialogDone()
     */
    @Override
    protected boolean dialogDone() {
        try {
        	int index = elecListBox.getSelectedIndex();
        	ArrayList<Election> elecList = this.electionManager.getElectionList();
        	this.electionManager.setElection(elecList.get(index));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().getSimpleName().toString(),
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Shows a <code>LoadContainerDialog</code> for user interaction.
     *
     * @param parent - The parent <code>JFrame</code> which created this dialog box.
     * @param em The <code>ElectionManager</code> object used to hold election info
     * @return <code>Election</code> instance with valid values.
     */
    public static Election showDialog(JFrame parent,ElectionManager em) {
        ElectionListDialog dialog = new ElectionListDialog(parent,em);
        dialog.setVisible(true);
        return dialog.selectedElection; 
    }
}
