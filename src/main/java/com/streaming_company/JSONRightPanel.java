package com.streaming_company;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONRightPanel extends JPanel {
    private static final String FILE_PATH = "AllSeries.json";

    private final JTextArea displayArea;

    /**
     * This constructor creates a read-only panel for displaying JSON series data.
     */
    public JSONRightPanel() {
        setLayout(new BorderLayout());
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setPreferredSize(new java.awt.Dimension(600, 0));

        refresh();  // Load JSON on initialization
    }

    /**
     * Displays all series from a JSON array.
     */
    private void showAllSeries(JSONArray seriesArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seriesArray.length(); i++) {
            JSONObject series = seriesArray.getJSONObject(i);
            sb.append("Series ID: ").append(series.getString("SeriesID")).append("\n")
              .append("Name: ").append(series.getString("Name")).append("\n")
              .append("Age Restriction: ").append(series.getString("AgeRestriction")).append("\n")
              .append("Episodes: ").append(series.getString("Episodes")).append("\n")
              .append("-------------------------\n");
        }
        displayArea.setText(sb.toString());
    }

    /**
     * Reloads JSON data from the file and updates the display.
     */
    public void refresh() {
        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));
            showAllSeries(seriesArray);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading JSON data!");
        }
    }

    /**
     * Updates a series in the JSON file.
     * @return true if update succeeded, false otherwise
     */
    public static boolean updateSeries(String seriesId, String name, String age, String episodes) {
        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            JSONArray seriesArray = new JSONArray(new JSONTokener(input));
            boolean found = false;

            for (int i = 0; i < seriesArray.length(); i++) {
                JSONObject series = seriesArray.getJSONObject(i);
                if (seriesId.equals(series.getString("SeriesID"))) {
                    series.put("Name", name);
                    series.put("AgeRestriction", age);
                    series.put("Episodes", episodes);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Series ID not found!");
                return false;
            }

            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write(seriesArray.toString(4));
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating JSON file!");
            return false;
        }
    }
}

/**
 * References
 * OpenAI. (2025, September 02). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */


