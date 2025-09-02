package com.streaming_company;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppLogin 
{
    private static String name;
    private static String surname;

    private static JTextField nameField = new JTextField();
    private static JTextField surnameField = new JTextField();

    public static void userDetails()                                                                //-Declare a static method to avoid creating multiple instances
    {
        while(true)
        {
            JPanel inputPanel = new JPanel(new GridLayout(0, 1));                                   //-reate a panel with a vertical grid layout
                inputPanel.add(new JLabel("Name:"));
                inputPanel.add(nameField);           
                inputPanel.add(new JLabel("Surname:"));
                inputPanel.add(surnameField);

                int result = JOptionPane.showConfirmDialog(                                         //-Creates a dialog to confirm user inputs
                                                    null,
                                                    inputPanel,
                                                    "iMANAGE LOGIN",
                                                    JOptionPane.OK_CANCEL_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE
                        );
                    if (result == JOptionPane.OK_OPTION)                                            //-If user presses OK
                    {
                        name = nameField.getText().trim();                                          //-Gets the text from the name field and trim whitespace
                        surname = surnameField.getText().trim();                                    //-Gets the text from the surname field and trim whitespace

                        if (name.isEmpty() || surname.isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "All fields are required.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                                break;                                                              //-Break from the loop if all fields are filled
                        }                                                       
                    } else {
                UIHelper.exitIfNotOk(result);                                                       //-Exits the application if user cancels the dialog
            }
        }
    }

   /**
    * This method gets the name and surname and returns the full name as a string
    *
    */
    public static String getFullName() 
    {
        if (name == null || surname == null) 
        {
            return "No name provided";
        }
    return name + " " + surname;
    }
}
