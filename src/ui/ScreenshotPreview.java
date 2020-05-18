package ui;

import javax.swing.*;
import java.awt.*;

// Represents the frame for the preview
class ScreenshotPreview extends JFrame {
    private final static float OPACITY = 0.2f;
    // EFFECTS: initializes the preview with given position and dimensions
    ScreenshotPreview(int topLeftX, int topLeftY, int width, int height) {
        super();
        setSize(width,height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(topLeftX,topLeftY);
        setUndecorated(true);
        setBackground(Color.BLACK);
        addPreviewText(width, height);
        setOpacity();
        setVisible(true);
    }

    // MODIFIES: This
    // EFFECTS: adds the preview text
    private void addPreviewText(int width, int height) {
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(width,height));
        JLabel jl = new JLabel();
        jl.setFont(new Font("Arial",Font.BOLD,30));
        jl.setText("PREVIEW");
        jp.add(jl);
        add(jp);
    }

    // MODIFIES: this
    // EFFECTS: if the system supports opacity, change the window opacity
    private void setOpacity() {
        // Determine if the GraphicsDevice supports translucency.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            System.err.println("Translucency is not supported");
        } else {
            setOpacity(OPACITY);
        }
    }
}
