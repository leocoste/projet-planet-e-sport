package ui.components.panels.home;

import config.Config;
import ui.components.buttons.*;
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

public class HomeMenuPanel extends JPanel {
    private final static int SEPARATOR_HEIGHT = 2;
    public final static int RIGHT_PADDING = 15;
    // height of the separator between title and home button

    /**
     * Side panel acting as a menu for the Home page
     * @param parentPanel :AppFrame
     */
    public HomeMenuPanel(ContentPanel parentPanel){
        super();
        this.parentPanel = parentPanel;
        init();
    }

    private void init(){
        this.setLayout(new GridLayout(6,1));
        this.setBackground(parentPanel.getBackground());
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,HomeMenuPanel.RIGHT_PADDING));
        initComponents();
    }

    public void setSize(){
        // set the panel's size to be large enough to display de title and never exceed 20% of the frame's width
        this.setMinimumSize(new Dimension(getTitleWidth() + 15,parentPanel.getHeight()));
        this.setPreferredSize(new Dimension(getTitleWidth() + 15,parentPanel.getHeight()));
        this.setMaximumSize(new Dimension((int)(this.parentPanel.getWidth()*0.2), this.parentPanel.getHeight()));
        this.setSize(new Dimension(getTitleWidth() + 15,parentPanel.getHeight()));
    }

    public int getTitleWidth(){
        // returns the width of the text in the title component
        return this.title.getFontMetrics(title.getFont()).stringWidth(title.getText());
    }

    private void initComponents(){
        //title
        this.title = new JLabel(Config.APP_TITLE);
        this.title.setFont(new Font("Arial",Font.BOLD,20));
        this.title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        // Set the size using the title's width
        this.setSize();
        //buttons
        this.homeButton = new HomeButton(this);
        this.newFileButton = new NewFileButton(this);
        this.openFileButton = new OpenFileButton(this);
        this.settingsButton = new SettingsButton(this);
        //adding listeners
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentPanel.getParentFrame().homeView();
            }
        });
        newFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateTournamentFrame(HomeMenuPanel.this.parentPanel.getParentFrame());
            }
        });
        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(null);
            }
        });
        //adding the components
        this.add(title);
        this.add(homeButton);
        this.add(newFileButton);
        this.add(openFileButton);
        this.add(settingsButton);
    }



    /**
     * Applies an Antialiasing effect
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        this.setSize();
        // Apply an Antialiasing effect on the panel
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paintComponent(g);
        // Painting separator between the title and the home button
        paintSeparator(g);
    }

    /**
     * Paints the separator between the title and the buttons
     * @param g :Graphics
     */
    private void paintSeparator(Graphics g){
        JLabel title = this.getTitle();
        CustomButton homeButton = this.getHomeButton();
        g.setColor(parentPanel.getBackground().brighter());

        int textY = title.getY() +title.getHeight()/2 + title.getFont().getSize()/2;
        int gap =  homeButton.getY() - textY;
        int rectY = textY + gap/2 + SEPARATOR_HEIGHT;
        int titleWidth = getTitleWidth();
        int rectX = (this.getWidth() - titleWidth) / 2 ;

        g.fillRoundRect(rectX,rectY , titleWidth ,SEPARATOR_HEIGHT,1,1 );
    }
    public ContentPanel getParentPanel() {
        return this.parentPanel;
    }

    public JLabel getTitle() {
        return title;
    }

    public HomeButton getHomeButton() {
        return homeButton;
    }

    public NewFileButton getNewFileButton() {
        return newFileButton;
    }

    public OpenFileButton getOpenFileButton() {
        return openFileButton;
    }

    public SettingsButton getSettingsButton() {
        return settingsButton;
    }

    private final ContentPanel parentPanel;

    private JLabel title;

    private HomeButton homeButton;
    private NewFileButton newFileButton;
    private OpenFileButton openFileButton;
    private SettingsButton settingsButton;
}
