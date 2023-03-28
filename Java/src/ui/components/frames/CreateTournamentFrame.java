package ui.components.frames;

import config.Config;
import entities.Tournament;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.sql.Date;

public class CreateTournamentFrame extends JDialog {

    /*
    Constructeur de la frame CreateTournamentFrame
     */
    public CreateTournamentFrame(AppFrame parentFrame) {
        super();
        this.parentFrame = parentFrame;
        this.setModal(true); //Pour empecher la creation de cette page plusieurs fois
        init();
        this.pack();
        this.setVisible(true);
    }

    /*
    Fonction d'initialisationd des éléments de la frame
     */
    private void init() {

        this.setTitle(Config.APP_TITLE);
        this.setSize(new Dimension(400, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.4)));
        this.setBackground(AppFrame.BACKGROUND_COLOR);
        this.setResizable(false);

        //instanciation de panel principal et de son GridBagLayout
        GridBagLayout gridBagLayout = new GridBagLayout();
        JPanel globalPanel = new JPanel(gridBagLayout);
        globalPanel.setPreferredSize(new Dimension(400, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8)));

        /*
        Instanciation des panels représentant les différentes catégories du panel principal
         */
        JPanel teamNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel tournamentDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel matchHourPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel gamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel tournamentPlacePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel castPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        /*
        Instanciation des composants du panel du nombre de Teams
        */
        JComboBox teamNumberComboBox = new JComboBox(teamList);
        teamNumberComboBox.setSelectedIndex(3);
        teamNumberPanel.add(teamNumberComboBox);
        teamNumberPanel.add(new JLabel("Nombre d'équipes participantes"));

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weighty = 50;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        globalPanel.add(teamNumberPanel, gridBagConstraints);//Ajout du panel du nombre de teams au panel global

        /*
        instantiation des JSpinner du choix de date
         */
        JSpinner yearSpinner = new JSpinner();
        yearSpinner.setValue(java.time.LocalDate.now().getYear());
        JSpinner monthSpinner = new JSpinner();
        monthSpinner.setValue(java.time.LocalDate.now().getMonthValue());
        JSpinner daySpinner = new JSpinner();
        daySpinner.setValue(java.time.LocalDate.now().getDayOfMonth());

        /*
        Ajout des composants du tournamentDatePanel dans celui-ci
         */
        tournamentDatePanel.add(new JLabel("DD:"));
        tournamentDatePanel.add(daySpinner);
        tournamentDatePanel.add(new JLabel("MM:"));
        tournamentDatePanel.add(monthSpinner);
        tournamentDatePanel.add(new JLabel("YYY:"));
        tournamentDatePanel.add(yearSpinner);
        tournamentDatePanel.add(new JLabel("Début du tournoi"));

        /*
        Creation d'un listener vérifiant que l'année choisie n'est pas déja passée
         */
        yearSpinner.addChangeListener(e -> {
            if((int)yearSpinner.getValue()<java.time.LocalDate.now().getYear()){
                yearSpinner.setValue(java.time.LocalDate.now().getYear());
            }
            if((int)yearSpinner.getValue()==java.time.LocalDate.now().getYear()){
                if((int)monthSpinner.getValue()<java.time.LocalDate.now().getMonthValue())monthSpinner.setValue(java.time.LocalDate.now().getMonthValue());
            }
            if((int)yearSpinner.getValue()==java.time.LocalDate.now().getYear()){
                if((int)monthSpinner.getValue()==java.time.LocalDate.now().getMonthValue()){
                    if((int)daySpinner.getValue()<java.time.LocalDate.now().getDayOfMonth())daySpinner.setValue(java.time.LocalDate.now().getDayOfMonth());
                }
            }
        });

        /*
        Creation d'un listener vérifiant que le mois choisi n'est pas déja passée
         */
        monthSpinner.addChangeListener(e -> {
            if((int)yearSpinner.getValue()<java.time.LocalDate.now().getYear()){
                yearSpinner.setValue(java.time.LocalDate.now().getYear());
            }
            if((int)yearSpinner.getValue()==java.time.LocalDate.now().getYear()){
                if((int)monthSpinner.getValue()<java.time.LocalDate.now().getMonthValue())monthSpinner.setValue(java.time.LocalDate.now().getMonthValue());
            }
            if((int)yearSpinner.getValue()==java.time.LocalDate.now().getYear()){
                if((int)monthSpinner.getValue()==java.time.LocalDate.now().getMonthValue()){
                    if((int)daySpinner.getValue()<java.time.LocalDate.now().getDayOfMonth())daySpinner.setValue(java.time.LocalDate.now().getDayOfMonth());
                }
            }
            if((int)monthSpinner.getValue()<1)monthSpinner.setValue(1);
            if((int)monthSpinner.getValue()>12)monthSpinner.setValue(12);
        });

        /*
        Creation d'un listener vérifiant que le jour choisi n'est pas déja passée
         */
        daySpinner.addChangeListener(e -> {
            if((int)yearSpinner.getValue()<java.time.LocalDate.now().getYear()){
                yearSpinner.setValue(java.time.LocalDate.now().getYear());
            }
            if((int)yearSpinner.getValue()==java.time.LocalDate.now().getYear()){
                if((int)monthSpinner.getValue()<java.time.LocalDate.now().getMonthValue())monthSpinner.setValue(java.time.LocalDate.now().getMonthValue());
            }
            if((int)yearSpinner.getValue()==java.time.LocalDate.now().getYear()){
                if((int)monthSpinner.getValue()==java.time.LocalDate.now().getMonthValue()){
                    if((int)daySpinner.getValue()<java.time.LocalDate.now().getDayOfMonth())daySpinner.setValue(java.time.LocalDate.now().getDayOfMonth());
                }
            }
            if((int)daySpinner.getValue()<1) daySpinner.setValue(1);
            if((int)daySpinner.getValue()>31) daySpinner.setValue(31);
        });

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        globalPanel.add(tournamentDatePanel, gridBagConstraints);//Ajout du panel de choix de la date du début du tournoi au panel global

        /*
        Instanciation des elements du panel d'heure de début des matchs
         */
        this.matchHourComboBox = new JComboBox(hourList);
        matchHourPanel.add(matchHourComboBox);
        matchHourPanel.add(new JLabel("Horaire préférentiel des matchs"));

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 1;
        globalPanel.add(matchHourPanel, gridBagConstraints);//Ajout du panel d'heure des matchs au panel global

        /*
        Instanciation des sous catégories avec :
            gameIntoListPanel correspondant au fait que le jeu soit dans la base de données.
            otherGameInputPanel correspondant au fait que le jeu ne soit pas dans la base de données, l'utilisateur peut donc l'entrer lui meme.
         */
        gamePanel.setLayout(new GridLayout(2, 0));
        JPanel gameIntoListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel otherGameInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        /*
        Instanciation et gestion des radioButtons de choix de jeu
         */
        ButtonGroup gameListRadioButtonGroup = new ButtonGroup();
        JRadioButton gameIntoListRadioButton = new JRadioButton();
        JRadioButton otherGameRadioButton = new JRadioButton();
        gameListRadioButtonGroup.add(gameIntoListRadioButton);
        gameListRadioButtonGroup.add(otherGameRadioButton);

        /*
        Creation d'une ArrayList qui comprendra tous les jeux récupérés en base de données
         */
        ArrayList<String> listGame = new ArrayList<String>();
        listGame.add("None");
        listGame.add("Call of Duty Black ops 3");
        listGame.add("Trackmania");
        listGame.add("Overwatch");
        listGame.add("Valorant");
        listGame.add("Rocket league");
        listGame.add("Pokemon");
        listGame.add("Hearthstone");
        listGame.add("Forza");


        /*
        try {
        Statement stmt = conn.createStatement();
        String sql = "SELECT gameName FROM Game";
        ResultSet res = stmt.executeQuery(sql);

        while(res.next()){
            listGame.add(res.getString("gameName"));
        }
        }catch (Exception e){
            System.out.println(e);
        }
        */

        /*
        Instanciation des champs de choix de jeu
         */
        JComboBox gameListComboBox = new JComboBox(listGame.toArray());
        JTextField otherGameInputTextField = new JTextField();

        otherGameInputTextField.setEnabled(false);
        gameListComboBox.setEnabled(false);

        /*
        Mise en place de listener pour la gestion de la désactivation des champs non sélectionnés
         */
        gameIntoListRadioButton.addActionListener(e -> {
            if (gameIntoListRadioButton.isSelected()) {
                otherGameInputTextField.setEnabled(false);
                gameListComboBox.setEnabled(true);
            } else if (otherGameRadioButton.isSelected()) {
                otherGameInputTextField.setEnabled(true);
                gameListComboBox.setEnabled(false);
            }
        });
        otherGameRadioButton.addActionListener(e -> {
            if (gameIntoListRadioButton.isSelected()) {
                otherGameInputTextField.setEnabled(false);
                gameListComboBox.setEnabled(true);
            } else if (otherGameRadioButton.isSelected()) {
                otherGameInputTextField.setEnabled(true);
                gameListComboBox.setEnabled(false);
            }
        });

        /*
        Ajout des elements au panel de choix du jeu
         */
        gameIntoListPanel.add(gameIntoListRadioButton);
        otherGameInputPanel.add(otherGameRadioButton);
        gameIntoListPanel.add(gameListComboBox);
        gameIntoListPanel.add(new JLabel("Nom du jeu"));
        otherGameInputPanel.add(otherGameInputTextField);
        otherGameInputPanel.add(new JLabel("Autre jeu"));
        gamePanel.add(gameIntoListPanel);
        gamePanel.add(otherGameInputPanel);

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        globalPanel.add(gamePanel, gridBagConstraints);//Ajout du panel de choix du jeu au panel global

        /*
        Instanciation des panels de gestion de durée des matchs et des pauses
         */
        JPanel durationPanel = new JPanel(new GridLayout(2, 0));
        JPanel matchDurationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel breakDurationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        /*
        Instanciation des éléments de durée des matchs
         */
        JSpinner matchDurationSpiner = new JSpinner();
        matchDurationSpiner.setValue(40);
        matchDurationSpiner.addChangeListener(e -> {
            if((int)matchDurationSpiner.getValue()<0)matchDurationSpiner.setValue(0);//Gestion de la valeur minimum possible
        });
        matchDurationPanel.add(matchDurationSpiner);
        matchDurationPanel.add(new JLabel("Durée des matchs en minutes"));

        /*
        Instanciation des éléments de durée des pauses
         */
        JSpinner breakDurationSpiner = new JSpinner();
        breakDurationSpiner.setValue(15);
        breakDurationSpiner.addChangeListener(e -> {
            if((int)breakDurationSpiner.getValue()<0)breakDurationSpiner.setValue(0);//Gestion de la valeur minimum possible
        });
        breakDurationPanel.add(breakDurationSpiner);
        breakDurationPanel.add(new JLabel("Durée des pauses en minutes"));

        /*
        Ajout des elements au panel de gestion des durées
         */
        durationPanel.add(matchDurationPanel);
        durationPanel.add(breakDurationPanel);

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        globalPanel.add(durationPanel, gridBagConstraints);//Ajout du panel de durées des matchs et des pauses au panel global

        /*
        Instanciation des panels de portée du tournoi
         */
        tournamentPlacePanel.setLayout(new GridLayout(4, 0));
        JPanel localTournamentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel roomNumberLocalTournamentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel distancialTournamentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel tournamentScopePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//tournamentScope = portée du tournoi

        /*
        Gestion des radio buttons sur le lieu du tournoi
         */
        ButtonGroup tournamentPlaceRadioButtonGroup = new ButtonGroup();
        JRadioButton localTournamentRadioButton = new JRadioButton();
        JRadioButton distancialTournamentRadioButton = new JRadioButton();
        tournamentPlaceRadioButtonGroup.add(localTournamentRadioButton);
        tournamentPlaceRadioButtonGroup.add(distancialTournamentRadioButton);

        /*
        Gestion des radio buttons sur la portée du tournoi
         */
        ButtonGroup tournamentScopeRadioButtonGroup = new ButtonGroup();
        JRadioButton globalTournamentRadioButton = new JRadioButton();
        JRadioButton regionalTournamentRadioButton = new JRadioButton();
        tournamentScopeRadioButtonGroup.add(globalTournamentRadioButton);
        tournamentScopeRadioButtonGroup.add(regionalTournamentRadioButton);

        /*
        Instanciation des éléments du panel de portée du tournoi
         */
        JTextField tournamentPlaceTextField = new JTextField();
        JSpinner NumberRoomSpinner = new JSpinner();
        tournamentPlaceTextField.setEnabled(false);
        NumberRoomSpinner.setEnabled(false);

        NumberRoomSpinner.addChangeListener(e -> {
            if((int)NumberRoomSpinner.getValue()<1) NumberRoomSpinner.setValue((localTournamentRadioButton.isSelected()?1:0));//Gestion de la valeur minimum possible
        });

        /*
        Ajout de listener pour gérer la désactivation des champs non sélectionnés
         */
        localTournamentRadioButton.addActionListener(e -> {
            if (localTournamentRadioButton.isSelected()) {
                tournamentPlaceTextField.setEnabled(true);
                NumberRoomSpinner.setEnabled(true);
                NumberRoomSpinner.setValue(1);
            } else if (distancialTournamentRadioButton.isSelected()) {
                tournamentPlaceTextField.setEnabled(false);
                NumberRoomSpinner.setEnabled(false);
                NumberRoomSpinner.setValue(0);
            }
        });
        distancialTournamentRadioButton.addActionListener(e -> {
            if (localTournamentRadioButton.isSelected()) {
                tournamentPlaceTextField.setEnabled(true);
                NumberRoomSpinner.setEnabled(true);
                NumberRoomSpinner.setValue(1);
            } else if (distancialTournamentRadioButton.isSelected()) {
                tournamentPlaceTextField.setEnabled(false);
                NumberRoomSpinner.setEnabled(false);
                NumberRoomSpinner.setValue(0);
            }
        });

        /*
        Ajout des différents composants au panel
         */
        localTournamentPanel.add(localTournamentRadioButton);
        localTournamentPanel.add(tournamentPlaceTextField);
        localTournamentPanel.add(new JLabel("Local : nom du lieu"));
        roomNumberLocalTournamentPanel.add(NumberRoomSpinner);
        roomNumberLocalTournamentPanel.add(new JLabel("Nombre de salles du lieu"));
        distancialTournamentPanel.add(distancialTournamentRadioButton);
        distancialTournamentPanel.add(new JLabel("Distanciel"));

        tournamentScopePanel.add(regionalTournamentRadioButton);
        tournamentScopePanel.add(new JLabel("portée regionale"));
        tournamentScopePanel.add(globalTournamentRadioButton);
        tournamentScopePanel.add(new JLabel("portée globale"));

        tournamentPlacePanel.add(tournamentScopePanel);
        tournamentPlacePanel.add(localTournamentPanel);
        tournamentPlacePanel.add(roomNumberLocalTournamentPanel);
        tournamentPlacePanel.add(distancialTournamentPanel);

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 4;
        globalPanel.add(tournamentPlacePanel, gridBagConstraints);//Ajout du panel de lieu au panel global

        /*
        Instanciation des sous catégories avec :
            castIntoListPanel correspondant au fait que le commentateur soit dans la base de données.
            otherCastInputPanel correspondant au fait que le commentateur ne soit pas dans la base de données, l'utilisateur peut donc l'entrer lui meme.
            otherCastLinkInputPanel correspond au lien su live du caster si il est entré par l'utilisateur (optionnel)
         */
        castPanel.setLayout(new GridLayout(3, 0));
        JPanel castIntoListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel otherCastInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel otherCastLinkInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        /*
        Gestion des radio button de sélection du commentateur
         */
        ButtonGroup castListRadioButtonGroup = new ButtonGroup();
        JRadioButton castIntoListRadioButton = new JRadioButton();
        JRadioButton otherCastRadioButton = new JRadioButton();
        castListRadioButtonGroup.add(castIntoListRadioButton);
        castListRadioButtonGroup.add(otherCastRadioButton);

        /*
        Creation d'une ArrayList qui comprendra tous les commentateurs récupérés en base de données
         */
        ArrayList<String> listCast = new ArrayList<String>();
        listCast.add("None");
        listCast.add("Zerator");
        listCast.add("Lutti");
        listCast.add("AlphaCast");
        listCast.add("Wirtual");
        listCast.add("Kameto");
        listCast.add("Doigby");

        /*
        Instanciation des composants du panel commentateur
         */
        JComboBox castListComboBox = new JComboBox(listCast.toArray());
        JTextField otherCastInputTextField = new JTextField();
        JTextField otherCastLinkInputTextField = new JTextField();
        castListComboBox.setEnabled(false);
        otherCastInputTextField.setEnabled(false);
        otherCastLinkInputTextField.setEnabled(false);

        /*
        Ajout de listener pour gérer la désactivation des champs non sélectionnés
         */
        castIntoListRadioButton.addActionListener(e -> {
            if (castIntoListRadioButton.isSelected()) {
                castListComboBox.setEnabled(true);
                otherCastInputTextField.setEnabled(false);
                otherCastLinkInputTextField.setEnabled(false);
            } else if (otherCastRadioButton.isSelected()) {
                castListComboBox.setEnabled(false);
                otherCastInputTextField.setEnabled(true);
                otherCastLinkInputTextField.setEnabled(true);
            }
        });

        otherCastRadioButton.addActionListener(e -> {
            if (castIntoListRadioButton.isSelected()) {
                castListComboBox.setEnabled(true);
                otherCastInputTextField.setEnabled(false);
                otherCastLinkInputTextField.setEnabled(false);
            } else if (otherCastRadioButton.isSelected()) {
                castListComboBox.setEnabled(false);
                otherCastInputTextField.setEnabled(true);
                otherCastLinkInputTextField.setEnabled(true);
            }
        });

        /*
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT casterName FROM Caster";
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                listCast.add(res.getString("casterName"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
         */

        /*
        Ajout des composants au panel commentateur
         */
        castIntoListPanel.add(castIntoListRadioButton);
        otherCastInputPanel.add(otherCastRadioButton);
        castIntoListPanel.add(castListComboBox);
        castIntoListPanel.add(new JLabel("Nom du commentateur"));
        otherCastInputPanel.add(otherCastInputTextField);
        otherCastInputPanel.add(new JLabel("Autre commentateur"));
        castPanel.add(castIntoListPanel);
        castPanel.add(otherCastInputPanel);
        otherCastLinkInputPanel.add(otherCastLinkInputTextField);
        otherCastLinkInputPanel.add(new JLabel("Lien vers le stream (optionnel)"));
        castPanel.add(otherCastLinkInputPanel);

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridheight = 3;
        globalPanel.add(castPanel, gridBagConstraints);//Ajout du panel de commentateurs au panel global

        /*
        Gestion du bouton confirmer
         */
        JButton confirm = new JButton("valider");
        confirm.addActionListener(e -> {
            /*
            gestion des champs non remplis
             */
            java.util.List<String> tocomplete = new ArrayList<>();
            if (!gameIntoListRadioButton.isSelected() && !otherGameRadioButton.isSelected())
                tocomplete.add("section jeu du tournoi");
            if (otherGameRadioButton.isSelected()) {
                if (otherGameInputTextField.getText().equals("")) tocomplete.add("nom du jeu");
            }
            if (!localTournamentRadioButton.isSelected() && !distancialTournamentRadioButton.isSelected())
                tocomplete.add("section lieu du tournoi");
            if (!regionalTournamentRadioButton.isSelected() && !globalTournamentRadioButton.isSelected())
                tocomplete.add("section portée du tournoi");
            if (localTournamentRadioButton.isSelected()) {
                if (tournamentPlaceTextField.getText().equals("")) tocomplete.add("nom du lieu");
                if ((int) NumberRoomSpinner.getValue() < 1) tocomplete.add("nombre de salles");
            }
            if (!castIntoListRadioButton.isSelected() && !otherCastRadioButton.isSelected())
                tocomplete.add("section commentateur du tournoi");
            if (otherCastRadioButton.isSelected()) {
                if (otherCastInputTextField.getText().equals("")) tocomplete.add("nom du commentateur");
                otherCastLinkInputTextField.setEnabled(true);
            }
            if (!tocomplete.isEmpty())
                JOptionPane.showMessageDialog(null, "Veuillez completer les champs suivants :\n" + tocomplete);
            else {
                /*
                Confirmation des informations entrées via une confirmDialog
                 */
                int dialogButton = JOptionPane.YES_NO_OPTION;
                dialogButton = JOptionPane.showConfirmDialog(null, "validez vous les informations suivantes ?\n" + "Nombre d'équipes : " + teamNumberComboBox.getSelectedItem().toString() + "\n" + "Heure des matchs : " + matchHourComboBox.getSelectedItem().toString() + "\n" + "Debut du tournoi le " + daySpinner.getValue().toString() + "/" + monthSpinner.getValue().toString() + "/" + yearSpinner.getValue().toString() + "\n" + "durée moyenne des matchs : " + matchDurationSpiner.getValue().toString() +" minutes"+ "\n" + "durée des pauses : " + breakDurationSpiner.getValue().toString() + " minutes"+"\n" + (gameIntoListRadioButton.isSelected() ? "Sur le jeu " + gameListComboBox.getSelectedItem().toString() + "\n" : "") + (otherGameRadioButton.isSelected() ? "Sur le jeu " + otherGameInputTextField.getText() + "\n" : "") +"Ayant une portée "+(regionalTournamentRadioButton.isSelected()?"regionale":"globale") +"\n"+ (localTournamentRadioButton.isSelected() ? "En presentiel à " + tournamentPlaceTextField.getText() + " dans " + NumberRoomSpinner.getValue().toString() + " salles" + "\n" : "") + (distancialTournamentRadioButton.isSelected() ? "En distanciel \n" : "") + (castIntoListRadioButton.isSelected() ? "Commenté par " + castListComboBox.getSelectedItem().toString() + "\n" : "") + (otherCastRadioButton.isSelected() ? "Commenté par " + otherCastInputTextField.getText() + (!otherCastLinkInputTextField.getText().equals("") ? " sur la chaine : " + otherCastLinkInputTextField.getText() : "") : ""), "Confirmation", dialogButton);
                if (dialogButton == JOptionPane.YES_OPTION) {
                    /*
                    Gestion de l'appel du planningView() en passant en paramètre un tournoi créé
                     */
                    int tournament_nbteam = Integer.parseInt(teamNumberComboBox.getSelectedItem().toString());
                    int tournament_hour = Integer.parseInt(matchHourComboBox.getSelectedItem().toString());
                    int match_duration = (int) matchDurationSpiner.getValue();
                    int break_duration = (int) breakDurationSpiner.getValue();
                    int tournament_type = (regionalTournamentRadioButton.isSelected()?Tournament.REGIONAL:Tournament.GLOBAL);
                    String tournament_game = (gameIntoListRadioButton.isSelected() ? gameListComboBox.getSelectedItem().toString() : otherGameInputTextField.getText());
                    Boolean tournament_local = (localTournamentRadioButton.isSelected());
                    String tournament_cast = (castIntoListRadioButton.isSelected() ? castListComboBox.getSelectedItem().toString() : otherCastInputTextField.getText());
                    String tournament_link = (otherCastRadioButton.isSelected() ? otherCastLinkInputTextField.getText() : null);
                    CreateTournamentFrame.this.dispose();
                    Date tournamentStartDate = new Date((int) yearSpinner.getValue(), (int) monthSpinner.getValue() - 1, (int) daySpinner.getValue());
                    Tournament tournament = new Tournament(tournamentStartDate, tournament_hour, tournament_type, match_duration, break_duration, tournament_nbteam);
                    parentFrame.planningView(tournament);
                }
                //traitement post formulaire
            }
        });

        //Mise a jour des contraintes du GridBagLayout
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = 1;
        globalPanel.add(confirm, gridBagConstraints);//Ajout du panel de confirmation au panel global

        this.add(globalPanel);
    }

    private JPanel panel, teamNbPanel, matchHourPanel, gamePanel, placePanel, castPanel, gamelistPanel, gamelistotherPanel, castlistPanel, castlistotherPanel, castlistotherlinkPanel;

    private ButtonGroup gamelistRBG, castlistRBG, placeRBG;

    private JTextField gamelistotherTF, otherCastInputTextField, otherCastLinkInputTextField;

    private JRadioButton gamelistotherRB, gamelistRB, castlistRB, castlistotherRB, lanRB, distRB;

    private JComboBox teamNbCBB, matchHourComboBox, gamelistCBB, castListComboBox;

    private final String[] teamList = {"2", "4", "8", "16", "32"};

    private final AppFrame parentFrame;

    private final String[] hourList = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
}
