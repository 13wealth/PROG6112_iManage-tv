package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class HomePanel extends JPanel                                                               //-Main panel inherits JPanel
{
    private SidebarPanel sidebarPanel; 
    private RightPanel rightPanel;                                                             //-Declares the right panel

    public HomePanel() 
    {
        setLayout(new BorderLayout());                                                              //-Sets a layout manager

//============================= SIDE BAR=============================//
        sidebarPanel = new SidebarPanel();                                                          //-Creates a new instance of SidebarPanel and assigns it to sidebarPanel
        sidebarPanel.setBackground(new Color(30, 30, 30));
        sidebarPanel.setPreferredSize(new Dimension(200, 0));
        
        add(sidebarPanel, BorderLayout.WEST);                                                       //-Adds the sidebar panel to the left side of the main panel

//============================= RIGHT BAR=============================//
        rightPanel = new RightPanel();
        rightPanel.setPreferredSize(new Dimension(200, 0));
        rightPanel.setBackground(new Color(230, 230, 230));

        add(rightPanel, BorderLayout.EAST);   // Adds the right panel to the right side
    }




    /**
     * Exposes SidebarPanel for navigation logic
     * Exposes RightPanel for metadata display
     * @return
     */
    public SidebarPanel getSidebarPanel() { return sidebarPanel; }
    public RightPanel getRightPanel() { return rightPanel; }
}

