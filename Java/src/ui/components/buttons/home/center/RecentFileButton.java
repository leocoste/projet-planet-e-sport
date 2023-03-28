package ui.components.buttons.home.center;

import ui.assets.AssetGetter;
import ui.components.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecentFileButton extends CustomButton {
    /**
     * Custom button used on the home page to open recent files
     * @param parentPanel: JPanel
     * @param filePath: String path to the planning file associated with the button
     * @param fileName: String name of the planning file to be displayed on the UI
     */
    public RecentFileButton(JPanel parentPanel, String filePath, String fileName) {
        super(parentPanel);
        this.iconFileName = "load.png";
        this.setBounds(new Rectangle((int)(this.getParentPanel().getWidth()*0.05),(int)(this.getParentPanel().getHeight()*0.05)));
        this.setCustomButtonIcon(this.iconFileName);
        this.fileName = fileName;
        this.filePath = filePath;
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the selected file in the planning page
            }
        });
    }

    /**
     * Sets the button icon
     * @param iconFileName: string
     */
    @Override
    public void setCustomButtonIcon(String iconFileName){
        ImageIcon imgIcon = new ImageIcon(AssetGetter.getIconPath(iconFileName));
        imgIcon.setImage(this.scaleImage(imgIcon.getImage(), this.getIconSize(), (int)(getImageRatio(imgIcon)*getIconSize())));
        this.setIcon(imgIcon);
    }

    /**
     * Returns the height / width ratio
     * @param image: ImageIcon
     * @return int: height / width ratio
     */
    @Override
    public float getImageRatio(ImageIcon image){
        // computes the height/width ratio to conserve it when resizing the button's image icon
        return (float)image.getIconHeight()/image.getIconWidth();
    }

    /**
     * Paints the string next to the button
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("Arial",Font.BOLD,25));
        g.drawString("Open "+fileName,this.getWidth()+this.getX()+10,this.getY()+this.getHeight()/2);
    }

    @Override
    public int getIconSize(){
        if(super.getParentPanel().getWidth() < super.getParentPanel().getHeight()) {
            return this.getParentPanel().getWidth() / 16;
        }else{
            return this.getParentPanel().getHeight() / 16;
        }
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private final String fileName;
    private String filePath;
}
