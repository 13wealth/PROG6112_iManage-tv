package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainContentPanel extends JPanel
{
    private JLabel contentLabel;

    public MainContentPanel() 
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

//-Step 1: Add a temporary placeholder in the content area when no button is pressed
        JLabel placeHolder = new JLabel("Please select an option from the menu.",              //-This is displayed when no content is available
                                         SwingConstants.CENTER);
        add(placeHolder, BorderLayout.CENTER);                                                      //-Adds the placeholder label to the center of the panel
    }

//-Step 2: Add a method that will dynamically update the content area
    public void updateContent(String newContent) 
    {
        removeAll();                                                                                //-Remove existing components just before adding new ones
        ///contentLabel = new JLabel(newContent, SwingConstants.CENTER);                               //-Create a new JLabel for the updated content
        add(contentLabel, BorderLayout.CENTER);                                                     //-Adds the updated content label to the center of the panel
        revalidate();                                                                                //-Refresh the panel to show the new content
        repaint();                                                                                   //-Repaints the panel to ensure the new content is displayed
    }
}


