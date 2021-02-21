import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main {


    public static void main(String[] args) {

        // Start window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartWindow();
            }
        });




        /*
        int xSize = 10;
        int ySize = 10;
        int numberOfMines = 10;
        GridLayout layout = new GridLayout(xSize, ySize);
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xSize * 150, ySize * 150);
        frame.setLayout(layout);



        PlayField playField = new PlayField(xSize, ySize, numberOfMines);

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                JButton button = new JButton("x:" + x + "y:" + y);
                button.setBackground(Color.LIGHT_GRAY);
                button.setOpaque(true);
                int finalX = x;
                int finalY = y;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                            if (!button.getText().equals("?")) {
                                if (playField.isThereMine(finalX, finalY)) {
                                    button.setBackground(Color.RED);
                                    button.setText("x");
                                    JOptionPane.showMessageDialog(frame,"Booooom! Game Over!");
                                } else {
                                    button.setBackground(Color.white);
                                    button.setText(String.valueOf(playField.getNumberOfMinesAround(finalX, finalY)));
                                    playField.decrementOpenFields();
                                    if (playField.hasWon()) {
                                        JOptionPane.showMessageDialog(frame, "Congrats, you are amazing. You have found all the mines!");
                                    }
                                }
                                button.setEnabled(false);
                            }
                        }
                        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                            if (button.getText().equals("?")) {
                                button.setText("");
                                button.setBackground(Color.LIGHT_GRAY);
                            } else {
                                button.setText("?");
                                button.setBackground(Color.YELLOW);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {

                    }
                });
                frame.add(button);
            }
        } */
    }
}
