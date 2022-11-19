package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private final SimpleController controller = new SimpleController();

    /**
     * Creates a new SimpleGUI.
     */
    public SimpleGUI() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField field = new JTextField();
        panel.add(field, BorderLayout.NORTH);
        final JTextArea area = new JTextArea();
        panel.add(area, BorderLayout.CENTER);
        final JButton buttonPrint = new JButton("Print");
        final JButton buttonHistory = new JButton("Show history");
        final JPanel panelButton = new JPanel();
        panelButton.setLayout(new BorderLayout());
        panelButton.add(buttonPrint, BorderLayout.WEST);
        panelButton.add(buttonHistory, BorderLayout.EAST);
        panel.add(panelButton, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonPrint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    controller.setString(field.getText());
                    controller.printCurrent();
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error: ", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); //NOPMD
                }
            }
        });

        buttonHistory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    area.setText("");
                    for (final String str : controller.getHistory()) {
                        area.append(str + "\n");
                    }
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error: ", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); //NOPMD
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
        new SimpleGUI().display();
     }

}
