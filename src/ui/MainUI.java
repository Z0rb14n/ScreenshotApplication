package ui;

import util.Screenshotter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

// Represents the main frame
class MainUI extends JFrame {
    private static MainUI singleton;
    private Screenshotter ss;
    private ScreenshotPreview preview;
    private JLabel folderLoc = new JLabel();
    final static int SCREENSHOT_DELAY = 4;
    private Header vfs;
    final static int DEFAULT_WIDTH = 100;
    final static int DEFAULT_HEIGHT = 100;
    final static int DEFAULT_X = 0;
    final static int DEFAULT_Y = 0;
    final static int DEFAULT_TIMES = 1;
    final static int DEFAULT_MODE_INDEX = 0;
    private int width;
    private int topLeftX;
    private int topLeftY;
    private int height;
    private int numTimes;
    private NextPageMode mode;
    private File folder;

    // EFFECTS: returns the singleton main UI instance
    static MainUI getMainUI() {
        if (singleton == null) {
            singleton = new MainUI();
        }
        return singleton;
    }

    // MODIFIES: this
    // EFFECTS: sets default values for screenshotter program
    private void setDefaultValues() {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        topLeftX = DEFAULT_X;
        topLeftY = DEFAULT_Y;
        numTimes = DEFAULT_TIMES;
        mode = NextPageMode.values()[DEFAULT_MODE_INDEX];
    }

    // EFFECTS: initializer that initializes all components of the UI
    private MainUI() {
        super("Screenshot Application");
        setDefaultValues();
        folderLoc.setHorizontalAlignment(JLabel.CENTER);
        folderLoc.setText("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000,200);
        setLocationRelativeTo(null);
        vfs = new Header();
        add(vfs,BorderLayout.PAGE_START);
        add(new Buttons(),BorderLayout.PAGE_END);
        add(folderLoc,BorderLayout.CENTER);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets the screenshot height , and hides the preview
    void setHeight(int height) {
        if (preview != null) preview.setVisible(false);
        this.height = height;
    }

    // MODIFIES: this
    // EFFECTS: sets the screenshot width, and hides the preview
    void setWidth(int width) {
        if (preview != null) preview.setVisible(false);
        this.width = width;
    }

    // MODIFIES: this
    // EFFECTS: sets the screenshot position, and hides the preview
    void setTopLeftX(int x) {
        if (preview != null) preview.setVisible(false);
        this.topLeftX = x;
    }

    // MODIFIES: this
    // EFFECTS: sets the screenshot position, and hides the preview
    void setTopLeftY(int y) {
        if (preview != null) preview.setVisible(false);
        this.topLeftY = y;
    }

    // MODIFIES: this
    // EFFECTS: sets the number of times the screenshot program runs
    void setNumTimes(int times) {
        this.numTimes = times;
    }

    // MODIFIES: this
    // EFFECTS: sets the key pressed when the program moves to the next page
    void setNextPageMode(NextPageMode m) {
        this.mode = m;
    }

    // MODIFIES: this
    // EFFECTS: sets the location that the files are saved to
    void setFolderLocation(File loc) {
        folderLoc.setText("Folder: " + loc.getPath());
        folderLoc.repaint();
        this.folder = loc;
    }

    // MODIFIES: this
    // EFFECTS: displays a preview of what is to be screenshotted
    void preview() {
        vfs.update();
        if (preview != null) preview.setVisible(false);
        if (width == 0 || height == 0) {
            JOptionPane.showMessageDialog(this,"1D screenshot. No preview available.","Message",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        preview = new ScreenshotPreview(topLeftX,topLeftY,width,height);
    }

    // MODIFIES: this
    // EFFECTS: runs the screenshot program
    void runScreenShot() {
        vfs.update();
        if (preview != null) preview.setVisible(false);
        if (folder == null || !folder.isDirectory() || !folder.exists()) {
            JOptionPane.showMessageDialog(this,"Invalid folder location","Error",JOptionPane.ERROR_MESSAGE);
            return;
        } else if (numTimes < 1) {
            JOptionPane.showMessageDialog(this,"Screenshot number < 1. None taken.","Message",JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (width == 0 || height == 0) {
            JOptionPane.showMessageDialog(this,"1D screenshot. None taken.","Message",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            Thread.sleep(SCREENSHOT_DELAY * 1000);
        } catch (InterruptedException ignored) {
            return;
        }
        Screenshotter ss;
        try {
            ss = new Screenshotter();
            int centerX = topLeftX + (width / 2);
            int centerY = topLeftY + (height / 2);
            ss.mouseMove(centerX,centerY);
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(this,"Could not initialize screenshotter","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (int i = 0; i < numTimes; i++) {
            String loc = folder.getPath() + File.separator + "Image " + (i+1) + "." + Screenshotter.PNG;
            ss.screenShot(topLeftX,topLeftY,width,height,loc);
            if (i != numTimes - 1) {
                if (mode == NextPageMode.BUTTON1) {
                    ss.button1();
                } else if (mode == NextPageMode.SPACEBAR) {
                    ss.keyPressRelease(KeyEvent.VK_SPACE);
                } else if (mode == NextPageMode.RIGHTARROW) {
                    ss.keyPressRelease(KeyEvent.VK_RIGHT);
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
