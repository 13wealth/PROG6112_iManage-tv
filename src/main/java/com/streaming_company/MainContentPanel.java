package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainContentPanel extends JPanel
{
    /**
     * Constructor that initialises the main content panel
     * Diisplays a text on app launch then updates the panel on every button press
     */
    public MainContentPanel() 
    {
        setLayout(new BorderLayout());                                                              //-Sets layout manager
        setBackground(Color.WHITE);

//-Step 1: Add a temporary placeholder in the content area when app is launched
    JLabel placeHolder = new JLabel("Please select an option from the menu.", 
                                    SwingConstants.CENTER);
                                    placeHolder.setFont(new Font("Arial", Font.ITALIC, 14));
    add(placeHolder, BorderLayout.CENTER);                                                          //-Adds the placeholder label to the center of the panel
    }

//-Step 2: Add a method that will dynamically update the content area on button press
    /**
     * Method updates the main content area with new content
     * We call this method in the HomePanel class to update content for each button
     * @param newContent
     */
    public void updateContent(JComponent newContent) 
    {
        removeAll();                                                                                //-Remove existing content just before adding new one
        add(newContent, BorderLayout.CENTER);                                                       //-Adds the updated content label to the center of the panel
        revalidate();                                                                               //-Refreshes the panel to show the new content
        repaint();                                                                                  //-Repaints the panel to ensure the new content is displayed
    }
}


