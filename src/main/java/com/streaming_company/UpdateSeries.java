package com.streaming_company;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UpdateSeries extends JPanel 
{
    private final JLabel idLabel;
    private final JTextField idField;
    private final JButton loadButton;
    private final JTextField nameField;
    private final JTextField ageField;
    private final JTextField episodesField;
    private final JButton saveButton;

    private static final String FILE_PATH = "AllSeries.json";

    public UpdateSeries() 
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

    //-Step 1: Create and add components to panel
        //--- Series ID ---
        idLabel = new JLabel("Enter Series ID:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2;
        add(idLabel, gbc);

        idField = new JTextField(20);
        gbc.gridx = 1; gbc.weightx = 0.8;
        add(idField, gbc);

        loadButton = new JButton("Load Series");
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.LINE_END;
        add(loadButton, gbc);

        //--- Name ---
        JLabel nameLabel = new JLabel("Series Name:");
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameLabel, gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        add(nameField, gbc);

        //--- Age Restriction ---
        JLabel ageLabel = new JLabel("Age Restriction:");
        gbc.gridx = 0; gbc.gridy = 3;
        add(ageLabel, gbc);

        ageField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        add(ageField, gbc);

        //--- Episodes ---
        JLabel episodesLabel = new JLabel("Episodes:");
        gbc.gridx = 0; gbc.gridy = 4;
        add(episodesLabel, gbc);

        episodesField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4;
        add(episodesField, gbc);

        //--- Save Button ---
        saveButton = new JButton("Save Changes");
        gbc.gridx = 1; gbc.gridy = 5; gbc.anchor = GridBagConstraints.LINE_END;
        add(saveButton, gbc);
    }
    

    /**
     * Step 2: Sets an action listener for a button to load series data to be updated
     */
    public void setupLoadAction(JSONRightPanel displayPanel) 
    {
        loadButton.addActionListener(e -> 
        {
            String seriesId = getSeriesId();                                                        //-Gets the input Series ID
            String[] data = searchByID(seriesId);                                                   //-Calls the method that searches JSON file and assigns the result to data

            if (data != null)                                                                       //-If series is found
            {
                nameField.setText(data[1]);                                                         //-Populate the name field
                ageField.setText(data[2]);                                                          //-Populate the age field
                episodesField.setText(data[3]);                                                     //-Populate the episodes field
            }
        });
    }


    /**
     * Step 3: Sets up the Save button to update JSON and refresh the display panel.
     */
    public void setupSaveAction(JSONRightPanel displayPanel) 
    {
        saveButton.addActionListener(e -> 
        {
            String seriesId = getSeriesId();
            String name = nameField.getText();
            String age = ageField.getText();
            String episodes = episodesField.getText();

            boolean success = JSONRightPanel.updateSeries(seriesId, name, age, episodes);
            if (success) {
                JOptionPane.showMessageDialog(null, "Series updated successfully!");
                displayPanel.loadData();  // Refresh the read-only display
            }
        });
    }


    /**
     * Static method to search for a series by ID
     */
    public static String[] searchByID(String seriesId) 
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH)) 
        {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));
            for (int i = 0; i < seriesArray.length(); i++) 
            {
                JSONObject series = seriesArray.getJSONObject(i);
                if (seriesId.equals(series.getString("SeriesID"))) 
                {
                    return new String[]
                    {
                        series.getString("SeriesID"),
                        series.getString("Name"),
                        series.getString("AgeRestriction"),
                        series.getString("Episodes")
                    };
                }
            }
            JOptionPane.showMessageDialog(null, "Series ID not found!");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading JSON file!");
            return null;
        }
    }


    /**
     * Getter to expose buttons and methods for the Home Panel
     */
    public String getSeriesId() { return idField.getText().trim(); }
    public JButton getUpdateButton() { return loadButton; }
    public JButton getSaveButton() { return saveButton; }
}
