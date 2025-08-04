
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.streaming_company.HomePanel;
import com.streaming_company.Registration;

public class TVSeriesManagement 
{
    public static void main(String[] args) 
    {
        Registration.regInput();                                                //Show registration input
            String fullName = Registration.getFullName();                       //Get the full name

        SwingUtilities.invokeLater(() -> 
        {
            JFrame appFrame = new JFrame("iManageTV");
            appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            appFrame.setSize(1100, 650);
            appFrame.setLocationRelativeTo(null);
            appFrame.add(new HomePanel(fullName));                                 //Correct GUI launch
            appFrame.setVisible(true);
        });
    }
}

