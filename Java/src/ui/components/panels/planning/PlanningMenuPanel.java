package ui.components.panels.planning;

import ui.components.buttons.home.menu.HomeButton;
import ui.components.buttons.home.menu.NewFileButton;
import ui.components.buttons.home.menu.OpenFileButton;
import ui.components.buttons.home.menu.SettingsButton;
import ui.components.frames.CreateTournamentFrame;
import ui.components.panels.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanningMenuPanel extends JPanel {
    final ContentPanel parentPanel;
    public PlanningMenuPanel(ContentPanel parentPanel) {
        this.setSize((int)(parentPanel.getWidth()*0.2),parentPanel.getHeight());
        this.parentPanel = parentPanel;
        init();
    }

    /**
     * Menu for the planning view
     * will allow to create, load and delete teams and games
     */
    private void init(){
        this.setPreferredSize(new Dimension(parentPanel.getSize().width,(int)(parentPanel.getSize().height*0.1)));
        this.setBackground(this.parentPanel.getParent().getBackground());
        this.initComponents();
    }
    private void initComponents(){
        JLabel title = new JLabel("Planning helper");
        JLabel fileName = new JLabel("file_name.csv");
        JLabel saveStatus = new JLabel("- Unsaved");
        HomeButton homeButton = new HomeButton(this);
        homeButton.setBackground(this.getBackground());
        NewFileButton newFileButton = new NewFileButton(this);
        newFileButton.setBackground(this.getBackground());
        OpenFileButton openFileButton = new OpenFileButton(this);
        openFileButton.setBackground(this.getBackground());
        SettingsButton settingsButton = new SettingsButton(this);
        settingsButton.setBackground(this.getBackground());
        JPanel leftmenupanel = new JPanel();
        JPanel rightmenupanel = new JPanel();
        JPanel midMenuPanel = new JPanel();

        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fileName.setFont(new Font("Arial",Font.ITALIC,20));
        fileName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fileName.setVerticalAlignment(JLabel.TOP);
        saveStatus.setFont(new Font("Arial",Font.ITALIC,20));
        saveStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveStatus.setVerticalAlignment(JLabel.TOP);

        GridLayout gridLayout = new GridLayout(1, 3);
        this.setLayout(gridLayout);
        leftmenupanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        rightmenupanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,0));

        homeButton.setPreferredSize(new Dimension(80,80));
        newFileButton.setPreferredSize(new Dimension(80,80));
        openFileButton.setPreferredSize(new Dimension(80,80));
        settingsButton.setPreferredSize(new Dimension(80,80));
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentPanel.getParentFrame().homeView();
            }
        });
        newFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateTournamentFrame(PlanningMenuPanel.this.parentPanel.getParentFrame());
            }
        });
        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(null);
            }
        });

        leftmenupanel.add(title);
        leftmenupanel.add(homeButton);
        leftmenupanel.add(newFileButton);
        leftmenupanel.add(openFileButton);
        rightmenupanel.add(settingsButton);
        midMenuPanel.add(fileName);
        midMenuPanel.add(saveStatus);
        this.add(leftmenupanel);
        this.add(midMenuPanel);
        this.add(rightmenupanel);
    }
}
