package com.streaming_company;

import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SeriesReport 
{
     private static final String FILE_PATH = "AllSeries.json";

    /**
     * Reads all series from the JSON file and returns them as a JSONArray.
     */
    public static JSONArray readAllSeries() 
    {
        try (FileInputStream input = new FileInputStream(FILE_PATH)) 
        {
            return new JSONArray(new JSONTokener(input));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading JSON file!");
            return new JSONArray(); 
        }
    }

    /**
     * Helper method to print all series in a readable format
     */
    public static void printAllSeries() {
        JSONArray seriesArray = readAllSeries();
        for (int i = 0; i < seriesArray.length(); i++) 
        {
            JSONObject series = seriesArray.getJSONObject(i);
            System.out.println("Series ID: " + series.getString("SeriesID"));
            System.out.println("Name: " + series.getString("Name"));
            System.out.println("Age Restriction: " + series.getString("AgeRestriction"));
            System.out.println("Episodes: " + series.getString("Episodes"));
            System.out.println("------------------------------------");
        }
    }
}