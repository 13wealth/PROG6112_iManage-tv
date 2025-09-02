package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class HomePanel extends JPanel                                                               //-Main panel inherits JPanel
{
    private SidebarPanel sidebarPanel;                                                              //-Declares the sidebar panel
    private RightPanel rightPanel;                                                                  //-Declares the right panel
    private TopPanel topPanel;
    private MainContentPanel mainContentPanel;                                                    //-Declares the main content panel

    public HomePanel() 
    {
        setLayout(new BorderLayout());                                                              //-Sets a layout manager

//============================= SIDE BAR=============================//
        sidebarPanel = new SidebarPanel();                                                          //-Creates a new instance of SidebarPanel and assigns it to sidebarPanel
        sidebarPanel.setBackground(new Color(30, 30, 30));
        sidebarPanel.setPreferredSize(new Dimension(180, 0));                                       //-Sets preferred size for sidebar panel

        add(sidebarPanel, BorderLayout.WEST);                                                       //-Adds the sidebar panel to the left side of the main panel

//============================= RIGHT BAR=============================//
        rightPanel = new RightPanel();                                                              //-Creates a new instance of RightPanel and assigns it to rightPanel
        rightPanel.setPreferredSize(new Dimension(300, 0));                                         //-Sets preferred size for right panel
        rightPanel.setBackground(new Color(230, 230, 230));

        add(rightPanel, BorderLayout.EAST);                                                         //-Adds the right panel to the right side

//============================= TOP PANEL=============================//
        topPanel = new TopPanel();                                                          //-Creates a new instance of TopPanel and assigns it to topPanel

        add(topPanel, BorderLayout.NORTH);

//========================= MAIN-CONTENT PANEL=========================//    
        mainContentPanel = new MainContentPanel();                                                  //-Creates a new instance of MainContentPanel and assigns it to mainContentPanel

        add(mainContentPanel, BorderLayout.CENTER);                                                 //-Adds the main content panel to the center of the main panel
    }
/**
* Exposes SidebarPanel for navigation logic
* Exposes RightPanel for metadata display
* @return
*/
    public SidebarPanel getSidebarPanel() { return sidebarPanel; }
    public RightPanel getRightPanel() { return rightPanel; }
    public TopPanel getTopPanel() { return topPanel; }
    public MainContentPanel getContentPanel() { return mainContentPanel; }  
}

