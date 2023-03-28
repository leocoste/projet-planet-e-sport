package ui.components.buttons.home.menu;

import ui.components.buttons.CustomButton;

import javax.swing.*;

public class RemoveTeamButton extends CustomButton {

    private JPanel parentPanel;

    public RemoveTeamButton(JPanel parentPanel) {
        super(parentPanel);
        this.iconFileName = "remove.png";
        this.setCustomButtonIcon(this.iconFileName);
    }

    public int getIconSize(){
        return super.getParentPanel().getHeight()/40;
    }
}
