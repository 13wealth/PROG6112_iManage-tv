package com.streaming_company;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddSeries 
{
    private String loggedInUser;

    public AddSeries(String loggedInUser) 
    {
        this.loggedInUser = loggedInUser;
    }

    public JPanel getMainFormPanel() 
    {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel idLabel = new JLabel("Series ID:");
        JTextField idField = new JTextField(generateSeriesId());
        idField.setEditable(false);

        JLabel nameLabel = new JLabel("Series Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age Restriction:");
        JComboBox<String> ageCombo = new JComboBox<>(new String[]{"All", "13", "16", "18"});

        JLabel episodesLabel = new JLabel("Number of Episodes:");
        JTextField episodesField = new JTextField();

        formPanel.add(idLabel);      formPanel.add(idField);
        formPanel.add(nameLabel);    formPanel.add(nameField);
        formPanel.add(ageLabel);     formPanel.add(ageCombo);
        formPanel.add(episodesLabel);formPanel.add(episodesField);

        return formPanel;
    }

    public JPanel getMetadataPanel(String seriesId) 
    {
        JPanel metaPanel = new JPanel(new GridLayout(3, 1));
        metaPanel.setBorder(BorderFactory.createTitledBorder("Metadata"));

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
