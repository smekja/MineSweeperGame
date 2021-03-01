import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;


public class StartWindow {

    StartWindow() {
        JFrame frame = new JFrame("Start Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating components
        JTextField textX = new JTextField(4);
        JTextField textY = new JTextField(4);
        JTextField textMines = new JTextField(4);
        JLabel labelX = new JLabel("Width of the mine field: ");
        JLabel labelY = new JLabel("Height of the mine field: ");
        JLabel labelMines = new JLabel("Number of mines: ");
        JButton startButton = new JButton("Start");

        // Adding compoments
        frame.add(labelX);
        frame.add(textX);
        frame.add(labelY);
        frame.add(textY);
        frame.add(labelMines);
        frame.add(textMines);
        frame.add(startButton);
        startButton.setEnabled(false);

        // Listeners
        DocumentListener watchForChange = new SimpleDocumentListener() {
            @Override
            public void change(DocumentEvent e) {
                Document document = e.getDocument();
                // deletes any letters from the text field, allows only numbers to be entered
                if (document == textX.getDocument()) {
                    newRunnable(textX);
                } else if (document == textY.getDocument()) {
                    newRunnable(textY);
                } else if (document == textMines.getDocument()) {
                    newRunnable(textMines);
                }
                // enables the start button only if all text fields are not empty
                if (!(textX.getText().equals("") || textY.equals("") || textMines.getText().equals(""))) {
                    startButton.setEnabled(true);
                } else {
                    startButton.setEnabled(false);
                }
            }
        };

        textX.getDocument().addDocumentListener(watchForChange);
        textY.getDocument().addDocumentListener(watchForChange);
        textMines.getDocument().addDocumentListener(watchForChange);

        startButton.addActionListener(actionEvent -> {
            int xSize = Integer.parseInt(textX.getText());
            int ySize = Integer.parseInt(textY.getText());
            int numberOfMines = Integer.parseInt(textMines.getText());
            if (xSize * ySize < numberOfMines) {
                JOptionPane.showMessageDialog(null, "Number of mines has to be less than the" +
                        " total size of the mine field.(less than width*height)");
            } else if (numberOfMines < 1) {
                JOptionPane.showMessageDialog(null, "Number of mines has to be more than 0");
            } else {
                new MineFieldApp(xSize, ySize, numberOfMines);
                frame.setVisible(false);
            }
        });
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Deletes any letters from the text field, allows only numbers to be entered
    private String deleteLetters(String text) {
        if (isNumeric(text)) {
            return null;
        } else return text.replaceAll("[^\\d]", "");
    }

    private void newRunnable(JTextField textField) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String text = deleteLetters(textField.getText());
                if (!(text == null)) {
                    textField.setText(text);
                }
            }
        });
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

