package ui.components.panels.home;

import ui.components.panels.ContentPanel;

import javax.swing.*;
import java.awt.*;

public class HomeContainerPanel extends JPanel {
    /**
     *  Custom JPanel used to place the elements of the rest of the view
     */
    public HomeContainerPanel(ContentPanel parentPanel) {
        this.parentPanel = parentPanel;
        this.setSize();
        this.setBackground(parentPanel.getBackground());
        this.setLayout(new BorderLayout(0,10));
        JPanel filesPanel = new FilesHomePanel(this);
        JPanel previewPanel = new PlanningPreviewHomePanel(this);
        this.add(filesPanel,BorderLayout.WEST);
        this.add(previewPanel,BorderLayout.CENTER);
    }

    public void setSize(){
        this.setSize(this.parentPanel.getSize());
    }

    private final ContentPanel parentPanel;
}
