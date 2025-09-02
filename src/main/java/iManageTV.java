
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.streaming_company.AppLogin;
import com.streaming_company.HomePanel;
import com.streaming_company.UIHelper;

public class iManageTV 
{
    public static void main(String[] args) 
    {
        ///AppLogin.userDetails();                                                     //Call a static method to get user details
            String fullName = AppLogin.getFullName();                                               /*Get the full name from the AppLogin class to be
                                                                                                    passed to the HomePanel for personalisation of the
                                                                                                    welcome message*/
            UIHelper.playWelcomeTone("/sounds/welcome2.wav");                             //-Plays a welcome tone when the application starts 
            SwingUtilities.invokeLater(() -> {                                                      /*This ensures that JFrame creation and showing the GUI happens on the correct thread.
                                                                                                      Best practice not to draw and update GUI components directly
                                                                                                      in the main method to avoid potential threading issues.
                                                                                                      THREAD: A mini-program running inside your bigger program/main*/       
            JFrame appWindow = new JFrame("Your Shows, Your Control!");                             //-Creates JFrame application window called "appWindow"
        //-Fill JFrame with components from different classes
            UIHelper.setAppIcon(appWindow, "/images/iM_icon.png");                         //-Sets the application icon when the application starts
            UIHelper.adaptScreen(appWindow);                                                        //-Adapts the JFrame to any screen size
            appWindow.setLocationRelativeTo(null);                                                  //-Centers the frame on the screen
            appWindow.add(new HomePanel(fullName));                                                 //-Fills up the frame with HomePanel components
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
     */