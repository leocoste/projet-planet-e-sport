package ui.components.buttons.planning.display;

import ui.components.panels.planning.TeamPlanningBox;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlanningCancelButton extends PlanningButton {
    private TeamPlanningBox parentPanel;
    public PlanningCancelButton(TeamPlanningBox parentPanel){
        super(parentPanel, "return.png");
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
