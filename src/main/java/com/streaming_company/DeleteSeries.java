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

public class DeleteSeries extends JPanel {

    private final JLabel idLabel;
    private final JTextField idField;
    private final JButton deleteButton;

    private static final String FILE_PATH = "AllSeries.json";

    public DeleteSeries() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Series ID ---
        idLabel = new JLabel("Enter Series ID:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2;
        add(idLabel, gbc);

        idField = new JTextField(20);
        gbc.gridx = 1; gbc.weightx = 0.8;
        add(idField, gbc);

        // --- Delete Button ---
        deleteButton = new JButton("Delete Series");
        gbc.gridx = 1; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        add(deleteButton, gbc);
    }

    // --- Hook delete logic to right panel ---
    public void setupDeleteAction(JSONRightPanel displayPanel) {
        deleteButton.addActionListener(e -> {
            String seriesId = idField.getText().trim();
            if (seriesId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Series ID!");
                return;
            }

            boolean success = deleteSeriesByID(seriesId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Series deleted successfully!");
                displayPanel.loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Series ID not found!");
            }
        });
    }

    // --- Static delete method ---
    public static boolean deleteSeriesByID(String seriesId) {
        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));

            for (int i = 0; i < seriesArray.length(); i++) {
                JSONObject series = seriesArray.getJSONObject(i);
                if (seriesId.equals(series.getString("SeriesID"))) {
                    seriesArray.remove(i);

                    try (FileOutputStream output = new FileOutputStream(FILE_PATH)) {
                        output.write(seriesArray.toString(4).getBytes());
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading/writing JSON file!");
        }
        return false;
    }
}
