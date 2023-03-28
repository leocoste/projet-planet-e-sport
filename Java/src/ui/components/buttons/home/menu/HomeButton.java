package ui.components.buttons.home.menu;


import ui.components.buttons.CustomButton;

import javax.swing.*;
public class HomeButton extends CustomButton {
    public HomeButton(JPanel parentPanel){
        super(parentPanel);
        this.iconFileName = "home.png";
        this.setCustomButtonIcon(this.iconFileName);
    }

    @Override
    public int getIconSize(){
        return super.getParentPanel().getHeight()/16;
    }
}
