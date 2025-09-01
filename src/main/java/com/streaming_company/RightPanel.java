package com.streaming_company;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPanel extends JPanel {   // Right panel inherits JPanel

    private JLabel lblUser;
    private JLabel lblDate;
    private JLabel lblSeriesId;

    public RightPanel() 
    {                  // Constructor, not a JPanel method
       
        JLabel lblHeader = new JLabel("📋 Metadata");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 14));
        lblHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblUser = new JLabel("User: ---");
        lblDate = new JLabel("Date: ---");
        lblSeriesId = new JLabel("Series ID: ---");

        add(lblHeader);
        add(Box.createVerticalStrut(15));
        add(lblUser);
        add(lblDate);
        add(lblSeriesId);
    }

    // Getters so HomePanel (or future logic) can update labels
    public JLabel getLblUser() { return lblUser; }
    public JLabel getLblDate() { return lblDate; }
    public JLabel getLblSeriesId() { return lblSeriesId; }
}
