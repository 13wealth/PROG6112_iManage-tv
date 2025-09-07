package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONArray;
import org.json.JSONObject;

public class RightPanel extends JPanel 
{
    private final JLabel[] metaLabels;                                                         //-Declares an array for metadata labels
    private final JLabel[] dataFields;                                                              //-Declares an array for data fields
    private final JTextArea descriptionField;                                                       //-Declares a text area for the description

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
     * Retrieves the data from the right panel
     * @return
     */
    public String[] getData()
    {
        String[] data = new String[dataFields.length];
        for (int i = 0; i < dataFields.length; i++) 
        {
            data[i] = dataFields[i].getText();                                                      //-Gets text from each data field
        }
        return data;
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
            dataFields[i].setText(capturedData[i]);                                                 //-Sets text for each data field

            if (capturedData.length > dataFields.length)                                            //-Checks if there is additional data for the description field
            {
                descriptionField.setText(capturedData[dataFields.length]);
            }
        }
    }

    
    /**
     * Saves the series data to a JSON file while the program runs
     * Makes use of a temporary memory to store the existing file
     * @param panelData
     */
    public void saveData(String[] panelData) 
    {
        try 
        {
            JSONArray seriesArray = new JSONArray();                                                //-Creates a new JSON array

            try (FileReader reader = new FileReader("series.json"))                                 //-Tries to read the existing JSON file
            {
                char[] buffer = new char[4096];                                                     //-Sets temporary memory size for reading the file
                int length = reader.read(buffer);                                                   //-Reads data into the temporary memory
                    if (length > 0)                                                                 //-Checks if any data was read
                    {
                        String existing = new String(buffer, 0, length);                            //-Creates a string from the temporary memory
                        seriesArray = new JSONArray(existing);                                      //-Parses the string into a JSON array
                    }
            } catch (IOException e) {

            }

        //-Create a new series object to hold the current series data
            JSONObject series = new JSONObject();
            series.put("SeriesID", panelData[0]);
            series.put("Name", panelData[1]);
            series.put("AgeRestriction", panelData[2]);
            series.put("Episodes", panelData[3]);
            ///series.put("Description", panelData[4]); //Has been excluded as it crushed the program due to created outside dataField.length Return 3 for JSON

        //-Parse the series object to the array and save
            seriesArray.put(series);                                                                //-Adds the series object to the array
                try (FileWriter writeData = new FileWriter("AllSeries.json"))                          //-Writes the JSON array to the file
                {
                    writeData.write(seriesArray.toString(4));                          //-Formats the JSON output with an indentation of 4 spaces
                }

        } catch (java.io.IOException | org.json.JSONException e) {                                  //-Multi-catch for specific exceptions
            e.printStackTrace();                                                                    //-Prints the trace LOG trace debugging
            JOptionPane.showMessageDialog(null, "Error saving series data!");
        }
    }
}
