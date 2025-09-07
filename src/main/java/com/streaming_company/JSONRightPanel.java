package com.streaming_company;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONRightPanel extends JPanel 
{
    private final JTextArea displayArea;
    private static final String FILE_PATH = "AllSeries.json";                                       //-Path to the JSON file

    /**
     * This constructor creates a read-only panel for displaying JSON series data.
     */
    public JSONRightPanel() 
    {
        setLayout(new BorderLayout());
        displayArea = new JTextArea();
        displayArea.setEditable(false);                                                             //-Makes the text area read-only
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);                                     //-Enables a scrollable view
        setPreferredSize(new java.awt.Dimension(600, 0));

        loadData();                                                                                  //- Calls a method to reload JSON data from the file and updates the display
    }


    /**
     * Displays all series from a JSON array in the right panel.
     * Method code was assisted by Open AI
     */
    private void showAllSeries(JSONArray seriesArray) 
    {
        StringBuilder JSONData = new StringBuilder();
        
        for (int i = 0; i < seriesArray.length(); i++) 
        {
            JSONObject series = seriesArray.getJSONObject(i);                                       //-Get the JSON object for the series
            JSONData.append("Series ID: ").append(series.getString("SeriesID")).append("\n")    //-Appends and builds the JSON data string to display on UI
              .append("Name: ").append(series.getString("Name")).append("\n")
              .append("Age Restriction: ").append(series.getString("AgeRestriction")).append("\n")
              .append("Episodes: ").append(series.getString("Episodes")).append("\n")
              .append("----------------------------------------\n");
        }
        displayArea.setText(JSONData.toString());                                                   //-Sets the text area to display the JSON data
    }


    /**
     * Reloads JSON data from the file and updates the display (Right Panel)
     * Method code was assisted by Open AI
     */
    public void loadData() 
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH))                                //-Opens the JSON file for reading
        {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));                          //-Creates a JSON array from the input stream
            showAllSeries(seriesArray);                                                             //-Calls a method that displays all series in the JSON array
        } catch (Exception e) {                                                                     //-Exception handling
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading JSON data!");
        }
    }


    /**
     * Updates a series in the JSON file after editing and clicking the save button
     * @return true if update succeeded, false otherwise
     */
    public static boolean updateSeries(
                                        String seriesId, 
                                        String name, 
                                        String age, 
                                        String episodes
                                      ) 
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH))                                //-Opens the JSON file for reading
        {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));                          //-Creates a JSON array from the input stream
                boolean found = false;                                                                  //-Flag to track if series is found
        
            //-Loop that iterates through the series array to find a match
            for (int i = 0; i < seriesArray.length(); i++) 
            {
                JSONObject series = seriesArray.getJSONObject(i);                                   //-Gets the JSON object for the series
                if (seriesId.equals(series.getString("SeriesID")))                              //-If the series ID matches, update the series details 
                {
                    series.put("Name", name);
                    series.put("AgeRestriction", age);
                    series.put("Episodes", episodes);
                    found = true;
                    break;
                }
            }

                if (!found)                                                                         //-If no matching series ID was found, show an error message
                {
                    JOptionPane.showMessageDialog(null, "Series ID not found!");
                        return false;
                }

                try (FileWriter writer = new FileWriter(FILE_PATH))                                 //-Opens the JSON file for writing
                { 
                    writer.write(seriesArray.toString(4));                             //-Writes the updated JSON array back to the file with indentation for readability  
                }
                    return true;
        } catch (Exception e) {
                e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating JSON file!");
            return false;                                                                           //-Return false if update failed
        }
    }
    

    /**
     * Deletes a series from the JSON file.
     * @param seriesId
     * @return
     * Delete method code was assisted by Open AI
     */
    public static boolean deleteSeries(String seriesId) 
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH))                                //-Opens the JSON file for reading
        {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));
                boolean found = false;
            
            for (int i = 0; i < seriesArray.length(); i++)                                          //-Loop that iterates through the series array to find a match
            {
                JSONObject series = seriesArray.getJSONObject(i);                                   //-Gets the JSON object for the series

                if (seriesId.equals(series.getString("SeriesID")))                              //-If the series ID matches, delete the series
                {
                    seriesArray.remove(i);                                                          //-Removes the series from the array
                    Files.write(Paths.get(FILE_PATH), 
                                          seriesArray.toString(4).getBytes());         //-Save updated JSON back to the file
                    return true;
                }
            }
        
            JOptionPane.showMessageDialog(null, "Series ID not found!");
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting JSON entry!");
            return false;                                                                               //-Return false if deletion failed
        }
    }
}



/**
 * References
 * OpenAI. (2025, September 02). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */


