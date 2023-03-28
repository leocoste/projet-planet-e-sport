package ui.components.panels;


import models.planning.Planning;
import ui.components.frames.AppFrame;
import ui.components.panels.home.HomeMenuPanel;

import javax.swing.*;
import java.awt.*;
public abstract class ContentPanel extends JPanel {
    /**
     * Save the current planning locally
     * @param path :String path to the file
     */
    public void savePlanning(String path){}
    /**
     * Open and read a planning file
     * @param path :string path to the file
     * @return Planning object
     */
    public Planning openPlanning(String path){return null;}

    /**
     * Creates a new planning
     * @return Planning object
     */
    public Planning createPlanning(){return null;}

    /**
     * Boilerplate class inherited by the other panels
     * @param parentFrame :AppFrame
     */
    public ContentPanel(AppFrame parentFrame){
        this.parentFrame = parentFrame;
        this.setLayout(new BorderLayout(HomeMenuPanel.RIGHT_PADDING,0));
        this.setBackground(this.parentFrame.getBackground());
        init();
    }

    private void init(){
        this.setSize(parentFrame.getSize());
    }

    public void updateSize(){
        this.setSize(parentFrame.getSize());
        this.adaptComponentsSize();
    }

    public abstract void adaptComponentsSize();

    public AppFrame getParentFrame() {
        return parentFrame;
    }
    private final AppFrame parentFrame;




}
