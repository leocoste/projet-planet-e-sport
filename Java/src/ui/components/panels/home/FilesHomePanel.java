package ui.components.panels.home;

import ui.components.buttons.home.center.RecentFileButton;

import javax.swing.*;
import java.awt.*;

public class FilesHomePanel extends JPanel {
    /**
     * Panel used to display RecentFileButton
     * @param parentPanel :HomeContainerPanel
     */
    public FilesHomePanel(HomeContainerPanel parentPanel) {
        super();
        this.parentPanel = parentPanel;
        init();
        initComponents();
    }
    private void init(){
        this.setSize(new Dimension(parentPanel.getWidth()/4,parentPanel.getHeight()));
        this.setLayout(new GridLayout(9,1));
    }
    private void initComponents(){
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new FlowLayout(FlowLayout.LEFT,19,10));
        JLabel title = new JLabel("Recent files :");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial",Font.BOLD,30));
        container.add(title);
        this.add(container);
        //createRecentFile("Planning 01 - Overwatch");
        //createRecentFile("Planning 02 - CS:GO");
    }

    /**
     * Creates a new RecentFileButton and add it to the panel
     * @param fileName :String, name of the file
     */
    private void createRecentFile(String fileName){
        JPanel container = new JPanel();
        container.setOpaque(false);
        JLabel label = new JLabel(fileName);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial",Font.BOLD,20));
        container.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        RecentFileButton recentFileButton = new RecentFileButton(this,"",fileName);
        recentFileButton.setBounds(new Rectangle(recentFileButton.getBounds().width+label.getWidth(),recentFileButton.getBounds().height));
        container.add(recentFileButton);
        container.add(label);
        this.add(container);
    }
    private final HomeContainerPanel parentPanel;
}
