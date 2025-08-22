
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
        AppLogin.userDetails();                                                     //Call a static method to get user details
            String fullName = AppLogin.getFullName();                                /*Get the full name from the AppLogin class to be
                                                                                       passed to the HomePanel for personalisation of the
                                                                                       welcome message*/
       
        SwingUtilities.invokeLater(() -> {                                           /*Drawing the GUI on the Event Dispatch Thread/EDT. 
                                                                                       Best practice not to draw and update GUI directly 
                                                                                       in the main method to avoid potential threading issues.
                                                                                       THREAD: A mini-program running inside your bigger program/main*/       

            UIHelper.playWelcomeTone("src/sounds/welcome2.wav");           //Plays a welcome sound when the application starts

            JFrame appFrame = new JFrame("iManageTV");
            appFrame.setIconImage(new ImageIcon("src/icons/main.png").getImage());
            appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 //Ensure the application exits when the frame is closed
            appFrame.setSize(1100, 650);
            appFrame.setLocationRelativeTo(null);                                    //Center the frame on the screen
            appFrame.add(new HomePanel(fullName));                                   //Correct GUI launch
            appFrame.setVisible(true);                                               //Make the frame visible
        });
    }
}
