package ui.components.buttons.home.menu;

import ui.components.buttons.CustomButton;

import javax.swing.*;

public class AddTeamButton extends CustomButton {
    private JPanel parentPanel;

    public AddTeamButton(JPanel parentPanel) {
        super(parentPanel);
        this.iconFileName = "add.png";
        this.setCustomButtonIcon(this.iconFileName);
    }

    public int getIconSize(){
        return super.getParentPanel().getHeight()/40;
    }
}
