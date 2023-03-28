package ui.components.panels.home;

import ui.components.frames.AppFrame;

import javax.swing.*;
import java.awt.*;

public class PlanningPreviewHomePanel extends JPanel {
    /**
     * Panel used to display a preview of the selected planning
     * @param parentPane :HomeContainerPanel
     */
    public PlanningPreviewHomePanel(HomeContainerPanel parentPane)
    {
        this.parentPanel = parentPane;
        init();
    }
    private void init(){
        this.setSize(new Dimension(3*(parentPanel.getWidth()/4),parentPanel.getHeight()));
        this.setLayout(new BorderLayout(0,20));
        initComponents();
    }
    private void initComponents(){

        JPanel panel = new JPanel();
        panel.setBackground(AppFrame.BACKGROUND_COLOR);
        this.add(panel, BorderLayout.CENTER);

        JPanel borderPanelNorth = new JPanel();
        borderPanelNorth.setPreferredSize( new Dimension(0,0));
        this.add(borderPanelNorth, BorderLayout.NORTH);


        JPanel borderPanelEast = new JPanel();
        borderPanelEast.setPreferredSize( new Dimension((int)(this.getWidth()*0.02),0));
        this.add(borderPanelEast, BorderLayout.EAST);

        JPanel borderPanelWest = new JPanel();
        borderPanelWest.setPreferredSize( new Dimension((int)(this.getWidth()*0.02),0));
        this.add(borderPanelWest, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize( new Dimension(0,(int)(this.getWidth()*0.04)));
        this.add(buttonPanel, BorderLayout.SOUTH);
        JButton deleteButton = new JButton("Delete");
        JButton openButton = new JButton("Open");
        deleteButton.setBackground(this.getBackground().darker());
        openButton.setBackground(this.getBackground().darker());
        buttonPanel.add(deleteButton);
        buttonPanel.add(openButton);
    }
    private final HomeContainerPanel parentPanel;


}
