package com.streaming_company;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TopPanel extends JPanel
{
    private final JLabel appLogo;
    private final JLabel welcomeNote;
    private final JLabel dateTime;

    public TopPanel() 
    {
//-Step 1: Set layout and background for top panel
        setLayout(new GridLayout(1, 3, 10, 10));                                                    //-Panel structure: 1 row, 3 columns, 10 pixels horizontal and vertical spacing
        setBackground(new Color(45, 45, 45));                                                       //-Dark header bar                                                         

//-Step 2: Build the components in the 3 defined columns
    //-1. Application Logo
            java.net.URL logoURL = getClass().getResource("/images/logo_2.png");                    //-Gets the logo image from the resources folder
            ImageIcon originalIcon = new ImageIcon(logoURL);                                        //-Creates an ImageIcon from the logo URL
            Image scaledImage = originalIcon.getImage().getScaledInstance(180, 70, 
                                                        Image.SCALE_SMOOTH);                        //-Scales the image to fit the label
            ImageIcon scaledIcon = new ImageIcon(scaledImage);                                      //-Creates an ImageIcon from the scaled image
            appLogo = new JLabel(scaledIcon);                                                       //-Creates a JLabel with the scaled icon
            appLogo.setHorizontalAlignment(SwingConstants.LEFT);                                    //-Aligns the logo to the left

            add(appLogo);                                                                           //-Adds the application logo to the panel

    //-2. Welcome Note
            welcomeNote = new JLabel("Welcome to iManageTV!");
            welcomeNote.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
            welcomeNote.setForeground(Color.LIGHT_GRAY);
            welcomeNote.setHorizontalAlignment(SwingConstants.CENTER);

            add(welcomeNote);                                                                       //-Adds the welcome note to the panel

    //-3. Date and Time
            dateTime = new JLabel("Date and Time");
            dateTime.setFont(new Font("Arial", Font.PLAIN, 16));
            dateTime.setForeground(Color.LIGHT_GRAY);
            dateTime.setHorizontalAlignment(SwingConstants.CENTER);

            add(dateTime);                                                                          //-Adds the date and time label to the panel

            startClock();                                                                           //-Calls the method that starts the clock to update the date/time label
    }

     /**
     * Adding a clock code was assisted by OpenAI
     * Updates the date/time label every second.
     */
    private void startClock() 
    {
        Timer time = new Timer(1000, e -> 
        {
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());    //-Gets the current date and time
            dateTime.setText(currentTime);                                                          //-Updates the date and time
        });
            time.start();                                                                           //-Starts the timer to update the date and time
    }
    

     /**
     * Makes this method labels to be accessible from other classes
     * Label updates can be safely done from other classes
     * @return
     */
    public JLabel getLogo() { return appLogo; }
    public JLabel getWelcome() { return welcomeNote; }
    public JLabel getDateTime() { return dateTime; }
}






/**
 * References
 * OpenAI. (2025, September 02). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */