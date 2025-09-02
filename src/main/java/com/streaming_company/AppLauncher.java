package com.streaming_company;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppLauncher extends JPanel
{
    private static String menuChoice;                                                               //-Declares a private menuChoice variable
    private static JTextField launchField = new JTextField();                                       //-Creates a text field for user input

    /**
     * Shows the startup menu in a loop.
     * Returns true only when the user enters '1' to launch.
     * Exits the program if the user cancels or chooses another option.
     */
    public static boolean startMenu() 
    {
        while(true)                                                                                 //-Wrap the menu choice logic in a while loop to handle empty input
        {
            launchField.setText("");                                                                //-Clears/Resets the text field on a loop
    
    //-Step 1: Create an input panel for the menu. Adds a label and text field
            JPanel menuPanel = new JPanel(new GridLayout(0, 1));
            menuPanel.add(new JLabel("<html> (1) Enter '1' to Launch the Application.<br> " +
                                            "(2) Enter any other key to exit. </html>"));
            menuPanel.add(launchField);

    //-Step 2: Launch a dialog and pass the input to it
                int result = JOptionPane.showConfirmDialog(
                        null,
                        menuPanel,
                        "iMANAGE STARTUP",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION)                                                //-If user clicks OK
                {
                    menuChoice = launchField.getText().trim();                                      //-Go get the user input and store it in menuChoice

                    if (menuChoice.isEmpty()) 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter a choice to continue.");
                            continue;                                                               //-Restarts the loop for valid input
                    }

                        if (!"1".equals(menuChoice)) 
                        {
                            JOptionPane.showMessageDialog(null, "Goodbye 👋");
                                System.exit(0);                                                     //-Exit if not 1
                        }
                        return true;                                                                //-Otherwise continue to app
                    } else {
                UIHelper.exitIfNotOk(result);                                                       //-Call helper method if user exits the application
            return false;
            }
        }
    }
}
