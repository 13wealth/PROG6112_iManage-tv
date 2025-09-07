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
        if (age == null || age.trim().isEmpty() || !age.trim().matches("^(?:[2-9]|1[0-8])$"))                                    //-Regex for non-numeric age and valid age range
        {
            JOptionPane.showMessageDialog(null, """
                                                You have entered an invalid series age!
                                                Valid age restriction is between 2 and 18
                                                Please re-enter the series age.
                                                """);
            return false;
        }

        if (episodes == null || episodes.trim().isEmpty() || !episodes.trim().matches("\\d+"))                                        //-Regex for non-numeric episodes
        {
            JOptionPane.showMessageDialog(null, "Episodes must be a number!");
            return false;
        }
        return true;                                                                                //-Return true if both fields are valid
    }
}


