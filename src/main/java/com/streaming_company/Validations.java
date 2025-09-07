package com.streaming_company;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Validations {

    public static void validateAgeAndProcess(String ageInput, String[] capturedData,
                                         RightPanel rightPanel, AddSeries addSeriesForm,
                                         MainContentPanel mainContentPanel) {
    if (ageInput == null || ageInput.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Age field cannot be empty.");
        return;
    }

    String trimmed = ageInput.trim();

    for (int i = 0; i < trimmed.length(); i++) {
        if (!Character.isDigit(trimmed.charAt(i))) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric age.");
            return;
        }
    }

    int age = Integer.parseInt(trimmed);

    if (age < 0) {
        JOptionPane.showMessageDialog(null, "Age cannot be negative.");
        return;
    }

    // ✅ Everything happens here
    rightPanel.setData(capturedData);
    addSeriesForm.getSubmitButton().setEnabled(false);
    JOptionPane.showMessageDialog(null, "Series added successfully!");

    String[] rightPanelData = rightPanel.getData();
    rightPanel.saveData(rightPanelData);

    mainContentPanel.updateContent(new JPanel());
    rightPanel.setData(new String[]{"", "", "", "", ""});
}

}
