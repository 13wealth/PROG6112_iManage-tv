package com.streaming_company;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DeleteSeries extends JPanel 
{

    private final JLabel idLabel;
    private final JTextField idField;
    private final JButton deleteButton;

    private static final String FILE_PATH = "AllSeries.json";                                       //- Path to the JSON file

//-Step 1: Build a panel and button to take input and delete

    public DeleteSeries() 
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idLabel = new JLabel("Enter Series ID:");                                                   //-Creates a label for the series ID input
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2;
        add(idLabel, gbc);

        idField = new JTextField(20);                                                               //-Creates a text field for user input
        gbc.gridx = 1; gbc.weightx = 0.8;
        add(idField, gbc);

        deleteButton = new JButton("Delete Series");                                                //-Creates a delete button
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        add(deleteButton, gbc);
    }

//-Step 2: Create an action listener for the delete button
    /**
     * This method executes a delete from JSON file
     * The method is called and fired in the HomePanel()
     * @param displayPanel
     */
    public void deleteExecution(JSONRightPanel displayPanel) 
    {
        deleteButton.addActionListener(e ->                                                         //-Creates an action listener for the delete button
        {
            String seriesId = idField.getText().trim();                                             //-Gets the input from idField, trims it then assigns it to seriesId
            if (seriesId.isEmpty())                                                                 //-Checks if it is empty
            {
                JOptionPane.showMessageDialog(this, "Please enter a Series ID!");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(                                             //-Confirmation dialog before deleting
                                    null,
                                    "Are you sure you want to delete Series ID: " + seriesId + "?",
                                    "Delete Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE
                );

            if (choice == JOptionPane.YES_OPTION)                                                   //-If choice is YES
            {
                boolean success = deleteSeriesByID(seriesId);                                       //-Call this method that performs the delete in the JSON file
                
                if (success) 
                {
                    JOptionPane.showMessageDialog(this, "Series deleted successfully!");            //-Confirmation of the delete
                    displayPanel.loadData();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Series ID not found!");
                }
            }
        });
    }

//-Step 3: Create a method that executes the delete in the JSON file
    /**
     * This method reads the JSON file and iterates to find a match to delete
     * Once delete is executed successfully, it updates the JSON file.
     * @param seriesId
     * @return
     */
    public static boolean deleteSeriesByID(String seriesId) 
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH))                                //-Opens the JSON file for reading
        {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));                          //-Creates a JSON array from the input stream

            for (int i = 0; i < seriesArray.length(); i++)                                          //-Iterates to find a match
            {
                JSONObject series = seriesArray.getJSONObject(i);                                   //-Gets the JSON object at the current index

                if (seriesId.equals(series.getString("SeriesID")))                              //-If there is a match
                {
                    seriesArray.remove(i);                                                          //-Removes the series from the array

                    try (FileOutputStream output = new FileOutputStream(FILE_PATH))                 //-Opens the JSON file for writing
                    {
                        output.write(seriesArray.toString(4).getBytes());              //-Writes the updated JSON array to the file
                    }
                    return true;
                }
            }
        } catch (Exception e) {                                                                     //-Exception handling
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading/writing JSON file!");
        }
        return false;
    }
}
