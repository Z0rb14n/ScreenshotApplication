package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Represents the buttons on the bottom of the frame
class Buttons extends JPanel {
    // EFFECTS: initializes the panel with a given layout (2 rows, 1 column) and adds buttons
    Buttons() {
        super();
        setLayout(new GridLayout(2,1));
        add(new RunButton());
        add(new PreviewButton());
    }

    // Represents the Preview Button
    private class PreviewButton extends JButton {
        // EFFECTS: initializes the button with given text and a mouse listener
        PreviewButton() {
            super("Preview");
            addMouseListener(new Listener());
        }

        // Represents the mouse listener to test whether the button is pressed
        private class Listener extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    MainUI.getMainUI().preview();
                }
            }
        }
    }

    // Represents the Run Button
    private class RunButton extends JButton {
        // EFFECTS: initializes the button with given text and a mouse listener
        RunButton() {
            super("Run Screenshotter (with " + MainUI.SCREENSHOT_DELAY + " second delay)");
            addMouseListener(new Listener());
        }

        // Represents the mouse listener to test whether the button is pressed
        private class Listener extends MouseAdapter {
            @Override
            // MODIFIES: MainUI
            // EFFECTS: asks the main frame to run the screen shot bot
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    MainUI.getMainUI().runScreenShot();
                }
            }
        }
    }
}
