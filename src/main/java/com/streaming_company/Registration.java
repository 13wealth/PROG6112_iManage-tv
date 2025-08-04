package com.streaming_company;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registration 
{
    private static String name;
    private static String surname;
    private static String email;

    private static JTextField nameField = new JTextField();
    private static JTextField surnameField = new JTextField();
    private static JTextField emailField = new JTextField();

    public static void regInput()                                               //Declare a static method to avoid creating multiple instances
    {
        while(true)
        {
            JPanel regPanel = new JPanel(new GridLayout(0, 1));                 //Create a panel with a vertical grid layout
                regPanel.add(new JLabel("Name:"));
                regPanel.add(nameField);           
                regPanel.add(new JLabel("Surname:"));
                regPanel.add(surnameField);
                /*regPanel.add(new JLabel("Email:"));
                regPanel.add(emailField);*/

                int result = JOptionPane.showConfirmDialog(
                                                    null,
                                                    regPanel,
                                                    "iMANAGE REGISTRATION",
                                                    JOptionPane.OK_CANCEL_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE
                        );
            
                    if (result == JOptionPane.OK_OPTION)                        //User presses OK
                    {
                        name = nameField.getText().trim();                      //Get the text from the name field and trim whitespace
                        surname = surnameField.getText().trim();                //Get the text from the surname field and trim whitespace
                        email = emailField.getText().trim();                    //Get the text from the email field and trim whitespace

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
    public static String getFullName() 
    {
        if (name == null || surname == null) 
        {
            return "No name provided";
        }
    return name + " " + surname;
    }
}
