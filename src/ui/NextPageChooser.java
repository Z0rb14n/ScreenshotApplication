package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the panel that shows the next page combo box and its label
class NextPageChooser extends JPanel {
    // EFFECTS: initializes the panel with the label and the combo box
    NextPageChooser() {
        super();
        setLayout(new GridLayout(2,1));
        JLabel jl = new JLabel();
        jl.setHorizontalAlignment(JLabel.CENTER);
        jl.setText("Next Page:");
        add(jl);
        add(new Chooser());
    }

    // Represents the combo box that the user interacts with
    private class Chooser extends JComboBox<NextPageMode> {
        // EFFECTS: initializes the combo box with a set series of values and an action listener
        Chooser() {
            super(NextPageMode.values());
            setSelectedIndex(MainUI.DEFAULT_MODE_INDEX);
            addActionListener(new Listener());
        }
        // Represnts the action listener
        private class Listener implements ActionListener {
            @Override
            // MODIFIES: MainUI
            // EFFECTS: sets the page mode that the program uses
            public void actionPerformed(ActionEvent e) {
                NextPageMode mode = (NextPageMode) getSelectedItem();
                MainUI.getMainUI().setNextPageMode(mode);
            }
        }
    }


}
