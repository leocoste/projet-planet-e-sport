package ui.components.panels.planning;

import entities.Tournament;
import jdk.jshell.spi.ExecutionControlProvider;
import ui.components.frames.AppFrame;
import ui.components.panels.ContentPanel;

import java.awt.*;

public class PlanningPanel extends ContentPanel {
    /**
     * Container panel contains PlanningDrawPanel, PlanningMenuPanel
     * @param parentFrame :AppFrame
     */
    private PlanningDisplayPanel planningDisplayPanel;
    public PlanningPanel(AppFrame parentFrame){
        super(parentFrame);
    }
    public void reset(){
        try{
            for(Component component : this.getComponents()){
                this.remove(component);
            }
        }catch(NullPointerException ignored){}
    }
    public void init(Tournament tournament){
        this.reset();
        this.setLayout(new BorderLayout(15,15));
        this.setBackground(getParentFrame().getBackground());
        initComponent(tournament);
    }

    private void initComponent(Tournament tournament){
        this.planningDisplayPanel = new PlanningDisplayPanel(this);
        PlanningMenuPanel planningMenuPanel = new PlanningMenuPanel(this);
        PlanningToolsPanel planningToolsPanel = new PlanningToolsPanel(this,tournament);

        planningMenuPanel.setSize(this.getWidth(),(int)(this.getHeight()*0.2));
        planningToolsPanel.setSize((int) (this.getWidth()*0.2),this.getHeight());

        this.add(planningDisplayPanel, BorderLayout.CENTER);
        this.add(planningMenuPanel, BorderLayout.NORTH);
        this.add(planningToolsPanel, BorderLayout.WEST);
    }

    public void setPlanningDisplayPanel(PlanningDisplayPanel planningDisplayPanel) {
        this.remove(this.planningDisplayPanel);
        this.planningDisplayPanel = planningDisplayPanel;
        this.add(planningDisplayPanel, BorderLayout.CENTER) ;
    }

    public PlanningDisplayPanel getPlanningDisplayPanel() {
        return planningDisplayPanel;
    }

    @Override
    public void savePlanning(String path) {

    }

    @Override
    public void adaptComponentsSize() {
    }

}
