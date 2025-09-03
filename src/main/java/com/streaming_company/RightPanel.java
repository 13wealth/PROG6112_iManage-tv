package com.streaming_company;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory; 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RightPanel extends JPanel 
{                                                          
    private JLabel [] metaLabels;                                                                   //-Column 1 of 2
    private JLabel [] dataFields;                                                                   //-Column 2 of 2
    private JTextArea descriptionField;                                                             //-Declared separately because the field will be set up differently
    private JScrollPane descriptionScroll;                                                          /*The description field will have a scroll pane 
                                                                                                      due to its potentially large content*/
    /**
     * Constructor for RightPanel
     * Initialises, creates and adds the first 4 fields of the right pane using a loop.
     * Sets up the description field separately with a scroll pane.
     */
    public RightPanel() 
    {                  
 
    //-Step 1: Draws or sets up the right panel and applies basic styling    
        setLayout(new GridBagLayout());                                                             //-Lets you freely set the h and w of the components
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),                                    //-Border thin outer line
            BorderFactory.createEmptyBorder(5, 5, 5, 5)                                             //-Padding inside
        ));

    //-Step 2: Sets up the layout limitations for the panel
        GridBagConstraints paneSetup = new GridBagConstraints();                                    /*Instantiates the GridBagConstraints object and creates
                                                                                                      the layout for the panel*/
                           paneSetup.insets = new Insets(5, 5, 5, 5);                               //-Applies padding for each component
                           paneSetup.fill = GridBagConstraints.HORIZONTAL;                          //-Allows components to stretch horizontally
                           paneSetup.weightx = 1.0;                                                 //-Applies flexible space allocation

    //-Step 3: Adds for 4 components to the panel
        String [] titles = {                                                                        //-Titles that go inside column 1, used for the loop length/iteration
            "Series ID:",
            "Series Name:",
            "Series Age:",
            "Episodes:"
        };

        metaLabels = new JLabel[titles.length];                                                     //-Object that holds the titles array in column 1
        dataFields = new JLabel[titles.length];                                                     //-Object that holds the data fields in column 2

        //-Loop applies to the first 4 rows for even
        for (int i = 0; i < titles.length; i++)                                                     //-Loops through the titles array to create meta labels and data fields
        {
            paneSetup.gridx = 0;                                                                    //-Position: 0 = column 1
            paneSetup.gridy = i;                                                                    //-Position= row i in the grid
            paneSetup.weightx = 0.1;                                                                //-If there’s horizontal free space, give this component 0.3 share compared to others.
            metaLabels[i] = new JLabel(titles[i]);                                                  //-Creates a new label for each title in column 1
            metaLabels[i].setBorder(BorderFactory.createMatteBorder(
                                                            0, 0, 1, 1, new Color(220, 220, 220)    //-Borders the created field
        ));                                                                                         
            add(metaLabels[i], paneSetup);                                                          //-Writes the titles in the fields created

            paneSetup.gridx = 1;                                                                    //-Position: 1 = column 2
            paneSetup.weightx = 0.9;                                                                //-Gridx: 1 takes 0.9 of the free space (0.1 goes to gridx:0)
            dataFields[i] = new JLabel("");                                                         //-Creates a new empty label for each data field in column 2
            dataFields[i].setBorder(BorderFactory.createMatteBorder(
                                                        0, 0, 1, 0, new Color(220, 220, 220)
        ));                                                                                         
            add(dataFields[i], paneSetup);                                                           
        }
    
    //-Step 4: Adds the Description component to the panel
        //-Description label
            paneSetup.gridx = 0;                                                                    //-Component goes in the first column
            paneSetup.gridy = titles.length;                                                        //-After iterating through the titles, this component goes in the next row
            
            paneSetup.weighty = 1.0;                                                                //-Component takes all available space in the y direction
            paneSetup.anchor = GridBagConstraints.NORTH;                                            //-Aligns the component to the top
            JLabel descriptionLabel = new JLabel("Description:");
            descriptionLabel.setBorder(BorderFactory.createMatteBorder(
                                                        0, 0, 1, 1, new Color(220, 220, 220)
        ));
            add(descriptionLabel, paneSetup);

        //-Description field properties
            paneSetup.gridx = 1;                                                                    //-Position: 1 = column 2
            paneSetup.weightx = 0.7;                                                                //-Takes 70% of the horizontal available space
            paneSetup.weighty = 1.0;                                                                //-Takes all vertical available space
            paneSetup.fill = GridBagConstraints.BOTH;                                               //-Allows the component to fill the available space
            descriptionField = new JTextArea(5, 20);                                                //-Text area: 5 rows, 20 columns
            descriptionField.setLineWrap(true);                                                     //-Enables line wrapping
            descriptionField.setWrapStyleWord(true);                                                //-Wraps at word boundaries
            descriptionField.setBorder(BorderFactory.createMatteBorder(
                    0, 0, 1, 0, new Color(220, 220, 220)
        ));
        
        //-Description field scroll pane
            descriptionScroll = new JScrollPane(descriptionField);
            descriptionScroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
            add(descriptionScroll, paneSetup);
    }
    

    /**
     * Method populate data in the second column when submit is pressed
     * @param capturedData
     */
    public void setData(String[] capturedData) 
    {
        for (int i = 0; i < capturedData.length && i < dataFields.length; i++) 
        {
            dataFields[i].setText(capturedData[i]);
        }
    }
}


    /* Getters so HomePanel (or future logic) can update labels
    public JLabel getSeriesID() { return id; }
    public JLabel getSeriesName() { return name; }
    public JLabel getSeriesAge() { return age; }
} */
