package com.streaming_company;

import javax.swing.JOptionPane;

public class DialogHelper 
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
}

