package ui.components.panels.home;

import ui.components.frames.AppFrame;
import ui.components.panels.ContentPanel;

import java.awt.*;

public class HomePanel extends ContentPanel {

    @Override
    public void savePlanning(String path) {

    }

    /**
     * Main home panel, contains HomeTopPanel & HomeContainerPanel
     * @param parentFrame :AppFrame
     */
    public HomePanel(AppFrame parentFrame) {
        super(parentFrame);
        this.parentFrame = parentFrame;
        this.init();
    }

    private void init(){
        this.setSize(parentFrame.getSize());
        this.setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        this.sidePanel = new HomeMenuPanel(this);
        this.topPanel = new HomeTopPanel(this);
        this.container=new HomeContainerPanel(this);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.WEST);
    }

    @Override
    public void adaptComponentsSize() {
        this.sidePanel.setSize();
        this.topPanel.setSize();
        this.container.setSize();
    }

    public AppFrame getParentFrame() {
        return parentFrame;
    }

    public HomeTopPanel getTopPanel() {
        return topPanel;
    }

    private HomeMenuPanel sidePanel;
    private HomeTopPanel topPanel;
    private HomeContainerPanel container;
    private final AppFrame parentFrame;

}
