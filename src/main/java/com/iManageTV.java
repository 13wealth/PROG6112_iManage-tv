
package com;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.streaming_company.AppLauncher;
import com.streaming_company.HomePanel;
import com.streaming_company.UIHelper;



public class iManageTV 
{
    public static void main(String[] args) 
    {
        AppLauncher.startMenu();                                                                  //-Call this app launcher method to validate user choice
        UIHelper.playWelcomeTone("/sounds/welcome2.wav");                                         //-Plays a welcome tone when the application starts
   
        SwingUtilities.invokeLater(() -> 
        {                                                      
            JFrame appWindow = new JFrame("Your Shows, Your Control!");                             //-Creates JFrame application window called "appWindow"
            UIHelper.setAppIcon(appWindow, "/images/iM_icon.png");                         //-Sets the application icon when the application starts
            UIHelper.adaptScreen(appWindow);                                                        //-Adapts the JFrame to any screen size
            appWindow.setLocationRelativeTo(null);                                                  //-Centers the frame on the screen
            appWindow.add(new HomePanel());                                                         //-Fills up the frame with HomePanel components
            appWindow.setVisible(true);                                                             //-Make the frame visible
            appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                               //-Ensure the application exits when the frame is closed
        });
    }
}



 /**
     * NOTES:
     * This is the main entry point for the iManageTV application.
     * JFrame is just a container window and the HomePanel() is the entire visible UI inside it.
     * It initializes the user interface and sets up the main application window:
     *      - Creates the main application frame
     *      - Sets the application icon
     *      - Configures the initial size and position of the window
     *      - Adds the home panel to the frame
     *      - Makes the frame visible
     * 
     * The swing utility ensures JFrame creation and showing GUI happens on the correct thread.
     * Best practice not to draw and update GUI components directly in the main method
     *  to avoid potential threading issues.
     * This is because Swing is not thread-safe, and all UI updates should occur on 
     *  the Event Dispatch Thread (EDT).
     * THREAD: A mini-program running inside your bigger program/main
     */
 
