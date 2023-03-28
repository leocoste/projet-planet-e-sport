package ui.components.frames;

import config.Config;
import entities.Team;
import models.planning.Match;
import ui.components.buttons.planning.display.PlanningWinButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MatchScoreFrame extends JDialog {

    /*
    Constructeur de la frame MatchScoreFrame
    */
    public MatchScoreFrame(PlanningWinButton parentPanel,Match match, Team team1, Team team2) {
        super();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setModal(true); //Pour empecher la creation de cette page plusieurs fois
        init(parentPanel,match, team1,team2);
        this.pack();
        this.setVisible(true);
    }

    /*
    Fonction d'initialisationd des éléments de la frame
     */
    private void init(PlanningWinButton parentPanel,Match match,Team team1,Team team2) {
        this.setTitle("Score de match");
        this.setSize(new Dimension(500, 500));
        this.setBackground(AppFrame.BACKGROUND_COLOR);
        this.setResizable(false);
        this.setLocationRelativeTo(parentPanel);

        /*
        Initialisation des panels de la frame
        */
        JPanel matchScorePanel = new JPanel(new GridLayout(3,0));
        JPanel matchScoreTeam1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel matchScoreTeam2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        /*
        Initialisation composants
        */
        JSpinner scoreTeam1Spinner = new JSpinner();
        JSpinner scoreTeam2Spinner = new JSpinner();
        scoreTeam1Spinner.setPreferredSize(new Dimension(50,20));
        scoreTeam2Spinner.setPreferredSize(new Dimension(50,20));
        JButton confirmScoreButton = new JButton("Confirmer le score");

        if(team1.equals(match.getWinner())) scoreTeam1Spinner.setValue(1);
        else scoreTeam2Spinner.setValue(1);
        /*
        Gestion du listener du bouton valider
        */
        confirmScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((int)scoreTeam1Spinner.getValue()==(int)scoreTeam2Spinner.getValue()){
                    JOptionPane.showMessageDialog(null,"Il faut que les scores ne soient pas égaux");
                } else if ((team1.equals(match.getWinner())) && (int)scoreTeam1Spinner.getValue()<(int)scoreTeam2Spinner.getValue() || (team2.equals(match.getWinner())) && (int)scoreTeam1Spinner.getValue()>(int)scoreTeam2Spinner.getValue()) {
                    JOptionPane.showMessageDialog(null,"Les scores ne correspondent pas a l'équipe gagnante");
                } else{
                    ArrayList<Team> teamArrayList = new ArrayList<>();
                    teamArrayList.add(team1);
                    teamArrayList.add(team2);
                    int[] score = new int[2];
                    Match matchResult = new Match(match,teamArrayList,((int)scoreTeam1Spinner.getValue()>(int)scoreTeam2Spinner.getValue()?team1:team2),score);
                    score[0] = (int)scoreTeam1Spinner.getValue();
                    score[1] = (int)scoreTeam2Spinner.getValue();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    dialogButton = JOptionPane.showConfirmDialog(null,"Confirmez vous que l'équipe : " + matchResult.getWinner().getTeamName() + " a gagné " + (score[0]>score[1]?score[0]:score[1]) + " à " + (score[0]<score[1]?score[0]:score[1]),"Confirmation de score", dialogButton);
                    if (dialogButton == JOptionPane.YES_OPTION) {
                        MatchScoreFrame.this.dispose();
                    }
                }
            }
        });

        /*
        Ajout des composants a la frame
         */
        matchScoreTeam1Panel.add(new JLabel("Score de l'équipe " +  team1.getTeamName()));
        matchScoreTeam1Panel.add(scoreTeam1Spinner);
        matchScoreTeam2Panel.add(new JLabel("Score de l'équipe " +  team2.getTeamName()));
        matchScoreTeam2Panel.add(scoreTeam2Spinner);
        matchScorePanel.add(matchScoreTeam1Panel);
        matchScorePanel.add(matchScoreTeam2Panel);
        matchScorePanel.add(confirmScoreButton);
        this.add(matchScorePanel);
    }
}
