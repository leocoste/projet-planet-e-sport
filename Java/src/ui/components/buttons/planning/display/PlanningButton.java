package ui.components.buttons.planning.display;

import ui.components.buttons.CustomButton;
import ui.components.panels.planning.TeamPlanningBox;

public abstract class PlanningButton extends CustomButton {

    public PlanningButton(TeamPlanningBox parentPanel,String fileName){
        super(parentPanel);
        this.iconFileName = fileName;
        this.parentPanel = parentPanel;
        this.setBackground(parentPanel.getParentPanel().getDisplayPanel().getBackground());
        this.setCustomButtonIcon(this.iconFileName);
    }

    @Override
    public int getIconSize(){
        return (int) (super.getParentPanel().getWidth()*0.08);
    }

    @Override
    public TeamPlanningBox getParentPanel() {
        return parentPanel;
    }

    private final TeamPlanningBox parentPanel;
}
