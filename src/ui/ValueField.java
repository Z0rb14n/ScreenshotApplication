package ui;

import javax.swing.*;
import java.awt.*;

// Represents the panel where the number field and its label is drawn
class ValueField extends JPanel {
    private NumberField nf;
    // EFFECTS: initializes panel with given field type (and label)
    ValueField(Type type) {
        super();
        setLayout(new GridLayout(2,1));
        JLabel jl = new JLabel();
        jl.setText(type.getLabel());
        jl.setHorizontalAlignment(JLabel.CENTER);
        add(jl);
        nf = new NumberField(type);
        add(nf);
    }

    // EFFECTS: gets the value of the value field
    int getValue() {
        return Integer.parseInt(nf.getText());
    }

    // Represents the type of ValueField
    enum Type {
        WIDTH("Width",MainUI.DEFAULT_WIDTH),
        HEIGHT("Height",MainUI.DEFAULT_HEIGHT),
        TOP_LEFT_X("Top Left X Position",MainUI.DEFAULT_X),
        TOP_LEFT_Y("Top Left Y Position",MainUI.DEFAULT_Y),
        NUM_TIMES("Number of times",MainUI.DEFAULT_TIMES);
        private String label;
        private int defaultValue;
        // EFFECTS: initializes Type with given label
        Type(String label, int defaultValue) {
            this.label = label;
            this.defaultValue = defaultValue;
        }

        // EFFECTS: returns label for ValueField
        public String getLabel() {
            return label;
        }

        // EFFECTS: gets default value of int
        public int getDefaultValue() {
            return defaultValue;
        }
    }
}
