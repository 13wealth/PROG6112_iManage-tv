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
    //private static String email;

    private static JTextField nameField = new JTextField();
    private static JTextField surnameField = new JTextField();
    //private static JTextField emailField = new JTextField();

    public static void userDetails()                                               //Declare a static method to avoid creating multiple instances
    {
        while(true)
        {
            JPanel inputPanel = new JPanel(new GridLayout(0, 1));                 //Create a panel with a vertical grid layout
                inputPanel.add(new JLabel("Name:"));
                inputPanel.add(nameField);           
                inputPanel.add(new JLabel("Surname:"));
                inputPanel.add(surnameField);
                /*inputPanel.add(new JLabel("Email:"));
                inputPanel.add(emailField);*/

                int result = JOptionPane.showConfirmDialog(
                                                    null,
                                                    inputPanel,
                                                    "iMANAGE LOGIN",
                                                    JOptionPane.OK_CANCEL_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE
                        );
            
                    if (result == JOptionPane.OK_OPTION)                        //User presses OK
                    {
                        name = nameField.getText().trim();                      //Get the text from the name field and trim whitespace
                        surname = surnameField.getText().trim();                //Get the text from the surname field and trim whitespace
                        //email = emailField.getText().trim();                    

                        if (name.isEmpty() || surname.isEmpty() /*|| email.isEmpty()*/)
                        {
                            JOptionPane.showMessageDialog(null, "All fields are required.", "ERROR", JOptionPane.ERROR_MESSAGE);
                       /* } else if (!inputValidation.checkEmail(email)) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "ERROR", JOptionPane.ERROR_MESSAGE);*/
                        } else {
                                break;                                          //If all fields are filled and email is valid then exit loop
                        }                                                       
                    } else {
                UIHelper.exitIfNotOk(result);    
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
