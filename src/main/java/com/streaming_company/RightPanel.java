package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RightPanel extends JPanel 
{
    private final JLabel[] metaLabels;                                                                    //-Declares an array for metadata labels
    private final JLabel[] dataFields;                                                                    //-Declares an array for data fields
    private final JTextArea descriptionField;                                                             //-Declares a text area for the description

    public RightPanel() 
    {
        setLayout(new BorderLayout(10, 10));                                                        //-Sets layout manager with  horizontal and vertical gaps
        setBorder(BorderFactory.createCompoundBorder(                                               //-Creates a compound border
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        String[] titles = { 
                            "Series ID:", 
                            "Series Name:", 
                            "Series Age:", 
                            "Episodes:" 
                        };
        metaLabels = new JLabel[titles.length];                                                     //-Creates an array object to hold metadata labels
        dataFields = new JLabel[titles.length];                                                     //-Creates an array object to hold data fields

        /* new JPanel() → Creates a new panel container for rows
         * new GridLayout(titles.length, 1, 5, 5) → Is nested within the rowsPanel
         * Each row becomes a new JPanel with a BorderLayout (can handle multiple components)
         * This tells the layout manager to apply these settings:
         *   - titles.length: Number of rows (one for each title)
         *   - 1: Number of columns (single column layout)
         *   - 5, 5: Horizontal and vertical gaps between components
         */
        JPanel rowsPanel = new JPanel(new GridLayout(titles.length, 1, 5, 5));                      

        for (String title : titles)                                                                 //-Iterates over each title
        {
            JPanel row = new JPanel(new BorderLayout(5, 5));                                        //-Creates a new row panel with a BorderLayout

            JLabel label = new JLabel(title);                                                       //-Creates a new label with the title text (Col 1)
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(220, 220, 220)    //- Creates a border with specified color
            ));

            label.setPreferredSize(new Dimension(100, 20));                                         //-Forces left column width to be ~30%

            JLabel data = new JLabel("");                                                           //-Creates a new label for data (Col 2)
            data.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)
            ));

            row.add(label, BorderLayout.WEST);                                                      //-Left = ~30%
            row.add(data, BorderLayout.CENTER);                                                     //-Right = remaining ~70%

            rowsPanel.add(row);                                                                     //-Adds the row panel to the rowsPanel

            /*
             * Keeps references to the labels and data fields you add to the panel, 
             *   so you can later update them.
             */
            int i = rowsPanel.getComponentCount() - 1;
            metaLabels[i] = label;                                                                  //-Saves the given label (a JLabel) into the metaLabels array at index i
            dataFields[i] = data;                                                                   //-Saves the given data into the dataFields array at index i.
        }

            add(rowsPanel, BorderLayout.NORTH);                                                     //-Adds the rowsPanel to the top of the RightPanel

    //-Description section
        JPanel descriptionPanel = new JPanel(new BorderLayout(5, 5)); 
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(220, 220, 220)
        ));
        descriptionLabel.setPreferredSize(new Dimension(100, 20));                                  //-Match the same left column width as defined above
        descriptionPanel.add(descriptionLabel, BorderLayout.WEST);                                  //-Adds the descriptionLabel to the left side of the descriptionPanel

        descriptionField = new JTextArea(5, 20);                                                    //-JTextArea for the description with 5 rows and 20 columns
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(descriptionField);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        descriptionPanel.add(scroll, BorderLayout.CENTER);

        add(descriptionPanel, BorderLayout.CENTER);
    }

    /**
     * Sets the data that was retrieved by getData() for the right panel
     * Keeps all metadata fields in sync with the form data
     * @param capturedData
     */
    public void setData(String[] capturedData) 
    {
        for (int i = 0; i < capturedData.length && i < dataFields.length; i++) 
        {
            dataFields[i].setText(capturedData[i]);
        
            if (capturedData.length > dataFields.length) 
            {
                descriptionField.setText(capturedData[dataFields.length]);
            }
        }
    }
}
