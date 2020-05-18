package ui;

import util.Screenshotter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static ui.ValueField.Type;

// Represents the text box to input numbers
class NumberField extends JTextField {
    private final static int LENGTH = 6;
    private Type type;
    // EFFECTS: initializes the text box with a given type (i.e. what input is it for)
    NumberField(Type type) {
        super(LENGTH);
        setText("" + type.getDefaultValue());
        setColumns(LENGTH);
        ((PlainDocument) getDocument()).setDocumentFilter(new IntegerFilter());
        setHorizontalAlignment(JTextField.CENTER);
        NumberField nf = this;
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                nf.selectAll();
            }
        });
        this.type = type;
    }

    // Represents the filter for the text box
    private class IntegerFilter extends DocumentFilter {
        @Override
        // EFFECTS: inserts the string to given location, assuming it is valid
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        // EFFECTS: returns whether a string is valid text
        private boolean test(String text) {
            if (text.length() > LENGTH) return false;
            try {
                int result = Integer.parseInt(text);
                if (type == Type.WIDTH || type == Type.TOP_LEFT_X) {
                    if (result >= Screenshotter.DISPLAYWIDTH) return false;
                }
                if (type == Type.HEIGHT || type == Type.TOP_LEFT_Y) {
                    if (result >= Screenshotter.DISPLAYHEIGHT) return false;
                }
                return result >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        // EFFECTS: replaces text if the text is valid
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        // EFFECTS: removes text if the text is valid
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString())) {
                super.remove(fb, offset, length);
            }
        }
    }
}
