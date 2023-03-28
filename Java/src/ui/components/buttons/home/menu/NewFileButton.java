package ui.components.buttons.home.menu;

import ui.components.buttons.CustomButton;

import javax.swing.*;

public class NewFileButton extends CustomButton {
    private JPanel parentPanel;

    public NewFileButton(JPanel parentPanel) {
        super(parentPanel);
        this.iconFileName = "new.png";
        this.setCustomButtonIcon(this.iconFileName);
    }
    public int getIconSize(){
        return super.getParentPanel().getHeight()/16;
    }
}
