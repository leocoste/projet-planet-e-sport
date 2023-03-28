package ui.components.panels.planning;

import entities.Team;
import entities.Tournament;
import models.planning.Planning;
import ui.components.buttons.home.menu.AddTeamButton;
import ui.components.buttons.home.menu.RemoveTeamButton;
import ui.components.panels.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.shuffle;

public class PlanningToolsPanel extends JPanel {
    public PlanningToolsPanel(ContentPanel parentPanel, Tournament tournament) {
        this.parentPanel = parentPanel;
        init(tournament);
    }

    /**
     * Planning used as container for the planning actions
     * Actions : Create and delete teams, generate first bracket
     * displays team list & file name
     *
     */
    private void init(Tournament tournament){
        this.setBackground(this.parentPanel.getParent().getBackground());
        this.setPreferredSize(new Dimension((int)(parentPanel.getSize().width*0.2), parentPanel.getSize().height));
        this.setSize();
        this.initComponents(tournament);

    }
    public void setSize(){
        this.setSize((int)(parentPanel.getWidth()*0.2),parentPanel.getHeight());
    }

    private void initComponents(Tournament tournament){
        JLabel fileName = new JLabel("file_name.csv");
        AddTeamButton addTeamButton = new AddTeamButton(parentPanel);
        RemoveTeamButton removeTeamButton = new RemoveTeamButton(parentPanel);
        DefaultListModel listModel = new DefaultListModel<>();
        JList teamslist = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(teamslist);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel addRemovePanel = new JPanel();
        JButton generateButton = new JButton("Generate");
        JButton shuffleButton = new JButton("Mélanger");

        ArrayList<String> toAddList = new ArrayList<>();

        toAddList.add("Solary");
        toAddList.add("Lunary");
        toAddList.add("Kcorp");
        toAddList.add("Vitallity");
        toAddList.add("Missfits");
        toAddList.add("Mandatory");
        toAddList.add("BDS");
        toAddList.add("BIG");
        toAddList.add("emuLate");
        toAddList.add("Angel's Wing");
        toAddList.add("LDLC OL");
        toAddList.add("G2 E-sport");
        toAddList.add("AS monaco E-sports");
        toAddList.add("PSG E-sport");
        toAddList.add("TeamBonus1");
        toAddList.add("TeamBonus2");
        toAddList.add("TeamBonus3");
        toAddList.add("TeamBonus4");
        toAddList.add("TeamBonus5");
        toAddList.add("TeamBonus6");
        toAddList.add("TeamBonus7");
        toAddList.add("TeamBonus8");
        toAddList.add("TeamBonus9");
        toAddList.add("TeamBonus10");
        toAddList.add("TeamBonus11");
        toAddList.add("TeamBonus12");
        toAddList.add("TeamBonus13");
        toAddList.add("TeamBonus14");
        toAddList.add("TeamBonus15");
        toAddList.add("TeamBonus16");
        toAddList.add("TeamBonus17");
        toAddList.add("TeamBonus18");
        toAddList.add("TeamBonus19");
        toAddList.add("TeamBonus20");

        DefaultComboBoxModel addCBBmodel = new DefaultComboBoxModel<>();
        for (String elements:toAddList) {
            addCBBmodel.addElement(elements);
        }
        DefaultComboBoxModel removeCBBmodel = new DefaultComboBoxModel<>();
        JComboBox addTeamCBB = new JComboBox(addCBBmodel);
        JComboBox removeTeamCBB = new JComboBox(removeCBBmodel);

        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,30));
        addRemovePanel.setLayout(new FlowLayout());

        addTeamCBB.setPreferredSize(new Dimension((int)(parentPanel.getSize().width*0.12),20));
        removeTeamCBB.setPreferredSize(new Dimension((int)(parentPanel.getSize().width*0.12),20));
        addTeamCBB.setBackground(Color.white.darker());
        removeTeamCBB.setBackground(Color.white.darker());
        addTeamCBB.setForeground(Color.black);
        removeTeamCBB.setForeground(Color.black);
        addTeamButton.setPreferredSize(new Dimension(20,20));
        removeTeamButton.setPreferredSize(new Dimension(20,20));
        teamslist.setForeground(Color.black);
        teamslist.setPreferredSize(new Dimension((int)(parentPanel.getSize().width*0.12 + 25),(int)(parentPanel.getSize().height)));
        scrollPane.setPreferredSize(new Dimension((int)(parentPanel.getSize().width*0.12 + 25),(int)(parentPanel.getSize().height*0.3)));
        teamslist.setBackground(Color.white.darker());
        generateButton.setPreferredSize(new Dimension((int)(parentPanel.getSize().width*0.12 + 20),20));
        generateButton.setBackground(Color.white.darker());
        generateButton.setForeground(Color.black);
        generateButton.setFont(new Font("Arial", Font.PLAIN,15));

        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addTeamCBB.getSelectedItem() == ""){

                }
                else if(tournament.getNTeams()>listModel.getSize()){
                    Object toAddTeam = addTeamCBB.getSelectedItem();
                    if(toAddTeam != null){
                        listModel.addElement((String) toAddTeam);
                        teamslist.setModel(listModel);
                        addCBBmodel.removeElement(toAddTeam);
                        addTeamCBB.setModel(addCBBmodel);
                        removeCBBmodel.addElement(toAddTeam);
                        removeTeamCBB.setModel(removeCBBmodel);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Trop d'équipes, veuillez en supprimer pour pouvoir en ajouter une nouvelle");
                }
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tournament.getNTeams()==listModel.getSize()) {
                    //PlanningDisplayPanel.createTestPlanning(tournament.getNTeams(),tournament.getStartTime(),tournament.getMatchDuration(),tournament.getBreakDuration())
                    List<Team> teams = new ArrayList<>();
                    ListModel<String> model = teamslist.getModel();
                    int size = model.getSize();
                    for (int i = 0; i < size; i++) {
                        String item = model.getElementAt(i);
                        teams.add(new Team(i+1,item,0));
                        // do something with the item
                    }
                    //teams.add(new Team(i++,elements,0));
                    PlanningDisplayPanel planningDisplayPanel = new PlanningDisplayPanel(parentPanel,new Planning(teams,tournament.getType(), tournament.getStartTime(),tournament.getStartTime(),tournament.getStartTime(),tournament.getRooms(),tournament.getBeginDate(),tournament.getMatchDuration(),tournament.getBreakDuration()));
                    PlanningPanel pdp = (PlanningPanel) parentPanel;
                    pdp.setPlanningDisplayPanel(planningDisplayPanel);
                    //parentPanel.add(planningDisplayPanel, BorderLayout.CENTER);
                    generateButton.setEnabled(false);
                    parentPanel.revalidate();
                    parentPanel.repaint();
                }else JOptionPane.showMessageDialog(null, "Pas assez d'équipes pour générer un tournoi, veuillez ajouter "+(tournament.getNTeams()-listModel.getSize())+" équipes de plus");
            }
        });

        removeTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(removeTeamCBB.getSelectedItem() == ""){

                }
                else{
                    Object toRemoveTeam = removeTeamCBB.getSelectedItem();
                    if(toRemoveTeam != null){
                        listModel.removeElement((String) toRemoveTeam);
                        teamslist.setModel(listModel);
                        addCBBmodel.addElement(toRemoveTeam);
                        addTeamCBB.setModel(addCBBmodel);
                        removeCBBmodel.removeElement(toRemoveTeam);
                        removeTeamCBB.setModel(removeCBBmodel);
                    }
                }
            }
        });

        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> teams = new ArrayList<>();
                ListModel<String> model = teamslist.getModel();
                int size = model.getSize();
                for (int i = 0; i < size; i++) {
                    String item = model.getElementAt(i);
                    teams.add(item);
                    // do something with the item
                }
                shuffle(teams);
                listModel.clear();
                for (String elements:teams) {
                    listModel.addElement(elements);
                }
                teamslist.setModel(listModel);
            }
        });

        fileName.setFont(new Font("Arial",Font.ITALIC,20));
        fileName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fileName.setVerticalAlignment(JLabel.TOP);

        this.add(fileName);
        this.add(addTeamCBB);
        this.add(addTeamButton);
        this.add(removeTeamCBB);
        this.add(removeTeamButton);
        this.add(scrollPane);
        this.add(shuffleButton);
        this.add(generateButton);
    }

    public ContentPanel getParentPanel() {
        return parentPanel;
    }

    private final ContentPanel parentPanel;

}
