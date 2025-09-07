package com.streaming_company;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SidebarPanel extends JPanel                                                            //-Sidebar panel inherits JPanel
{
    private final Map<String, JButton> sidebarButton = new LinkedHashMap<>();                                   //-Maintains insertion order

    /**
     * Constructor for SidebarPanel
     * Sets a layout for the sidepanel and initializes buttons
     */
    public SidebarPanel() 
    {
//-Step 1: Set layout for sidebar panel
        setLayout(new GridLayout(6, 1, 10, 10));                                                    //-Panel structure: 6 rows, 1 column, 10 pixels horizontal and vertical spacing

//-Step 2: Adds Button labels to an array
        String[] labels = { "Capture Series", 
                            "Search Series", 
                            "Update Series", 
                            "Delete Series", 
                            "Reports", 
                            "Logout" 
                          };

//-Step 3: Create and style buttons using a loop
        for (String label : labels) 
        {
            JButton btn = new JButton(label);                                                       //-Declares and Creates a new button
            styleButton(btn);                                                                       //-Calls the styleButton method and applies styling to the new button created above
            sidebarButton.put(label, btn);                                                                //-Stores button in map for easy access 
            add(btn);                                                                               //-Adds button to the panel
        }
    }

    /**
     * Button Styling was assisted by ChatGPT
     * Method to style buttons consistently
     * @param button
     */
    private void styleButton(JButton button)                                                        //-Calls styleButton method above and applies consistent styling
    {
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
//-Adds a bevel style to the button
       /* button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createBevelBorder(1, Color.LIGHT_GRAY, Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
    ));*/
//-Adds a hover effect to the button
        button.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override                                                                                   //-Indicates an override of the JPanel attributes
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                button.setBackground(new Color(90, 90, 90));                                            //-Lighter when hover on a button
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                button.setBackground(new Color(60, 60, 60));                                            //-Restore original once mouse exits
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                button.setBorder(BorderFactory.createBevelBorder(0, Color.DARK_GRAY, Color.GRAY));      //-Creates this event when the mouse is pressed
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBorder(BorderFactory.createBevelBorder(1, Color.LIGHT_GRAY, Color.DARK_GRAY)); //-Creates this event when the mouse is released
            }
        });
    }

    /**
     * Getter for HomePanel to attach navigation logic
     * @param name
     * @return
     */
    public JButton getAddButton() { return sidebarButton.get("Capture Series"); }
}


 /**
     * References
     * OpenAI. (2025, August 31). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
     *
     */