package com.streaming_company;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONRightPanel extends JPanel {

    private JLabel displayLabel;

    public JSONRightPanel() {
        setLayout(new BorderLayout());
        displayLabel = new JLabel();
        displayLabel.setVerticalAlignment(SwingConstants.TOP);
        add(new JScrollPane(displayLabel), BorderLayout.CENTER);
        setPreferredSize(new java.awt.Dimension(400, 0));
    }

    /**
     * Display all series from the JSON file in a read-only manner.
     */
    public void showAllSeries(JSONArray seriesArray) {
        StringBuilder allSeriesText = new StringBuilder("<html><pre>");
        for (int i = 0; i < seriesArray.length(); i++) {
            JSONObject series = seriesArray.getJSONObject(i);
            allSeriesText.append("Series ID: ").append(series.getString("SeriesID")).append("\n")
                         .append("Name: ").append(series.getString("Name")).append("\n")
                         .append("Age Restriction: ").append(series.getString("AgeRestriction")).append("\n")
                         .append("Episodes: ").append(series.getString("Episodes")).append("\n")
                         .append("-------------------------\n");
        }
        allSeriesText.append("</pre></html>");
        displayLabel.setText(allSeriesText.toString());
        revalidate();
        repaint();
    }

    
}


