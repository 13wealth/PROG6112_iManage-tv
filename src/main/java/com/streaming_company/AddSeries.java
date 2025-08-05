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

    public AddSeries(String loggedInUser) 
    {
        this.loggedInUser = loggedInUser;
    }

    public JPanel getMainFormPanel()                                    //The form below will be created when the user selects "Add Series"
    {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));    //GridLayout(5, 2, 10, 10) = 5 rows, 2 columns, 10px horizontal and 10pxvertical gaps.
            formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
    /*This section is for declaring and creating the main form panel 
    and components like labels, text fields and drop down options*/
        JLabel idLabel = new JLabel("Series ID:");
            JTextArea idField = new JTextArea(generateSeriesId() + 
                                                "\n Captured by: " + loggedInUser +
                                                "\n Date: " + LocalDate.now());
                    idField.setEditable(false);                         //Series ID is auto-generated and not editable
                        idField.setLineWrap(true);
                            idField.setWrapStyleWord(true);

        JLabel nameLabel = new JLabel("Series Name:");
            JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age Restriction:");
            JComboBox<String> ageCombo = new JComboBox<>(new String[]{"All", "13", "16", "18"});

        JLabel episodesLabel = new JLabel("Number of Episodes:");
            JTextField episodesField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
            JTextField descriptionField = new JTextField();

            formPanel.add(idLabel);             formPanel.add(idField);
            formPanel.add(nameLabel);           formPanel.add(nameField);
            formPanel.add(ageLabel);            formPanel.add(ageCombo);
            formPanel.add(episodesLabel);       formPanel.add(episodesField);
            formPanel.add(descriptionLabel);    formPanel.add(descriptionField);

        return formPanel;
    }

    public JPanel getMetadataPanel(String seriesId) 
    {
        JPanel metaPanel = new JPanel(new GridLayout(3, 1));
        metaPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel dateLabel = new JLabel("Date: " + LocalDate.now());
        JLabel userLabel = new JLabel("User: " + loggedInUser);
        JLabel idLabel = new JLabel("Series ID: " + seriesId);

        metaPanel.add(dateLabel);
        metaPanel.add(userLabel);
        metaPanel.add(idLabel);

        return metaPanel;
    }

    private String generateSeriesId() 
    {
        return "SER" + System.currentTimeMillis();
    }
}
