package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


/**
 * JPanel styling components was assisted by ChatGPT
 *
 */
public class HomePanel extends JPanel                                                               //Inheriits the JPanel GUI components
{
        private String regName;
        private JPanel mainContent;
        private JPanel rightPanel;
        private JComboBox<String> sideBarDropdown;
        private String loggedInUser;

    public HomePanel(String fullname)                                                               //Use method as a constructor to return the registered user's name
    {
        this.regName = fullname;
        this.loggedInUser = fullname;                                                               //Save user for metadata 

//------------------------ WELCOME MESSAGE ------------------------//
        JPanel topPanel = new JPanel(new BorderLayout());                                           //Declare a new top panel
        topPanel.setBackground(new Color(45, 45, 45));                                              //Match dark theme
        topPanel.setPreferredSize(new Dimension(1000, 100));                                        //Give it height
        topPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));            //Padding inside


        JLabel welcomeLabel = new JLabel("Welcome, " + regName, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Aptos", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel, BorderLayout.CENTER);

//------------------------ DARK MODE STYLING ------------------------//
        setBackground(Color.DARK_GRAY);
        UIManager.put("Panel.background", new Color(45, 45, 45));                                   //Background for all JPanel components
        UIManager.put("Button.background", new Color(65, 65, 65));                                  //Button background
        UIManager.put("Button.foreground", Color.WHITE);                                            //Button text color
        UIManager.put("Label.foreground", Color.GRAY);                                              //Label text color (Main Content Area)


        setLayout(new BorderLayout());                                                              /*Set main layout of the panel to BorderLayout 
                                                                                                    (NORTH, SOUTH, WEST, EAST, CENTER)*/

//------------------------ LEFT SIDEBAR ------------------------//
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));                                //Stack buttons vertically
        sideBar.setBackground(new Color(30, 30, 30));                                               //Dark sidebar color
        sideBar.setPreferredSize(new Dimension(150, getHeight()));                                  //Fixed width

        //Define button names and icon paths
        String[] labels = {"Home", "Profile", "Series", "Movies", "Contacts", "Settings"};
     
        //Loop through and add buttons to the sidebar
        for (String label : labels) 
        {
            JButton sideButton = new JButton(label);                                                //Create button with label
            sideButton.setHorizontalAlignment(SwingConstants.LEFT);                                 //Align text to the left
            sideButton.setMaximumSize(new Dimension(Short.MAX_VALUE,60));                           //Width of a button
            sideBar.add(sideButton);
            sideBar.add(Box.createVerticalStrut(15));                                               //Space between buttons
            UIHelper.styleSideBarButton(sideButton, new Color(30, 30, 30), new Color(45, 45, 45));
        }

//------------------------ TOP BUTTONS ------------------------//        
        topPanel.setBackground(new Color(45, 45, 45));                                              //Match dark theme

        JPanel leftTop = new JPanel();
        leftTop.setBackground(topPanel.getBackground());
        leftTop.setLayout(new BoxLayout(leftTop, BoxLayout.Y_AXIS));

        //Submit & Exit Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(topPanel.getBackground());
        JButton submitButton = new JButton("Submit");                                               //Create the submit button
        JButton exitButton = new JButton("Exit Application");                                       //Create the exit button
        exitButton.addActionListener(x -> System.exit(0));                                          //Exits the application when clicked
        buttonPanel.add(submitButton);                                                              //Add submit button to panel
        buttonPanel.add(exitButton);                                                                //Add exit button to panel
            UIHelper.styleButton(submitButton, new Color(76, 175, 80), new Color(69, 160, 73));     //Style submit button: Green
            UIHelper.styleButton(exitButton, new Color(244, 67, 54), new Color(211, 47, 47));       //Style exit button: Red

        //Dropdown Menu
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));                         /*You're creating a new container (JPanel) named dropdownPanel.
                                                                                                    This will hold your dropdown menu (e.g. JComboBox).
                                                                                                    Put the panel to the left of the window*/
        dropdownPanel.setBackground(topPanel.getBackground());

        String[] menuItems = {"","Add Series", "Age Restriction", "Delete Series", "Print Report"};
        JComboBox<String> dropdown = new JComboBox<>(menuItems);
        dropdownPanel.add(dropdown);

        //Add both button and dropdown sections to left
        leftTop.add(buttonPanel);
        leftTop.add(dropdownPanel);
        topPanel.add(leftTop, BorderLayout.WEST);

        //Search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(topPanel.getBackground());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        topPanel.add(searchPanel, BorderLayout.EAST);
            UIHelper.styleButton(searchButton, new Color(33, 150, 243), new Color(30, 136, 229));      //Style the search button: blue

//------------------------ SPLIT THE MAIN SCREEN ------------------------// 
        //Create left (main) content panel
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(new Color(60, 60, 60));
        JLabel mainLabel = new JLabel("Main Content Area", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Aptos", Font.BOLD, 14));
        mainContent.add(mainLabel, BorderLayout.CENTER);

        //Create smaller right-side panel
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, getHeight()));
        rightPanel.setBackground(new Color(80, 80, 80));
        JLabel subLabel = new JLabel("Metadata", SwingConstants.CENTER);
        subLabel.setFont(new Font("Aptos", Font.BOLD, 12));
        rightPanel.add(subLabel);

        //Create a split pane to hold both panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainContent, rightPanel);
        splitPane.setDividerLocation(650);                                                          //Left panel gets 800px width
        splitPane.setResizeWeight(0.5);                                                             //80% of space goes to the left panel
        splitPane.setDividerSize(4);                                                                //Thin divider
        splitPane.setBorder(null);                                                                  //Remove ugly default border


//------------------------ ASSEMBLE EVERYTHING ------------------------//
        //Assembles all the parts built into the main layout
        add(topPanel, BorderLayout.NORTH);                                                          //Adds the top panel at the top of the window
        add(sideBar, BorderLayout.WEST);                                                            //Adds the sidebar on the left
        add(splitPane, BorderLayout.CENTER);                                                        //Adds the split pane to the center of the app


//------------------------ CAPTURE SERIES ------------------------//
         dropdown.addActionListener(j -> {                               /*Attach a listener to the dropdown so that when the user selects
                                                                           a menu item, this code runs.
                                                                           The ActionListener will receive an ActionEvent when a selection has
                                                                           been made. If the combo box is editable, then an ActionEvent will be
                                                                           fired when editing has stopped.*/
            
        String selected = (String) dropdown.getSelectedItem();                                      //Get the selected item from the dropdown menu
            if ("Add Series".equals(selected))                                                      //Then apply this condition for the item selected
            {
                AddSeries seriesForm = new AddSeries(loggedInUser);     /*Creates a new AddSeries form and pass in the loggedInUser so it can
                                                                          display their name in the metadata (like "captured by")*/
                JPanel formPanel = seriesForm.getMainFormPanel();               //Get the main form panel from the AddSeries class
                String generatedId = "SER" + System.currentTimeMillis();
                JPanel metaPanel = seriesForm.getMetadataPanel(generatedId);    //Get the metadata panel with the generated ID from the AddSeries class

            //Clear the mainContent area (center of the screen), then add the new form there.
                mainContent.removeAll();
                mainContent.add(formPanel, BorderLayout.CENTER);
                mainContent.revalidate();                                       //Re-layout the components.
                mainContent.repaint();                                          //Visually refresh the UI.
            
            //Clear the sidebar panel (rightPanel) and show the metadata panel there (at the top using BorderLayout.NORTH).
                rightPanel.removeAll();
                rightPanel.add(metaPanel, BorderLayout.NORTH);
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        });
    }
}
                                                                                 
                                                        
           



/**
* References:
* OpenAI. (2025, Aug 2). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat 
* 
*/ 