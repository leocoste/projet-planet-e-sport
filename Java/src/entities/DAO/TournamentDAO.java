package entities.DAO;

import entities.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAO {
    private Connection con;

    public TournamentDAO() {
        try {
            // Établissement de la connexion à la base de données
            String url = "jdbc:mysql://localhost:3306/ma_base_de_données";
            String user = "mon_utilisateur";
            String password = "mon_mot_de_passe";
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = new ArrayList<>();

        try {
            // Exécution d'une requête SQL pour récupérer tous les tournois
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Tournament");

            // Traitement des résultats
            while (rs.next()) {
                int id = rs.getInt("TournamentID");
                Date beginDate = rs.getDate("beginDate");
                int startTime = rs.getInt("startTime");
                int type = rs.getInt("tournamentType");
                int matchDuration = rs.getInt("matchDuration");
                int breakDuration = rs.getInt("breakDuration");
                int nTeams = rs.getInt("nTeams");
                tournaments.add(new Tournament(beginDate,startTime,type,matchDuration,breakDuration,nTeams));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    public Tournament getTournamentById(int id) {
        try {
            // Exécution d'une requête SQL pour récupérer le tournoi
            PreparedStatement st = con.prepareStatement("SELECT * FROM Tournament WHERE TournamentID = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Création de l'objet Tournament à partir des résultats de la requête
                Date beginDate = rs.getDate("beginDate");
                int startTime = rs.getInt("startTime");
                int type = rs.getInt("tournamentType");
                int matchDuration = rs.getInt("matchDuration");
                int breakDuration = rs.getInt("breakDuration");
                int nTeams = rs.getInt("nTeams");
                return new Tournament(beginDate,startTime,type,matchDuration,breakDuration,nTeams);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addTournament(Tournament tournament) {
        try {
            // Exécution d'une requête SQL pour ajouter le tournoi
            PreparedStatement st = con.prepareStatement(
                    "INSERT INTO Tournament (beginDate,startTime,tournamentType,matchDuration,breakDuration,nTeams) VALUES (?, ?, ?, ?, ?, ?)");
            st.setDate(1, tournament.getBeginDate());
            st.setInt(2,tournament.getStartTime());
            st.setInt(3,tournament.getType());
            st.setInt(4,tournament.getMatchDuration());
            st.setInt(5,tournament.getBreakDuration());
            st.setInt(6,tournament.getNTeams());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTournament(Tournament tournament) {
        try {
            // Exécution d'une requête SQL pour mettre à jour le tournoi
            PreparedStatement st = con.prepareStatement(
                    "UPDATE Tournament SET beginDate = ?, startTime = ?, tournamentType = ?,matchDuration = ?, breakDuration = ?, nTeams = ? WHERE TournamentID = ?");
            st.setInt(7,tournament.getTournamentId());
            st.setDate(1, tournament.getBeginDate());
            st.setInt(2,tournament.getStartTime());
            st.setInt(3,tournament.getType());
            st.setInt(4,tournament.getMatchDuration());
            st.setInt(5,tournament.getBreakDuration());
            st.setInt(6,tournament.getNTeams());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTournament(int id) {
        try {
            // Exécution d'une requête SQL pour supprimer le tournoi
            PreparedStatement st = con.prepareStatement("DELETE FROM Tournament WHERE TournamentID = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
