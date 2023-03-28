package ui.components.buttons.home.menu;

import ui.components.buttons.CustomButton;

import javax.swing.*;

public class OpenFileButton extends CustomButton {

    public OpenFileButton(JPanel parentPanel) {
        super(parentPanel);
        this.iconFileName = "open.png";
        this.setCustomButtonIcon(this.iconFileName);
    }
    public int getIconSize(){
        return super.getParentPanel().getHeight()/16;
    }

}
