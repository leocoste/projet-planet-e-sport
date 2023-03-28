package ui.components.panels.planning;

import entities.Room;
import entities.Team;
import entities.Tournament;
import models.planning.Match;
import models.planning.Planning;
import ui.components.panels.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.sql.Date;

public class PlanningDisplayPanel extends JPanel {
    /**
     * Planning used to display the planning
     */

    private static PlanningDisplayPanel  instance;
    private Planning currentPlanning;
    private List<Team> teams;

    private List<TeamBoxGroup> groups;
    private TeamPlanningBox selectedBox;

    private int groupHeight;
    private int groupWidth;
    public static int lastTeamGroupIndex = 0;

    private final List<JPanel> containers = new ArrayList<>();


    // Gap between each of the planning box
    private final int BOX_GAP = 10;
    public PlanningDisplayPanel(ContentPanel parentPanel){
        this.parentPanel = parentPanel;
    }

    public PlanningDisplayPanel(ContentPanel parentPanel, Planning planning) {
        this.parentPanel = parentPanel;
        this.currentPlanning = planning;
        init();
        instance = this;
    }

    public static Planning createTestPlanning(int nTeams, int startTime, int matchDuration, int breakDuration){
        List<Team> teams = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        for(int i = 1; i <= nTeams; i++){
            teams.add(new Team(i,"Team "+i,0));
        }
        for(int i = 1; i <= 4; i++){
            rooms.add(new Room(i,"Room "+i,20));
        }
        return new Planning(teams,Tournament.GLOBAL,startTime,startTime,startTime,rooms,new Date(System.currentTimeMillis()), matchDuration, breakDuration);
    }

    private void init() {
        //this.setBackground(parentPanel.getBackground());
        this.setSize((int)(parentPanel.getWidth()*0.8),(int)(parentPanel.getHeight()*0.8));
        this.setLayout(new GridLayout(1,nBrackets()));
        this.teams = this.currentPlanning.getTournament().getTeams();
        this.groupHeight = this.getHeight() / (teams.toArray().length /2);
        this.groupWidth = this.getWidth() / this.nBrackets();
        initComponents();
    }

    private void initComponents(){
        // this method generates the components that will be used to display the planning
        System.out.println("init components");
        int nTeams = this.teams.toArray().length;
        int nBrackets = this.nBrackets();
        Random random = new Random(System.currentTimeMillis());
        this.groups = new ArrayList<>();

        // Instantiate the containers
        for(int i = 0; i < nBrackets; i++){
            JPanel panel = new JPanel();
            panel.setBackground(this.getBackground());
            //panel.setBackground(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            this.setSize(panel, this.groupWidth+10,this.getHeight());
            // Set the layout of the panel to have as many lines as there will be matches in the bracket
            panel.setLayout(new GridLayout((int)(Math.round((nTeams / Math.pow(2,i)/2))),1,5,10));
            panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            containers.add(panel);
        }

        TeamBoxGroup boxGroup;
        List<Match> bracket = this.currentPlanning.generateFirstBracket();
        int bracketSize = bracket.toArray().length;
        // Create and place all the groups
        for(int i = 0; i < nBrackets-1; i ++) {
            JPanel bracketPanel = containers.get(i);
            for (int j = 0; j < bracketSize; j++) {
                if(i == 0){
                    // the components of the first bracket will have their teams assigned immediately
                    bracketPanel.add(new TeamBoxGroup(this,bracketPanel,bracket.get(j), this.groupWidth, this.groupHeight, lastTeamGroupIndex++));
                }else{
                    boxGroup = new TeamBoxGroup(this, bracketPanel,bracket.get(j), this.groupWidth, this.groupHeight, lastTeamGroupIndex++);
                    TeamBoxGroup lastGroup = (TeamBoxGroup) containers.get(i-1).getComponent(j*2);
                    lastGroup.setNextGroup(boxGroup);
                    lastGroup = (TeamBoxGroup) containers.get(i-1).getComponent(j*2+1);
                    lastGroup.setNextGroup(boxGroup);
                    bracketPanel.add(boxGroup);
                    this.groups.add(boxGroup);
                }
            }
            this.add(bracketPanel);
            bracket = this.getNextBracket(bracket);
            bracketSize/=2;
        }
        this.add(containers.get(containers.toArray().length-1));
    }

    private List<Match> getNextBracket(List<Match> bracket){
        List<Match> nextBracket = new ArrayList<>();
        for(Match match : bracket){
            if(nextBracket.contains(match)) continue;
            nextBracket.add(match.getNext());
        }
        return nextBracket;
    }

    public static void staticDisplayTournamentWinner(Team winner){
        instance.displayTournamentWinner(winner);
    }

    public void displayTournamentWinner(Team winner){
        TeamPlanningBox box = new TeamPlanningBox(winner,this.groupWidth/2,this.groupHeight);
        JPanel lastContainer = this.containers.get(this.containers.toArray().length-1);
        lastContainer.setLayout(new GridLayout());
        lastContainer.add(box);
    }

    private void setSize(JPanel panel, int w, int h){
        panel.setSize(w,h);
        panel.setPreferredSize(new Dimension(w,h));
        panel.setMaximumSize(new Dimension(w,h));
        panel.setMinimumSize(new Dimension(w,h));
    }

    public int nBrackets(int nTeams){
        int nBrackets = 0;
        while(nTeams > 1 ){
            nTeams /= 2;
            nBrackets++;
        }
        return nBrackets +1;
    }
    public int nBrackets(){
        return this.nBrackets(this.currentPlanning.getTournament().getTeams().toArray().length);
    }

    public ContentPanel getParentPanel() {
        return parentPanel;
    }

    public TeamPlanningBox getSelectedBox() {
        return selectedBox;
    }

    public Planning getCurrentPlanning() {
        return currentPlanning;
    }
    public void setSelectedBox(TeamPlanningBox selectedBox) {
        this.selectedBox = selectedBox;
    }

    public void setCurrentPlanning(Planning currentPlanning) {
        this.currentPlanning = currentPlanning;
    }

    public List<TeamBoxGroup> getGroups() {
        return groups;
    }

    private final ContentPanel parentPanel;

}
