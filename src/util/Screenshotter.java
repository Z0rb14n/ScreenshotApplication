package util;

import java.io.File;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

// Represents the Screenshotter that can take screenshots or press keys
public class Screenshotter extends Robot {
    private static final String JPG = "jpg";
    private static final String JPEG = "jpeg";
    public static final String PNG = "png";
    private static final String BMP = "bmp";
    private static final String WBMP = "wbmp";
    private static final String GIF = "gif";
    public final static int DISPLAYHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public final static int DISPLAYWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    // EFFECTS: initializes the screenshotter
    public Screenshotter() throws AWTException {
        super();
    }

    // EFFECTS: presses and releases the left button
    public void button1() {
        mousePress(InputEvent.BUTTON1_MASK);
        mouseRelease(InputEvent.BUTTON1_MASK);
    }

    // EFFECTS: presses and releases the key of given key code
    public void keyPressRelease(int a_key) {
        keyPress(a_key);
        keyRelease(a_key);
    }

    /**
     * Takes a screenshot with the given format specified by the path Format is
     * one of "jpeg","jpg","png","bmp","wbmp","gif" Saves screenshot to the
     * given path Screenshot is of the screen specified by x/y/height/height
     * coords
     *
     * @param x top left horizontal coordinate of screenshot - starts at 0?
     * @param y top left vertical coordinate of screenshot - starts at 0?
     * @param width width of screenshot
     * @param height height of screenshot
     * @param path path to save screenshot to
     */
    public void screenShot(int x, int y, int width, int height, String path) {
        if (path == null || path.length() < 5) {
            throw new IllegalArgumentException("BRUH WHAT");
        }
        String ext4 = path.substring(path.length() - 4);
        String ext3 = path.substring(path.length() - 3);
        String mode = null;
        if (ext4.compareToIgnoreCase(JPEG) == 0) {
            mode = JPEG;
        } else if (ext3.compareToIgnoreCase(JPG) == 0) {
            mode = JPG;
        } else if (ext3.compareToIgnoreCase(PNG) == 0) {
            mode = PNG;
        } else if (ext3.compareToIgnoreCase(BMP) == 0) {
            mode = BMP;
        } else if (ext4.compareToIgnoreCase(WBMP) == 0) {
            mode = WBMP;
        } else if (ext3.compareToIgnoreCase(GIF) == 0) {
            mode = GIF;
        } else {
            throw new IllegalArgumentException("INVALID FILE EXTENSION");
        }
        File output = new File(path);
        try {
            ImageIO.write(createScreenCapture(new Rectangle(x, y, width, height)), mode, output);
        } catch (IOException e) {
            System.err.println("Could not write image to path: " + path);
        }
    }
}
