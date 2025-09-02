package com.streaming_company;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPanel extends JPanel 
{                                                            //-Right panel inherits JPanel

    private JLabel user;
    private JLabel date;
    private JLabel seriesId;

    public RightPanel() 
    {                  
       
        JLabel header = new JLabel("📋 Metadata");
        
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setAlignmentX(Component.LEFT_ALIGNMENT);

        user = new JLabel("User: ---");
        date = new JLabel("Date: ---");
        seriesId = new JLabel("Series ID: ---");

        add(header);
        add(Box.createVerticalStrut(15));
        add(user);
        add(date);
        add(seriesId);
    }

    // Getters so HomePanel (or future logic) can update labels
    public JLabel getUser() { return user; }
    public JLabel getDate() { return date; }
    public JLabel getSeriesId() { return seriesId; }
}
