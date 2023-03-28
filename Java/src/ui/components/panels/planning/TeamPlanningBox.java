package ui.components.panels.planning;

import entities.Team;
import ui.components.buttons.planning.display.PlanningCancelButton;
import ui.components.buttons.planning.display.PlanningWinButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Random;

public class TeamPlanningBox extends JPanel{
    private final TeamBoxGroup parentPanel;
    private JPanel actionButtonPanel;
    private Team team;

    /**
     * Overload of the constructor to display the winning team
     * DO NOT use this constructor outside this use case
     * @param team : team winning the tournament
     * @param width
     * @param height
     */
    TeamPlanningBox(Team team, int width, int height){
        super();
        //this constructor is used when placing the last team at the end of the tournament
        this.parentPanel = null;
        this.setSize(width,height);
        JButton fakeButton = new JButton(team.getTeamName());
        fakeButton.setEnabled(false);
        fakeButton.setAlignmentY(CENTER_ALIGNMENT);
        this.init(width,height);
        this.add(fakeButton);
        fakeButton.setBounds(new Rectangle(width,height));
    }

    public TeamPlanningBox(TeamBoxGroup parentPanel, Team team, int width, int height){
        this.parentPanel = parentPanel;
        this.team = team;
        this.init(width,height);
        this.initComponents();
    }

    private void init(int width, int height){
        try {
            this.setBackground(this.parentPanel.getParentPanel().getBackground());
            Random random = new Random(this.parentPanel.getIndex());
        }catch(NullPointerException ignored){}
        //this.setBackground(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setBounds(new Rectangle(width, height));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new BorderLayout(5,0));
    }

    public void initComponents(){
        // Instantiate the action buttons
        PlanningWinButton winButton = new PlanningWinButton(this);
        PlanningCancelButton cancelButton = new PlanningCancelButton(this);
        // Instantiate the container panel
        this.actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        // Add the buttons to the container
        actionButtonPanel.add(cancelButton);
        actionButtonPanel.add(winButton);
        this.actionButtonPanel.setSize((int) (this.getWidth() * 0.2), this.getHeight());
        winButton.setSize((int) (this.getWidth() * 0.1), this.getHeight());
        cancelButton.setSize((int) (this.getWidth() * 0.1), this.getHeight());
        // Set the panel to be invisible by default
        this.actionButtonPanel.setVisible(false);
        // Add the container to this component
        this.add(this.actionButtonPanel, BorderLayout.EAST);
        // Instantiate the main button
        try{
            this.mainButton = new JToggleButton(this.team.getTeamName());
            initMainButton();
        }
        catch (NullPointerException e){
            this.mainButton = new JToggleButton();
            this.mainButton.setVisible(false);
        }
        this.add(this.mainButton, BorderLayout.CENTER);
    }

    private void initMainButton(){
        this.mainButton.setBounds(new Rectangle((int) (this.getWidth() * 0.8), this.getHeight()));
        this.mainButton.setSize((int) (this.getWidth() * 0.8), this.getHeight());
        this.mainButton.setPreferredSize(new Dimension((int) (this.getWidth() * 0.8), this.getHeight()));
        this.mainButton.setMinimumSize(new Dimension((int) (this.getWidth() * 0.8), this.getHeight()));

        mainButton.setMaximumSize(new Dimension(50,this.getHeight()));
        this.mainButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TeamPlanningBox.this.parentPanel.getWinner() == null) {
                    try {
                        TeamPlanningBox.this.parentPanel.getDisplayPanel().getSelectedBox().getMainButton().setSelected(false);
                    } catch (NullPointerException ignored) {
                    }
                    TeamPlanningBox.this.toggleActionButtons();
                    TeamPlanningBox.this.parentPanel.getDisplayPanel().setSelectedBox(TeamPlanningBox.this);
                }
            }});

    }

    private void reset(){
        for(Component component : this.getComponents()){
            this.remove(component);
        }
    }

    public void toggleActionButtons(){
        this.actionButtonPanel.setVisible(this.mainButton.isSelected());
    }

    public TeamBoxGroup getParentPanel() {
        return parentPanel;
    }

    public Team getTeam() {
        return team;
    }

    public void setWinner() {
        parentPanel.setWinner(this.team);
    }

    public void setTeam(Team team){
        this.team = team;
        this.mainButton.setVisible(true);
        this.initMainButton();
        this.mainButton.setText(this.team.getTeamName());
        this.repaint();
    }

    public JToggleButton getMainButton() {
        return mainButton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamPlanningBox that = (TeamPlanningBox) o;
        return Objects.equals(parentPanel, that.parentPanel) && Objects.equals(actionButtonPanel, that.actionButtonPanel) && Objects.equals(team, that.team) && Objects.equals(mainButton, that.mainButton);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentPanel, actionButtonPanel, team, mainButton)+985;
    }

    private JToggleButton mainButton;


}
