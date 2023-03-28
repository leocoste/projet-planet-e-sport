package ui.components.panels.home;

import ui.components.buttons.home.top.AccountButton;

import javax.swing.*;
import java.awt.*;

public class HomeTopPanel extends JPanel {
    private final HomePanel parentPanel;

    /**
     * Top panel for the home view
     * @param parentPanel :HomePanel
     */

    public HomeTopPanel(HomePanel parentPanel) {
        super();
        this.parentPanel = parentPanel;
        init();
    }

    private void init(){
        this.setSize();
        int padding = 25;
        this.setLayout(new FlowLayout(FlowLayout.LEFT, padding,0));
        this.setBackground(parentPanel.getBackground());
        initComponent();
    }

    private void initComponent(){
        this.accountButton = new AccountButton(this);
        this.add(accountButton);
    }

    public void setSize(){
        this.setSize(new Dimension(this.parentPanel.getWidth(),(int)(this.parentPanel.getHeight()*0.1)));
        this.setMinimumSize(new Dimension(this.parentPanel.getWidth(),(int)(this.parentPanel.getHeight()*0.1)));
        this.setPreferredSize(new Dimension(this.parentPanel.getWidth(),(int)(this.parentPanel.getHeight()*0.1)));
    }

    public HomePanel getParentPanel() {
        return parentPanel;
    }

    public AccountButton getAccountButton() {
        return accountButton;
    }

    private AccountButton accountButton;
}
