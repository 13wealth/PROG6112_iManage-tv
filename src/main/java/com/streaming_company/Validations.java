package com.streaming_company;

import javax.swing.JOptionPane;

public class Validations 
{
    /**
     * Validates age restriction and episodes fields.
     * Shows a message dialog if validation fails.
     * @param age -> Age Restriction input
     * @param episodes -> Episodes input
     * @return true if both fields are valid numbers, false otherwise
     */
    public static boolean validateData(String age, String episodes) 
    {
        age = (age != null) ? age.trim() : "";                                                      //-If age is not null, then set age to age.trim(). Else, set age to the empty string ""
        episodes = (episodes != null) ? episodes.trim() : "";                                       //-If episodes is not null, then set episodes to episodes.trim(). Else, set episodes to the empty string ""

        if (age.isEmpty() || !age.matches("\\d+")) //-Validates for empty or non-numeric age
        {
            JOptionPane.showMessageDialog(null, "Age Restriction must be a number!");
            return false;
        }

        if (episodes.isEmpty() || !episodes.matches("\\d+"))                    //-Validates for empty or non-numeric episodes
        {
            JOptionPane.showMessageDialog(null, "Episodes must be a number!");
            return false;
        }
        return true;                                                                                //-Return true if both fields are valid
    }
}
