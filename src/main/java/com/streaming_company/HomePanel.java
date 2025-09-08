package com.streaming_company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

    public class HomePanel extends JPanel                                                           //-Main panel inherits JPanel
    {
        private SidebarPanel sidebarPanel;                                                          //-Declares the sidebar panel
        private TopPanel topPanel;                                                                  //-Declares the top panel
        private RightPanel rightPanel;                                                              //-Declares the right panel
        private MainContentPanel mainContentPanel;  
        private JSONRightPanel jsonRightPanel;                                                      //-Declares the main content panel

        public HomePanel() 
        {
    
    //STEP 1: CREATE LAYOUT, CREATE AND ADD PANELS   

            setLayout(new BorderLayout());                                                          //-Sets a layout manager

        //+++ SIDE BAR PANEL
            sidebarPanel = new SidebarPanel();                                                      //-Creates an instance of SidebarPanel and assigns it to sidebarPanel
            sidebarPanel.setBackground(new Color(30, 30, 30));
            sidebarPanel.setPreferredSize(new Dimension(180, 0));                                   //-Sets preferred size for sidebar panel

            add(sidebarPanel, BorderLayout.WEST);                                                   //-Adds the sidebar panel to the left side of the main panel


        //+++ RIGHT BAR PANEL
            rightPanel = new RightPanel();                                                          //-Creates a new instance of RightPanel and assigns it to rightPanel
            rightPanel.setPreferredSize(new Dimension(600, 0));                                     //-Sets preferred size for right panel
            rightPanel.setBackground(new Color(230, 230, 230));

            add(rightPanel, BorderLayout.EAST);                                                     //-Adds the right panel to the right side

        //+++ TOP PANEL
            topPanel = new TopPanel();                                                              //-Creates a new instance of TopPanel and assigns it to topPanel

            add(topPanel, BorderLayout.NORTH);

        //+++ MAIN-CONTENT PANEL
            mainContentPanel = new MainContentPanel();                                              //-Creates a new instance of MainContentPanel and assigns it to mainContentPanel

            add(mainContentPanel, BorderLayout.CENTER);                                             //-Adds the main content panel to the center of the main panel

            
    //STEP 2: CREATE LOGIC AND ADD IT TO PANELS AND BUTTONS

        //+++ CAPTURE SERIES
            JButton captureButton = sidebarPanel.getCaptureButton();                                //-Calls the capture button from the sidebar panel

            //-Handles the capture button click
            captureButton.addActionListener(a -> 
            {
                swapRightPanel(rightPanel);
                rightPanel.setData(new String[] {"", "", "", "", ""});                              //-Clears previous data on the fields

                CaptureSeries addSeriesForm = new CaptureSeries();                                  //-Creates a new instance of CaptureSeries
                mainContentPanel.updateContent(addSeriesForm);                                      //-Updates the main content panel with the new form

            //-Handles the submit button click
                addSeriesForm.getSubmitButton().addActionListener(b ->                              
                {
                    String[] capturedData = addSeriesForm.getData();                                //-Gets the captured data from the form
                    String age = capturedData[2];
                    String episodes = capturedData[3];
                    if (!Validations.validateData(age, episodes)) return;

                    rightPanel.setData(capturedData);
                    JOptionPane.showMessageDialog(null, "Series added successfully!");
                    rightPanel.storeData(rightPanel.getData());

                    addSeriesForm.resetFields();
                    addSeriesForm.setSeriesId(CaptureSeries.generateSeriesId());                    //-Calls the static method to generate a new Series ID
                });
            });

        //+++ SEARCH SERIES
            JButton searchSeriesButton = sidebarPanel.getSearchButton();                            //-Calls the search button from the sidebar panel

            //-Handles the search button click
            searchSeriesButton.addActionListener(a -> 
            {
                swapRightPanel(rightPanel);

                rightPanel.setData(new String[] {"", "", "", "", ""});

                SearchSeries form = new SearchSeries();                                             //-Creates a new instance of SearchSeries
                mainContentPanel.updateContent(form);
            //-Handles the search button click
                form.getSearchButton().addActionListener(b -> 
                {
                    String seriesId = form.getSeriesId();
                    String[] capturedData = SearchSeries.searchByID(seriesId);                      //-Searches for the series by ID
                    rightPanel.setData(capturedData);
                });
            });

        //+++ UPDATE SERIES
            JButton updateButton = sidebarPanel.getUpdateButton();
            updateButton.addActionListener(a -> 
            {
                jsonRightPanel = new JSONRightPanel();
                swapRightPanel(jsonRightPanel);

                UpdateSeries updateForm = new UpdateSeries();
                mainContentPanel.updateContent(updateForm);

                updateForm.setupLoadAction(jsonRightPanel);
                updateForm.setupSaveAction(jsonRightPanel);
            });

        //+++ DELETE SERIES
            JButton deleteButton = sidebarPanel.getDeleteButton();
            deleteButton.addActionListener(a -> 
            {
                jsonRightPanel = new JSONRightPanel();
                swapRightPanel(jsonRightPanel);                                                     //-Calls a method that swaps the right panel to the correct panel

                DeleteSeries deleteForm = new DeleteSeries();                                       //-Creates an instance of the DeleteSeries class
                mainContentPanel.updateContent(deleteForm);                                         //-Updates the main content panel with the delete form

                deleteForm.deleteExecution(jsonRightPanel);                                         //-Calls the method that executes the delete
            });

        //+++ SERIES REPORT
            JButton reportButton = sidebarPanel.getReportButton();                                  //-Calls the report button from the sidebar panel
            reportButton.addActionListener(a -> 
            {
                jsonRightPanel = new JSONRightPanel();
                swapRightPanel(jsonRightPanel);

                JPanel reportMessagePanel = new JPanel();                                           //-Displays text in the main content area
                JLabel messageLabel = new JLabel("Current Active Series", JLabel.CENTER);           //-Creates a label for the report message
                messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
                messageLabel.setForeground(new Color(50, 50, 50));
                reportMessagePanel.add(messageLabel, BorderLayout.CENTER);
                mainContentPanel.updateContent(reportMessagePanel);

                SeriesReport.printAllSeries();
            });

        //+++ LOG OUT
            JButton logoutButton = sidebarPanel.getLogoutButton();
            logoutButton.addActionListener(a -> 
            {
                System.exit(0);
            });
        }

        /**
         * Helper method to swap the right panel
         * @param newPanel
         */
        private void swapRightPanel(JPanel newPanel) 
        {
            BorderLayout layout = (BorderLayout) getLayout();                                       //-Removes any existing EAST component
            Component east = layout.getLayoutComponent(BorderLayout.EAST);
            if (east != null) remove(east);

            add(newPanel, BorderLayout.EAST);
            revalidate();
            repaint();
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
    

