package com.streaming_company;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CaptureSeries extends JPanel
{
    private final JLabel idTitle, nameTitle, ageTitle, episodesTitle, descriptionTitle;             //-Labels that go inside column 1
    private final JTextField idField, nameField, ageField, episodesField;                                     //-Text fields that go inside column 2
    private final JTextArea descriptionField;                                                       //-Text area for description larger than other fields                                                               //-Text area for age restrictions
    private final JButton submitButton;                                                             //-Button to submit the form
    private final JScrollPane descriptionScroll;                                                    //-Scroll pane for the description field

    /**
     * Constructor for a form panel that goes in the main content area.
     * This form swaps the main content area when Add Series button is pressed.
     * Handles all input fields, generates a unique Series ID, and can returns captured data.
     */
    public CaptureSeries() 
    {
        setLayout(new GridBagLayout());                                                             //-Sets layout manager for the panel
        GridBagConstraints addButton = new GridBagConstraints();                                    //-Creates constraints/controls for the grid layout
        addButton.insets = new Insets(5, 5, 5, 5);                                                  //-Sets padding for grid elements of 5 px
        addButton.fill = GridBagConstraints.HORIZONTAL;                                             //-Allows components to stretch horizontally

    //-Add the series model components
        //-Series ID
        idTitle = new JLabel("Series ID:");
        idField = new JTextField(generateSeriesId());                                               //-Calls the static method to generate a unique Series ID
        idField.setEditable(false);
        idField.setBackground(new Color(230, 230, 230));
        addRow(idTitle, idField, addButton, 0, 0.0);                                    //-Calls the helper method to add the Series ID row and applies GridBagConstraints

        //-Series Name
        nameTitle = new JLabel("Series Name:");
        nameField = new JTextField();
        addRow(nameTitle, nameField, addButton, 1, 0.0);                                //-Position row:1 = row 2 on the UI

        //-Series Age Restriction
        ageTitle = new JLabel("Age Restriction:");
        ageField = new JTextField();
        addRow(ageTitle, ageField, addButton, 2, 0.0);                                  //-Adds label (Title) and field

        //-Episodes
        episodesTitle = new JLabel("Episodes:");
        episodesField = new JTextField();
        addRow(episodesTitle, episodesField, addButton, 3, 0.0);

        //-Description
        descriptionTitle = new JLabel("Description:");
        descriptionField = new JTextArea(10, 20);                                                   //-Text area 10 rows, 20 columns
        descriptionScroll = new JScrollPane(descriptionField);                                      //-Creates a scroll pane for the description field
        descriptionField.setLineWrap(true);                                                         //-Enables line wrapping
        descriptionField.setWrapStyleWord(true);                                                    //-Wraps at word boundaries
            //--Left column
        addButton.gridx = 0;                                                                        //-Position: 0 = Column 1
        addButton.gridy = 4;                                                                        //-Position: 4 = Row 5
        addButton.weightx = 0.2;                                                                    //-Gives the component 20% of the horizontal space
        addButton.weighty = 1.0;                                                                    //-Gives the component 100% of the vertical space
        addButton.fill = GridBagConstraints.BOTH;                                                   //-Allows the component to fill the available space
        add(descriptionTitle, addButton);                                                           //-Adds the description title label with controls
            //--Right column
        addButton.gridx = 1;                                                                        //-Position: 1 = Column 2
        addButton.weightx = 0.8;                                                                    //-Gives the component 80% of the horizontal space
        addButton.weighty = 1.0;                                                                    //-Gives the component 100% of the vertical space
        add(descriptionScroll, addButton);                                                          //-Adds the description scroll pane with controls

        //-Submit button
        submitButton = new JButton("Submit");                                                       //-Creates a JButton for submitting the form
        addButton.gridx = 1;                                                                        //-Position: 1 = Column 2
        addButton.gridy = 5;                                                                        //-Position: 5 = Row 6
        addButton.weightx = 0.0;                                                                    //-Restricts button not to expand horizontally
        addButton.weighty = 0.0;                                                                    //-Restricts button not to expand vertically
        addButton.fill = GridBagConstraints.NONE;                                                   //-Keeps original size
        addButton.anchor = GridBagConstraints.LINE_END;                                             //-Aligns to the right of the column
        add(submitButton, addButton);                                                               //-Adds the submit button with controls
    }

    /**
     * Helper method called above to add a row to the form
     * @param label -> JLabel_Column 1 components
     * @param field -> JTextField_Column 2 components
     * @param gbc -> GridBagConstraints
     * @param row -> int
     * @param weightY -> double
     */
    private void addRow(
                        JComponent label, 
                        JComponent field, 
                        GridBagConstraints gbc, 
                        int row, 
                        double weightY
                       ) 
    {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;
        gbc.weighty = weightY;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        add(field, gbc);
    }


    /**
     * Method to generate a unique Series ID
     * @return String representing the unique Series ID
     * Method code was assisted by ChatGPT.
     */
    public static String generateSeriesId() 
    {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";                                      //-Characters to choose from
        StringBuilder id = new StringBuilder("S");                                                  //-Code start with
    
        for (int i = 0; i < 3; i++) {                                                               //-This section generateds 3 random characters
            int rand = (int) (Math.random() * chars.length());
            id.append(chars.charAt(rand));
        }
        return id.toString();                                                                       //-Returns a unique Series ID
    }
    

    /**
     * This method resets all input fields in the form
     * We calling the method in the HomePanel()
     */
    public void resetFields() 
    {
        nameField.setText("");
        ageField.setText("");
        episodesField.setText("");
        descriptionField.setText("");
    }

    /**
     * Setter method to provide a new ID for the next form
     * @param newId
     */
    public void setSeriesId(String newId) 
    {
        idField.setText(newId);
    }

    /**
     * Getter method to retrieve the series data from the form
     * @return String array containing the series data
     */
    public String[] getData() 
    {
        return new String[] 
        {
            idField.getText(),
            nameField.getText(),
            ageField.getText(),
            episodesField.getText(),
            descriptionField.getText()
        };
    }


    /**
     * Getter method to retrieve the submit button
     * Exposes the submit button for external use
     * @return JButton representing the submit button
     */
    public JButton getSubmitButton() { return submitButton; }
}

/**
     * References
     * OpenAI. (2025, September 07). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
     *
     */