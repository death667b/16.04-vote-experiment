/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import asgn1Election.ElectionManager;

/**
 * Provides base class for dialog boxes used for data selection
 *
 * @author CAB302
 */
@SuppressWarnings("serial")
public abstract class AbstractDialog extends JDialog implements ActionListener {

    private JButton btnOK;
    private JButton btnCancel;
    protected ElectionManager electionManager;

    /**
     * This constructor calls the JDialog parent constructor to create a modal dialog
     * box with the specified parent and title. The dialog box is set to the specified
     * width and height. Before the constructor returns it calls the
     * createContentPanel method. No GUI creation code should be placed in
     * child constructors calling this constructor - use the over-rides
     *
     * @param parent The parent <code>JFrame</code> to which we attach the dialog box
     * @param em The <code>ElectionManager</code> object used to hold election info
     * @param title <code>String</code> title of the dialog box
     * @param width <code>int</code> dialog box width
     * @param height <code>int</code> dialog box height
     */
    protected AbstractDialog(Frame parent,ElectionManager em,String title, int width, int height) {
        super(parent, title, true);
        this.electionManager=em;
        JPanel pnlDialogControls = createDialogControls();
        JPanel pnlContent = createContentPanel();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnlDialogControls, BorderLayout.SOUTH);
        getContentPane().add(pnlContent, BorderLayout.CENTER);
        setSize(width, height);
    }

    /**
     * This abstract method is called by the AbstractDialog constructor to
     * build the JPanel that will be put in the centre of the dialog box.
     * Override and implement to create the contents of your dialog box.
     *
     * @return The <code>JPanel</code> holding dialog box content
     */
    protected abstract JPanel createContentPanel();

    /**
     * Lays out OK and Cancel buttons used by all dialogs.
     *
     * @return <code>JPanel</code> with OK and Cancel buttons.
     */
    private JPanel createDialogControls() {
        JPanel pnlDialogControls = new JPanel();
        pnlDialogControls.setLayout(new FlowLayout(FlowLayout.CENTER));

        btnOK = createButton("OK");
        btnCancel = createButton("Cancel");

        pnlDialogControls.add(btnOK);
        pnlDialogControls.add(btnCancel);
        return pnlDialogControls;
    }

    /**
     * Factory method which creates a <code>JButton</code> instance with the given text used for the
     * button text and the component name.
     *
     * @param text <code>String</code> to use on the JButton and as the component name.
     * @return <code>JButton</code> instance.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setName(text);
        button.addActionListener(this);
        return button;
    }

    /**
     * Helper function from the pracs to help with adding items
     * to panels with GridBagLayouts
     *
     * @param panel <code>JPanel</code> to add the component to
     * @param component The <code>Component</code> to add
     * @param constraints The default <code>GridBagConstraints</code>
     * @param x <code>int</code> row the component is to be placed in
     * @param y <code>int</code> column the component is to be placed in
     * @param width <code>int</code> columns to span
     * @param height <code>int</code> rows to span
     */
    protected void addToPanel(JPanel panel, Component component, GridBagConstraints constraints,
            int x, int y, int width, int height) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        panel.add(component, constraints);
    }

    /**
     * Handles the closing of the dialog box. This implementation
     * calls the dialogDone() method to determine if the dialogBox should close
     *
     * @param event <code>ActionEvent</code> to handle.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnCancel) {
            setVisible(false);
            return;
        }

        if (dialogDone()) {
            setVisible(false);
        }
    }

    /**
     * Called by the actionPerformed method when the OK button has been clicked to
     * determine if the dialog box should close. In this method you should process
     * the input, set the output field to its final value. If there is a problem
     * and you need the user to modify their data, return false to keep the dialog
     * open.
     *
     * @return true if the dialog box should close, false otherwise
     */
    protected abstract boolean dialogDone();

    /**
     * Called to cause the JDialog to completely redraw itself
     */
    protected void redraw() {
        invalidate();
        validate();
        repaint();
    }

    /**
     * Disables the OK button
     */
    protected void disableSubmit() {
        btnOK.setEnabled(false);
    }

    /**
     * Enables the OK button
     */
    protected void enableSubmit() {
        btnOK.setEnabled(true);
    }
}
