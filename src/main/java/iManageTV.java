
import javax.swing.ImageIcon;
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
            String fullName = AppLogin.getFullName();                                /*Get the full name from the AppLogin class to be
                                                                                       passed to the HomePanel for personalisation of the
                                                                                       welcome message*/
       
        SwingUtilities.invokeLater(() -> {                                           /*Drawing the GUI on the Event Dispatch Thread/EDT. 
                                                                                       Best practice not to draw and update GUI directly 
                                                                                       in the main method to avoid potential threading issues.
                                                                                       THREAD: A mini-program running inside your bigger program/main*/       

            UIHelper.playWelcomeTone("src/sounds/welcome2.wav");           //Plays a welcome sound when the application starts

            JFrame appFrame = new JFrame("iManageTV");                              //Creates JFrame application window called "appFrame"
            appFrame.setIconImage(new ImageIcon("src/icons/main.png").getImage());  //Sets the icon image for the application window
            appFrame.setSize(1100, 650);                                            //Sets the initial size of the application window when launched
            appFrame.setLocationRelativeTo(null);                                   //Centers the frame on the screen
            appFrame.add(new HomePanel(fullName));                                  //Fills up the frame with HomePanel components
            appFrame.setVisible(true);                                              //Make the frame visible
            appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                //Ensure the application exits when the frame is closed
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