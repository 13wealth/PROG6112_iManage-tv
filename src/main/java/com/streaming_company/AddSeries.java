package com.streaming_company;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddSeries 
{
    private String loggedInUser;

    //--- Instance variables for form fields (so metadata can read them)
    private JTextField nameField;
    private JComboBox<String> ageCombo;
    private JTextField episodesField;
    private JTextField descriptionField;

    public AddSeries(String loggedInUser) 
    {
        this.loggedInUser = loggedInUser;
    }

    /**
     * Builds the main form panel when "Add Series" is clicked.
     * The form fields are assigned to instance variables so their data
     * can be read later for metadata or JSON storage.
     */
    public JPanel getMainFormPanel() 
    {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));                                //-Creates a form 5 rows, 2 columns
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Series ID (auto-generated, non-editable)
        JLabel idLabel = new JLabel("Series ID:");
        JTextArea idField = new JTextArea(generateSeriesId() + 
                                          "\nCaptured by: " + loggedInUser +
                                          "\nDate: " + LocalDate.now());
        idField.setEditable(false);
        idField.setLineWrap(true);
        idField.setWrapStyleWord(true);

        // Series Name
        JLabel nameLabel = new JLabel("Series Name:");
        nameField = new JTextField();  // <-- instance variable

        // Age Restriction
        JLabel ageLabel = new JLabel("Age Restriction:");
        ageCombo = new JComboBox<>(new String[]{"All", "13", "16", "18"}); // <-- instance variable

        // Number of Episodes
        JLabel episodesLabel = new JLabel("Number of Episodes:");
        episodesField = new JTextField(); // <-- instance variable

        // Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField(); // <-- instance variable

        // Add components to form panel
        formPanel.add(idLabel);        formPanel.add(idField);
        formPanel.add(nameLabel);      formPanel.add(nameField);
        formPanel.add(ageLabel);       formPanel.add(ageCombo);
        formPanel.add(episodesLabel);  formPanel.add(episodesField);
        formPanel.add(descriptionLabel); formPanel.add(descriptionField);

        return formPanel;
    }

    /**
     * Builds the metadata panel, reading the values from the form fields.
     * @param seriesId The generated series ID
     * @return JPanel containing metadata about the series
     */
    public JPanel getMetadataPanel(String seriesId) 
    {
        JPanel metaPanel = new JPanel(new GridLayout(6, 1));
        metaPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Always show date, user, and series ID
        JLabel dateLabel = new JLabel("Date: " + LocalDate.now());
        JLabel userLabel = new JLabel("User: " + loggedInUser);
        JLabel idLabel = new JLabel("Series ID: " + seriesId);

        // Read data from instance form fields
        JLabel nameLabel = new JLabel("Series Name: " + 
                                      (nameField.getText().isEmpty() ? "<empty>" : nameField.getText()));
        JLabel ageLabel = new JLabel("Age Restriction: " + ageCombo.getSelectedItem());
        JLabel episodesLabel = new JLabel("Episodes: " + 
                                         (episodesField.getText().isEmpty() ? "<empty>" : episodesField.getText()));
        JLabel descriptionLabel = new JLabel("Description: " + 
                                            (descriptionField.getText().isEmpty() ? "<empty>" : descriptionField.getText()));

        // Add labels to metadata panel
        metaPanel.add(dateLabel);
        metaPanel.add(userLabel);
        metaPanel.add(idLabel);
        metaPanel.add(nameLabel);
        metaPanel.add(ageLabel);
        metaPanel.add(episodesLabel);
        metaPanel.add(descriptionLabel);

        return metaPanel;
    }

    /**
     * Generates a unique Series ID using current time in milliseconds.
     */
    public String generateSeriesId() 
    {
        return "SER" + System.currentTimeMillis();
    }
}
