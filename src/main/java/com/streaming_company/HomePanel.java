package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HomePanel extends JPanel                                                               //-Main panel inherits JPanel
{
    private SidebarPanel sidebarPanel;                                                              //-Declares the sidebar panel
    private TopPanel topPanel;                                                                      //-Declares the top panel
    private RightPanel rightPanel;                                                                  //-Declares the right panel
    private MainContentPanel mainContentPanel;                                                      //-Declares the main content panel

    public HomePanel() 
    {
        setLayout(new BorderLayout());                                                              //-Sets a layout manager

//============================= SIDE BAR=============================//
        sidebarPanel = new SidebarPanel();                                                          //-Creates an instance of SidebarPanel and assigns it to sidebarPanel
        sidebarPanel.setBackground(new Color(30, 30, 30));
        sidebarPanel.setPreferredSize(new Dimension(180, 0));                                       //-Sets preferred size for sidebar panel

        add(sidebarPanel, BorderLayout.WEST);                                                       //-Adds the sidebar panel to the left side of the main panel

        JButton sidebarButton = sidebarPanel.getAddButton();
        sidebarButton.addActionListener(e -> 
        {
    // Step 1: Create AddSeries form
    AddSeries addSeriesForm = new AddSeries();

    // Step 2: Swap mainContentPanel with the form
    mainContentPanel.updateContent(addSeriesForm);

    // Step 3: Handle form submission
    addSeriesForm.getSubmitButton().addActionListener(ev -> {
        // Retrieve captured data
        String[] capturedData = addSeriesForm.getData();                                      //Getter from AddSeries that retrieves user input
    //Update right panel with retrieved data
        rightPanel.setData(capturedData);                                                           /*A setter from RightPanel that takes retrieved data
                                                                                                      from getSeriesData() and updates the right panel with it*/

        // Feedback
        JOptionPane.showMessageDialog(null, "Series added successfully!");

        // Optionally reset or disable the form
        addSeriesForm.getSubmitButton().setEnabled(false);
    });
});

//============================= RIGHT BAR=============================//
        rightPanel = new RightPanel();                                                              //-Creates a new instance of RightPanel and assigns it to rightPanel
        rightPanel.setPreferredSize(new Dimension(600, 0));                                         //-Sets preferred size for right panel
        rightPanel.setBackground(new Color(230, 230, 230));

        add(rightPanel, BorderLayout.EAST);                                                         //-Adds the right panel to the right side

//============================= TOP PANEL=============================//
        topPanel = new TopPanel();                                                                  //-Creates a new instance of TopPanel and assigns it to topPanel

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

