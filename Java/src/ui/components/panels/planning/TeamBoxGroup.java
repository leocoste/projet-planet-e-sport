package ui.components.panels.planning;

import entities.Team;
import models.planning.Match;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TeamBoxGroup extends JPanel {
    private Team winner = null;
    private final Match match;
    private final int index;
    private final JPanel parentPanel;
    private final PlanningDisplayPanel displayPanel;

    private final TeamPlanningBox[] boxes = new TeamPlanningBox[2];

    private TeamBoxGroup nextGroup;

    public TeamBoxGroup(PlanningDisplayPanel displayPanel, JPanel parentPanel, int w, int h, int index){
        this(displayPanel,parentPanel,null,w,h,index);
    }

    public TeamBoxGroup(PlanningDisplayPanel displayPanel,JPanel parentPanel, Match match,int w, int h,int index){
        this.parentPanel = parentPanel;
        this.displayPanel = displayPanel;
        this.index = index;
        this.match= match;
        init(w,h);
    }

    public void init(int w, int h){
        this.setSize(w,h);
        //this.setBounds(new Rectangle(w,h));
        this.setBackground(parentPanel.getBackground());
        this.setLayout(new GridLayout(2, 1, 0, 0));
        this.initComponent();
    }

    private void initComponent(){
        for(int i = 0; i < 2; i++){
            JPanel boxPanel = new JPanel();
            boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.X_AXIS));
            boxPanel.setBackground(parentPanel.getBackground());
            boxPanel.setSize(this.getWidth()-10,this.getHeight()/2);
            try {
                this.boxes[i] = new TeamPlanningBox(this, this.match.getTeams().get(i), boxPanel.getWidth(), boxPanel.getHeight());
            }catch(Exception e ){
                this.boxes[i] = new TeamPlanningBox(this, null, boxPanel.getWidth(), boxPanel.getHeight());
            }
            this.boxes[i].setAlignmentY(CENTER_ALIGNMENT);
            boxPanel.add(this.boxes[i]);
            this.add(boxPanel,i);
        }
    }

    public void setWinner(Team winner) {
        this.winner = winner;
        this.match.setWinner(this.winner);
        this.displayPanel.getCurrentPlanning().forward();
        this.nextGroup.addTeam(winner,this.index);
        this.displayPanel.revalidate();
        this.displayPanel.repaint();
    }

    public void addTeam(Team team, int index){
        if(index % 2 == 0 && this.boxes[0].getTeam() == null) this.boxes[0].setTeam(team);
        else if (index % 2 == 1 && this.boxes[1].getTeam() == null) this.boxes[1].setTeam(team);}
    public Team getWinner() {
        return winner;
    }

    public int getIndex() {
        return index;
    }

    public Match getMatch() {
        return match;
    }

    public PlanningDisplayPanel getDisplayPanel(){
        return this.displayPanel;
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    public TeamBoxGroup getNextGroup() {
        return nextGroup;
    }

    public void setNextGroup(TeamBoxGroup nextGroup) {
        this.nextGroup = nextGroup;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamBoxGroup that = (TeamBoxGroup) o;
        return index == that.index && Objects.equals(parentPanel, that.parentPanel) && Objects.equals(displayPanel, that.displayPanel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, parentPanel, displayPanel)+98745;
    }
}
