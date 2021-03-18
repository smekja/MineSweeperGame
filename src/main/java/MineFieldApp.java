import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;


public class MineFieldApp {
    private final int xSize;
    private final int ySize;
    private final int numberOfMines;
    private long runningTime;
    private final Timer timer;
    private final SimpleDateFormat df = new SimpleDateFormat("mm:ss");

    MineFieldApp(int xSize, int ySize, int numberOfMines) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.numberOfMines = numberOfMines;

        JFrame frame = new JFrame("MineSweeper");

        // Panels to divide the window to minefield and timer
        JPanel panelMines = new JPanel(new GridLayout(xSize, ySize));
        JPanel panelTimer = new JPanel(new FlowLayout());
        frame.add(panelMines);
        frame.add(panelTimer);

        // Timer setup
        JLabel runningClock = new JLabel(df.format(0));
        int delay = 1000;
        panelTimer.add(runningClock);

        PlayField playField = new PlayField(xSize, ySize, numberOfMines);

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                JButton button = new JButton();
                button.setBackground(Color.LIGHT_GRAY);
                button.setOpaque(true);
                int finalX = x;
                int finalY = y;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                            if (!(button.getText().equals("?"))) {
                                // Mine exploded
                                if (playField.isThereMine(finalX, finalY)) {
                                    button.setBackground(Color.RED);
                                    button.setText("x");
                                    timer.stop();
                                    createPlayAgainDialog(frame, "Boom, game over!");
                                    frame.dispose();

                                }
                                // Empty field clicked (without a mine)
                                else {
                                    button.setBackground(Color.white);
                                    button.setText(String.valueOf(playField.getNumberOfMinesAround(finalX, finalY)));
                                    playField.decrementOpenFields();
                                    if (playField.hasWon()) {
                                        victory(frame);
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
                            if (playField.hasWon()) {
                                victory(frame);
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
                panelMines.add(button);
            }
        } // End of the for loop

        // Frame setup
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSize(frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                runningClock.setText(df.format(runningTime = runningTime + delay));
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
    } // End of the constructor

    private void createPlayAgainDialog(JFrame frame, String message) {
        Object[] options = {"Yes, please",
                "No way!"};
        int n = JOptionPane.showOptionDialog(frame,
                message + " Would you like to play again?",
                "A Silly Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
        // 0 = yes, play again; 1 = no
        if (n == 0) {
            new MineFieldApp(xSize, ySize, numberOfMines);
        }
    }

    private <T extends Component> void frameSize(T aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        aFrame.setSize((int) (width / 1.3), (int) (height / 1.2));
    }

    private void victory(JFrame frame) {
        timer.stop();
        if (runningTime < PlayField.bestTime) {
            PlayField.bestTime = runningTime;
            createPlayAgainDialog(frame, "Congrats, you are amazing. You have found all the mines! " +
                    "\n\n             You achieved your new best time! " + df.format(PlayField.bestTime) + "\n\n" +
                    "                   ");
        } else
            createPlayAgainDialog(frame, "Congrats, you are amazing. You have found all the mines!");
        frame.dispose();
    }
}
