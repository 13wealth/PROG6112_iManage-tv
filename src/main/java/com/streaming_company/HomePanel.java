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
  
//STEP 1: CREATE LAYOUT, CREATE AND ADD PANELS   

        setLayout(new BorderLayout());                                                              //-Sets a layout manager

    //+++ SIDE BAR PANEL
        sidebarPanel = new SidebarPanel();                                                          //-Creates an instance of SidebarPanel and assigns it to sidebarPanel
        sidebarPanel.setBackground(new Color(30, 30, 30));
        sidebarPanel.setPreferredSize(new Dimension(180, 0));                                       //-Sets preferred size for sidebar panel

        add(sidebarPanel, BorderLayout.WEST);                                                       //-Adds the sidebar panel to the left side of the main panel


    //+++ RIGHT BAR PANEL
        rightPanel = new RightPanel();                                                              //-Creates a new instance of RightPanel and assigns it to rightPanel
        rightPanel.setPreferredSize(new Dimension(600, 0));                                         //-Sets preferred size for right panel
        rightPanel.setBackground(new Color(230, 230, 230));

        add(rightPanel, BorderLayout.EAST);                                                         //-Adds the right panel to the right side

    //+++ TOP PANEL
        topPanel = new TopPanel();                                                                  //-Creates a new instance of TopPanel and assigns it to topPanel

        add(topPanel, BorderLayout.NORTH);

    //+++ MAIN-CONTENT PANEL
        mainContentPanel = new MainContentPanel();                                                  //-Creates a new instance of MainContentPanel and assigns it to mainContentPanel

        add(mainContentPanel, BorderLayout.CENTER);                                                 //-Adds the main content panel to the center of the main panel


//STEP 2: CREATE LOGIC AND ADD IT TO PANELS AND BUTTONS CREATED ABOVE
    
    //+++ ADD SERIES BUTTON  

    //-Step 1: Create AddSeries form and update main content panel
        JButton addButton = sidebarPanel.getAddButton();                                            //-From SidebarPanel(): Get the empty "Add Series" button
        addButton.addActionListener(e -> 
        {
            AddSeries addSeriesForm = new AddSeries();                                              //-From AddSeries(): Apply the logic to the "Add Series" button
            mainContentPanel.updateContent(addSeriesForm);                                          //-Swaps the main content panel with the AddSeries form

    //-Step 2: Handles form submission
            addSeriesForm.getSubmitButton().addActionListener(ev -> 
            {
                String[] capturedData = addSeriesForm.getData();                                    //-Getter from AddSeries that retrieves user input
                rightPanel.setData(capturedData);                                                   //-Setter from RightPanel that updates the right panel with it
                addSeriesForm.getSubmitButton().setEnabled(false);                                  //-Disables the submit button after submission

                JOptionPane.showMessageDialog(null, "Series added successfully!");                  //-Gives feedback to the user        
    
    //-Step 3: Retrieve the data and save it to the JSON file
            String [] rightPanelData = rightPanel.getData();                                        //-Gets data from the right panel
            rightPanel.saveData(rightPanelData);                                                    //-Saves the data in the JSON file

    //-Step 4: Reset the panels
            mainContentPanel.updateContent(new JPanel());                                           //-Reset the main content panel
            rightPanel.setData(new String[]{"", "", "", "", ""});                                   //-Reset the right panel data                                                                   
            });
        });
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

