package com.streaming_company;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

public class UIHelper 
{
    /**
     * Show a dialog and exit if cancelled or closed
     * Works on JOptionPane.showInputDialog
     * @param input
     */
    public static void exitIfCancelled(String input)
    {
        if(input == null)
        {
            JOptionPane.showMessageDialog(null, "Thank you for visiting iManage-Tv",
                                                    "EXIT",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            
        }
    }

    /**
     * Show a confirmation dialog and exit if OK is not pressed
     * Works on JOptionPane.showConfirmDialog
     * @param input
     */
    public static void exitIfNotOk(int input)
    {
        if(input != JOptionPane.OK_OPTION)
        {
            JOptionPane.showMessageDialog(null, "Thank you for visiting iManage-Tv",
                                                    "EXIT",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            
        }
    }

    public static void deleteConf()
    {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
                                                     "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            exitIfNotOk(response);
    }

    
    /**
     * Styles a JButton with specified colors.
     * @param button
     * @param baseColor
     * @param hoverColor
     */
    public static void styleButton(JButton button, Color baseColor, Color hoverColor) 
    {
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(baseColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Aptos", Font.BOLD, 12));
        button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        button.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                button.setBackground(baseColor);
            }
        });
    }

    public static void styleSideBarButton(JButton button, Color baseColor, Color hoverColor) 
    {
        button.setFocusPainted(false);
    button.setBorderPainted(true);
    button.setContentAreaFilled(true);
    button.setOpaque(true);
    button.setBackground(baseColor);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Aptos", Font.BOLD, 14));

    // ✅ Combine bevel + padding using compound border
    button.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createBevelBorder(BevelBorder.RAISED),     // outer bevel
        BorderFactory.createEmptyBorder(10, 20, 10, 20)           // inner padding
    ));
    button.addMouseListener(new java.awt.event.MouseAdapter() 
    {
        public void mouseEntered(java.awt.event.MouseEvent evt) 
        {
            button.setBackground(hoverColor); // Use passed hoverColor
        }

        public void mouseExited(java.awt.event.MouseEvent evt) 
        {
            button.setBackground(baseColor); // Use passed baseColor
        }
    });
}
}