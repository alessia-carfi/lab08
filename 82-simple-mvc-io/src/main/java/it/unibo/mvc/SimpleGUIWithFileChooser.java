package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "My Second GUI";
    private static final String PATH = System.getProperty("user.home")
    + File.separator
    + SimpleGUI.class.getSimpleName() + ".txt";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Creates a new SimpleGUIWithFileChooser.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());

        final JTextArea text = new JTextArea();
        final JButton button = new JButton("Save");

        final JTextField field = new JTextField();
        field.setEditable(false);
        final JButton secondButton = new JButton("Browse...");

        panel.add(text);
        panel.add(button, BorderLayout.SOUTH);

        secondPanel.add(field);
        secondPanel.add(secondButton, BorderLayout.EAST);
        panel.add(secondPanel, BorderLayout.NORTH);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                if (JOptionPane.showConfirmDialog(
                    frame,
                    "Save file",
                    "Do you want to save file?",
                    JOptionPane.YES_NO_OPTION
                    ) == 0) {
                    try (PrintStream ps = new PrintStream(PATH, StandardCharsets.UTF_8)) {
                        ps.print(text.getText());
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
                    }
                }
            }
        });

        secondButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                final Controller controller = new Controller();
                final JFileChooser chooser = new JFileChooser();
                final JPanel chooserFrame = new JPanel();
                final File newFile = new File(PATH);

                try {
                    if (chooser.showSaveDialog(chooserFrame) == JFileChooser.APPROVE_OPTION) {
                        controller.setFile(newFile);
                        field.setText(newFile.getPath());
                        text.setText(Controller.getFileContent(newFile));
                    }
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(frame, e2, "Error", JOptionPane.ERROR_MESSAGE);
                    e2.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
    }
    private void display() {

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setLocationByPlatform(true);

        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
     }


}
