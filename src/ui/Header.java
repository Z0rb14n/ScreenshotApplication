package ui;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

// Represents the header of the frame
class Header extends JPanel {
    private final ValueField[] vf = new ValueField[5];

    // EFFECTS: initializes the header with 5 given value field inputs, the next page button chooser and the file chooser
    Header() {
        super();
        vf[0] = new ValueField(ValueField.Type.WIDTH);
        vf[1] = new ValueField(ValueField.Type.HEIGHT);
        vf[2] = new ValueField(ValueField.Type.TOP_LEFT_X);
        vf[3] = new ValueField(ValueField.Type.TOP_LEFT_Y);
        vf[4] = new ValueField(ValueField.Type.NUM_TIMES);
        for (ValueField v : vf) {
            add(v);
        }
        add(new NextPageChooser());
        add(new FileLocationChooser());
    }

    // MODIFIES: MainUI
    // EFFECTS: updates the value for the Main UI frame
    void update() {
        MainUI.getMainUI().setWidth(vf[0].getValue());
        MainUI.getMainUI().setHeight(vf[1].getValue());
        MainUI.getMainUI().setTopLeftX(vf[2].getValue());
        MainUI.getMainUI().setTopLeftY(vf[3].getValue());
        MainUI.getMainUI().setNumTimes(vf[4].getValue());
    }

    // Represents the File chooser
    private class FileLocationChooser extends JButton {
        // EFFECTS: initializes the file chooser with given text and a mouse listener
        FileLocationChooser() {
            super("Choose folder");
            addMouseListener(new Listener());
        }

        // Represents a mouse listener
        private class Listener extends MouseAdapter {
            @Override
            // MODIFIES: MainUI
            // EFFECTS: changes the selected folder location if the user approved an option
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JFileChooser jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int option = jfc.showOpenDialog((FileLocationChooser) e.getSource());
                    if (option == JFileChooser.APPROVE_OPTION) {
                        File file = jfc.getSelectedFile();
                        MainUI.getMainUI().setFolderLocation(file);
                    }
                }
            }
        }
    }
}
