package ui.components.buttons.planning.display;

import entities.Team;
import models.planning.Match;
import ui.components.frames.CreateTournamentFrame;
import ui.components.frames.MatchScoreFrame;
import ui.components.panels.planning.PlanningDisplayPanel;
import ui.components.panels.planning.PlanningMenuPanel;
import ui.components.panels.planning.TeamPlanningBox;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlanningWinButton extends PlanningButton {

    public PlanningWinButton(TeamPlanningBox parentPanel){
        super(parentPanel, "win.png");
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PlanningWinButton.this.getParentPanel().getParentPanel().getMatch().getTeams().toArray().length == 2) {
                    try {
                        PlanningWinButton.this.setWinner();
                        System.out.println(parentPanel.getParentPanel().getMatch());
                    }catch(NullPointerException exc){
                        PlanningDisplayPanel.staticDisplayTournamentWinner(PlanningWinButton.this.getParentPanel().getTeam());
                    }
                    PlanningWinButton.this.getParentPanel().getMainButton().setSelected(false);
                    PlanningWinButton.this.getParentPanel().toggleActionButtons();
                    new MatchScoreFrame(PlanningWinButton.this, parentPanel.getParentPanel().getMatch(),
                            parentPanel.getParentPanel().getMatch().getTeams().get(0), parentPanel.getParentPanel().getMatch().getTeams().get(1));
                }else{
                    JOptionPane.showMessageDialog(PlanningWinButton.this,"Vous devez assigner une équipe de plus au match pour pouvoir le terminer.","Pas assez d'équipes dans le match",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public void setWinner(){
        super.getParentPanel().setWinner();
    }


}
