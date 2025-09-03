package com.streaming_company;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddSeries extends JPanel
{
    private JLabel idTitle, nameTitle, ageTitle, episodesTitle, descriptionTitle;                   //-Labels that go inside column 1
    private JTextField idField, nameField, episodesField;                                           //-Text fields that go inside column 2
    private JTextArea descriptionField;                                                             //-Text area for description larger than other fields
    private JComboBox<String> ageField;                                                             //-Combo box for selecting age restrictions
    private JButton submitButton;                                                                   //-Button to submit the form

    /**
     * Constructor for a form panel that goes in the main content area
     * Handles all input fields, generates a unique Series ID, and can returns captured data.
     * 
     */
    public AddSeries() 
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Helper method for uniform row addition
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Series ID
        idTitle = new JLabel("Series ID:");
        idField = new JTextField(generateSeriesId());
        idField.setEditable(false);
        idField.setBackground(new Color(230, 230, 230));
        addRow(idTitle, idField, gbc, 0, 0.0);

        // Series Name
        nameTitle = new JLabel("Series Name:");
        nameField = new JTextField();
        addRow(nameTitle, nameField, gbc, 1, 0.0);

        // Series Age
        ageTitle = new JLabel("Series Age:");
        String[] ageBands = {"All Ages", "10", "12", "16", "18"};
        ageField = new JComboBox<>(ageBands);
        addRow(ageTitle, ageField, gbc, 2, 0.0);

        // Episodes
        episodesTitle = new JLabel("Episodes:");
        episodesField = new JTextField();
        addRow(episodesTitle, episodesField, gbc, 3, 0.0);

        // Description (expandable)
        descriptionTitle = new JLabel("Description:");
        descriptionField = new JTextArea(10, 20); // more rows
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionField);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;             // let this row expand vertically
        gbc.fill = GridBagConstraints.BOTH;
        add(descriptionTitle, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Submit button (small, original size)
        submitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;             // do not expand vertically
        gbc.fill = GridBagConstraints.NONE; // keep original size
        gbc.anchor = GridBagConstraints.LINE_END; // align right
        add(submitButton, gbc);
    }

    private void addRow(JComponent label, JComponent field, GridBagConstraints gbc, int row, double weightY) 
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

    public String generateSeriesId() 
    {
        return "SER" + System.currentTimeMillis();
    }

    public String[] getSeriesData() 
    {
        return new String[] 
        {
            idField.getText(),
            nameField.getText(),
            (String) ageField.getSelectedItem(),
            episodesField.getText(),
            descriptionField.getText()
        };
    }

    public JButton getSubmitButton() { return submitButton; }
}

