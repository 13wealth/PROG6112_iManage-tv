package com.streaming_company;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;                                                                     //-Improt for reading the JSON file
import javax.swing.JLabel;                                                                         //-Improt for reading the JSON file
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SearchSeries extends JPanel
{
    private final JLabel idLabel;
    private final JTextField idField;
    private final JButton searchButton;
    private static final String FILE_PATH = "AllSeries.json";                                       /*FILE_PATH is a CONSTANT storing the path to your JSON file. 
                                                                                                      Using a constant avoids hardcoding the path in multiple places*/
    public SearchSeries()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints properties = new GridBagConstraints();
        properties.insets = new Insets(5,5,5,5);
        properties.fill = GridBagConstraints.HORIZONTAL;

    //-Step 1: Add the label
        idLabel = new JLabel("Enter Series ID:");
        properties.gridx = 0;
        properties.gridy = 0;
        properties.weightx = 0.2;
        add(idLabel, properties);

    //-Step 2: Add the text field
        idField = new JTextField(20);
        properties.gridx = 1;
        properties.weightx = 0.8;
        add(idField, properties);

    //-Step 3: Add the search button
        searchButton = new JButton("Search");
        properties.gridx = 1;
        properties.gridy = 1;
        properties.weightx = 0;
        properties.fill = GridBagConstraints.NONE;
        properties.anchor = GridBagConstraints.LINE_END;
        add(searchButton, properties);
    }
    

    /**
     * Reads and searches the JSON filefor a series by its ID.
     * @param Id The ID of the series to search for.
     * @return True if the series is found, false otherwise.
     * Reading the JSON file logic was assisted by ChatGPT.
     */
    public static String[] searchByID(String seriesId)
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH))                                //-Open the JSON file and close it automatically when done
        {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));                          //-JSONArray parse the JSON file into a JSONArray
        //-Loops through each object in the array to find a match                                      JSONTokener converts a file contents into a JSON objects/stream
            for (int i = 0; i < seriesArray.length(); i++)
            {
                JSONObject series = seriesArray.getJSONObject(i);
                if (seriesId.equals(series.getString("SeriesID")))
                {
                    return new String[]                                                             //-If found  it returns an String array with these keys
                    {
                        series.getString("SeriesID"),
                        series.getString("Name"),
                        series.getString("AgeRestriction"),
                        series.getString("Episodes"),
                    };
                }
            }

        //-If no match is found
            JOptionPane.showMessageDialog(null, "Series ID not found!");
            return null;

            } catch (IOException | org.json.JSONException e) {
                JOptionPane.showMessageDialog(null, "Error reading JSON file!");
                e.printStackTrace();
                return null;
        }
    }

     
    /**
     * Getters for HomePanel to attach input and search logic
     * @return
     */
    public JButton getSearchButton() { return searchButton; }
    public String getSeriesId() { return idField.getText().trim(); }
}



/**
 * References
 * OpenAI. (2025, September 07). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */