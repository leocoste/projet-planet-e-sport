package ui.components.buttons.planning.tools;

import ui.components.buttons.CustomButton;
import ui.components.frames.LoginFrame;
import ui.components.panels.ContentPanel;
import ui.components.panels.planning.PlanningToolsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewTeamButton extends CustomButton {

    public NewTeamButton(PlanningToolsPanel parentPanel, ContentPanel parentPanel1) {
        super(parentPanel);
        this.iconFileName = "user.png";
        this.setCustomButtonIcon(this.iconFileName);
        this.setHorizontalAlignment(LEFT);
        this.setVerticalAlignment(CENTER);
        this.setSize();
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(parentPanel.getParentPanel().getParentFrame()).setVisible(true);
            }
        });
    }

    private void setSize(){

    }

}
