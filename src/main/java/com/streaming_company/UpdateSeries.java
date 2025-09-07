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

public class UpdateSeries extends JPanel
{
    JLabel idLabel;
    JTextField idField;
    JButton loadButton;
    JTextField nameField;
    JTextField ageField;
    JTextField episodesField;
    private final JButton saveButton;
    private static final String FILE_PATH = "AllSeries.json";    

    public UpdateSeries() 
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

    //-Step 3: Add the load series button
        loadButton = new JButton("Load Series");
        properties.gridx = 1;
        properties.gridy = 1;
        properties.weightx = 0;
        properties.fill = GridBagConstraints.NONE;
        properties.anchor = GridBagConstraints.LINE_END;
        add(loadButton, properties);
    
    // Step 3: Name field
        JLabel nameLabel = new JLabel("Series Name:");
        properties.gridx = 0;
        properties.gridy = 2;
        properties.fill = GridBagConstraints.HORIZONTAL;
        add(nameLabel, properties);

        nameField = new JTextField(20);
        properties.gridx = 1;
        properties.gridy = 2;
        add(nameField, properties);

    // Step 4: Age Restriction
        JLabel ageLabel = new JLabel("Age Restriction:");
        properties.gridx = 0;
        properties.gridy = 3;
        add(ageLabel, properties);

        ageField = new JTextField(20);
        properties.gridx = 1;
        properties.gridy = 3;
        add(ageField, properties);

    // Step 5: Episodes
    JLabel episodesLabel = new JLabel("Episodes:");
        properties.gridx = 0;
        properties.gridy = 4;
        add(episodesLabel, properties);

        episodesField = new JTextField(20);
        properties.gridx = 1;
        properties.gridy = 4;
        add(episodesField, properties);

    // Step 6: Save button
        saveButton = new JButton("Save Changes");
        properties.gridx = 1;
        properties.gridy = 5;
        properties.anchor = GridBagConstraints.LINE_END;
        add(saveButton, properties);
    }

   /**
     * Reads all series from the JSON file.
     * @return A 2D array containing all series data.
     */
    public static String[][] readAllSeries() 
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH)) 
        {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));
            String[][] allData = new String[seriesArray.length()][4];

            for (int i = 0; i < seriesArray.length(); i++) {
                JSONObject series = seriesArray.getJSONObject(i);
                allData[i][0] = series.getString("SeriesID");
                allData[i][1] = series.getString("Name");
                allData[i][2] = series.getString("AgeRestriction");
                allData[i][3] = series.getString("Episodes");
            }
            return allData;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading JSON file!");
            e.printStackTrace();
            return new String[0][0];
        }
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
     * Getters for HomePanel to attach update logic
     * @return
     */
    public JButton getUpdateButton() { return loadButton; }
    public String getSeriesId() { return idField.getText().trim(); }
    public JButton getSaveButton() { return saveButton; }

}
