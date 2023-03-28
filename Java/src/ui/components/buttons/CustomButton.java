package ui.components.buttons;

import ui.assets.AssetGetter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class CustomButton extends JButton{
    /**
     * Abstract class inherited by the other buttons
     */
    private final JPanel parentPanel;
    protected String iconFileName;

    public CustomButton(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        init();
    }
    private void init() {
        // Make the default button invisible
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setMargin(new Insets(0,0,0,0));
        this.setBackground(this.parentPanel.getBackground());
    }

    /**
     * Set the icon for the button (fileName must be specified in the inherited class
     * @param iconFileName: String
     */

    public void setCustomButtonIcon(String iconFileName){
        //set the button's ImageIcon
        ImageIcon imgIcon = new ImageIcon(AssetGetter.getIconPath(iconFileName));
        imgIcon.setImage(this.scaleImage(imgIcon.getImage(), this.getIconSize(), (int)(getImageRatio(imgIcon)*getIconSize())));
        this.setIcon(imgIcon);
    }
    /**
     * returns the icon size
     * @return icon size :int
     */
    public int getIconSize(){
        if(this.parentPanel.getWidth() < this.parentPanel.getHeight()) {
            return this.getParentPanel().getWidth() / 4;
        }else{
            return this.getParentPanel().getHeight() / 4;
        }
    }

    /**
     * Returns the height / width ratio of the icon
     * @return int: height / width ratio
     */
    public float getImageRatio(ImageIcon image){
        int imageHeight = image.getIconHeight();
        int imageWidth = image.getIconWidth();
        // computes the height/width ratio to conserve it when resizing the button's image icon
        return (float)imageHeight/imageWidth;
    }

    /**
     * rescales an image
     * @param srcImg:Image image to rescale
     * @param w:int desired width
     * @param h:int desired height
     * @return image:Image the resized image
     */
    protected Image scaleImage(Image srcImg, int w, int h){
        // modifies the size of the image for the buttons icon
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return img;
    }

    /**
     * Applies an antialiasing effect on the button
     * @param g parent components graphics object
     */
    private void applyAntialiasing(Graphics g){
        // WIP, apply antialiasing effect on the button
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT,RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
    }

    /**
     * calls applyAntialiasing
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        applyAntialiasing(g);
        super.paintComponent(g);
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }
}