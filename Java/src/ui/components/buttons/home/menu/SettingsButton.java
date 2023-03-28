package ui.components.buttons.home.menu;

import ui.components.buttons.CustomButton;

import javax.swing.*;

public class SettingsButton extends CustomButton {
    public SettingsButton(JPanel parentPanel) {
        super(parentPanel);
        this.iconFileName = "settings.png";
        this.setCustomButtonIcon(this.iconFileName);
    }
    public int getIconSize(){
        return super.getParentPanel().getHeight()/16;
    }
}
