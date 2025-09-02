package com.streaming_company;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
     * Code logic was assisted by OpenAI.
     * Sets the application icon for the specified JFrame.
     * Method reads the icon image from a class path to safely access even after packaging into JAR.
     * @param frame
     * @param iconPath
     */
    public static void setAppIcon(JFrame frame, String iconPath) 
    {
        URL iconURL = UIHelper.class.getResource(iconPath);                                         //-Gets the icon URL from the classpath
        if (iconURL != null) 
        {
            frame.setIconImage(new ImageIcon(iconURL).getImage());                                  //-Sets the application icon
        } else {
            System.err.println("Icon not found");
        }
    }
    

    /**
     * Code logic was assisted by OpenAI.
     * Plays a welcome tone when the app launches.
     * Method reads the sound file from a class path to safely access even after packaging into JAR.
     * @param soundPath
     */
    public static void playWelcomeTone(String soundPath) 
    {
        try (InputStream soundStream = UIHelper.class.getResourceAsStream(soundPath))               //-Reads the sound file from the classpath
                                                                                                    //-try/catch (try-with-resource catches and handles the exceptions thrown by AudioSystem.getAudioInputStream(soundStream) and  Clip clip = AudioSystem.getClip()
        {
            if (soundStream != null) 
            {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(soundStream));
                clip.start();
            } else {
                System.err.println("Sound not found");
            }
        } catch (Exception e) {
            e.printStackTrace();                                                                    //-Prints all the exceptions and methods that caused the error
        }
    }


    /**
     * Adapts the JFrame to any screen size.
     * @param frame
     */
    public static void adaptScreen(JFrame frame)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();                         //-Gets the screen size
        int width = screenSize.width;
        int height = screenSize.height;
        frame.setSize(width, height);                                                               //-Sets the initial size of the application window to full screen
    }
}

/**
 * References
 * OpenAI. (2025, September 01). *ChatGPT* (Version GPT-4) [Large language model]. https://chat.openai.com/chat
 *
 */