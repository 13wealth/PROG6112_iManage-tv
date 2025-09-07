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
        addButton.addActionListener(a -> 
        {
            CaptureSeries addSeriesForm = new CaptureSeries();                                      //-Create and CaptureSeries object called addSeriesForm
            mainContentPanel.updateContent(addSeriesForm);                                          //-Swaps the main content panel with the CaptureSeries form

    //-Step 2: Handles form submission
            addSeriesForm.getSubmitButton().addActionListener(b -> 
            {
                String[] capturedData = addSeriesForm.getData();                                    //-Getter from CaptureSeries that retrieves user input
                String age = capturedData[2];
                String episodes = capturedData[3];
                if (!Validations.validateData(age, episodes)) return;                               //-Validates the data before proceeding
                rightPanel.setData(capturedData);                                                   //-Setter from RightPanel that updates the right panel with it
                addSeriesForm.getSubmitButton().setEnabled(true);                                   //-Disables the submit button after submission

                JOptionPane.showMessageDialog(null, "Series added successfully!");                  //-Gives feedback to the user        
    
    //-Step 3: Retrieve the data and save it to the JSON file
            String [] rightPanelData = rightPanel.getData();                                        //-Gets data from the right panel
            rightPanel.storeData(rightPanelData);                                                   //-Stores the data in the JSON file

    //-Step 4: Reset the panels and generate a new Series ID
            addSeriesForm.resetFields();                                                            //-Reset data fields in the Series form
            addSeriesForm.setSeriesId(CaptureSeries.generateSeriesId());                                //-Generates a new Series ID
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

