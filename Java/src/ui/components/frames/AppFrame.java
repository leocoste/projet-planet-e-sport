package ui.components.frames;

import config.Config;
import entities.Team;
import entities.Tournament;
import ui.components.panels.ContentPanel;
import ui.components.panels.home.HomePanel;
import ui.components.panels.planning.PlanningDisplayPanel;
import ui.components.panels.planning.PlanningPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Date;
import java.util.HashMap;

public class AppFrame extends JFrame {
    /**
     * App main frame
     */
    public static final Color BACKGROUND_COLOR = new Color(45,45,45);
    public static final Color FOREGROUND_COLOR = BACKGROUND_COLOR.brighter();
    private PlanningPanel planningPanel;
    public AppFrame(){
        super();
        init();
        this.setVisible(true);
    }
    private void init(){
        this.setTitle(Config.APP_TITLE);
        this.setSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.8)));
        //this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(AppFrame.BACKGROUND_COLOR);
        //this.setBackground(Color.green);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new CardLayout());

        this.planningPanel = new PlanningPanel(this);

        this.getContentPane().add(new HomePanel(this),"home");
        this.getContentPane().add(this.planningPanel,"planning");

        this.homeView();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                //AppFrame.this.contentPanel.updateSize();
            }
        });
    }
    public void homeView(){
        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(),"home");
    }

    public void planningView() {
        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(),"planning");
    }


    public void planningView(Tournament tournament) {
        this.planningPanel.init(tournament);
        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(),"planning");
    }

    public void setContentPanel(ContentPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    private ContentPanel contentPanel;

}
