package ui.components.buttons.home.top;

import ui.components.buttons.CustomButton;
import ui.components.frames.LoginFrame;
import ui.components.panels.home.HomeTopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AccountButton extends CustomButton {
    private boolean isConnected = false;
    public AccountButton(HomeTopPanel parentPanel) {
        super(parentPanel);
        this.parentPanel = parentPanel;
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


    public void setSize(){
        this.setMinimumSize(new Dimension(parentPanel.getWidth()/5,parentPanel.getHeight()));
        this.setSize(new Dimension(parentPanel.getWidth()/5,parentPanel.getHeight()));
        this.setMaximumSize(new Dimension(parentPanel.getWidth()/5,parentPanel.getHeight()));
        this.setPreferredSize(new Dimension(parentPanel.getWidth()/5,parentPanel.getHeight()));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString(buttonText,this.getX()+ this.getIconSize(), (int) (this.getY()+(this.getSize().getHeight()/2)+g.getFontMetrics().getHeight()/2));
    }

    @Override
    public  int getIconSize(){
        return this.getParentPanel().getHeight()/2;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        if(connected){
            this.buttonText = "Logged as : "+userName;
        }else{
            buttonText = "Connect";
        }
        isConnected = connected;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private final HomeTopPanel parentPanel;
    private String buttonText = "Connect";
    private String userName = "user_name;";
}
