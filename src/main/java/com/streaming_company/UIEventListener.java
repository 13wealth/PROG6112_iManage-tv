package com.streaming_company;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UIEventListener {
    private final HomePanel homePanel;

    public UIEventListener(HomePanel homePanel) {
        this.homePanel = homePanel;
        attachListeners();
    }

    private void attachListeners() {
        // ------------------- Add New -------------------
        homePanel.getAddNewButton().addActionListener(e -> {
            // Create the form with the current user
            AddSeries seriesForm = new AddSeries(homePanel.getUserFullName());
            homePanel.setCurrentSeriesForm(seriesForm);

            // Show the form in the main content area
            JPanel formPanel = seriesForm.getMainFormPanel();
            JPanel main = homePanel.getMainContentPanel();
            main.removeAll();
            main.add(formPanel, BorderLayout.CENTER);
            main.revalidate();
            main.repaint();

            // Clear the right panel (metadata) until Submit
            JPanel right = homePanel.getRightPanel();
            right.removeAll();
            right.revalidate();
            right.repaint();
        });

        // ------------------- Submit -------------------
        homePanel.getSubmitButton().addActionListener(e -> {
            AddSeries form = homePanel.getCurrentSeriesForm();
            if (form == null) {
                JOptionPane.showMessageDialog(homePanel, "Please click 'Add New' and fill in the form first.");
                return;
            }

            // Build metadata panel from current form values
            String generatedId = "SER" + System.currentTimeMillis();
            JPanel metaPanel = form.getMetadataPanel(generatedId);

            // Update the right panel with metadata
            JPanel right = homePanel.getRightPanel();
            right.removeAll();
            right.setLayout(new BorderLayout());
            right.add(metaPanel, BorderLayout.NORTH);
            right.revalidate();
            right.repaint();

            // Ask if the user wants to continue
            int result = JOptionPane.showConfirmDialog(
                    homePanel,
                    "Do you want to continue?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.NO_OPTION) {
                // optional: clear form, or disable inputs, etc.
            }
        });

        /*// ------------------- Exit -------------------
        homePanel.getExitButton().addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    homePanel,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });*/
    }
}