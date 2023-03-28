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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                tournaments.add(new Tournament(id, name, location, startDate, endDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    public Tournament getTournamentById(int id) {
        try {
            // Exécution d'une requête SQL pour récupérer le tournoi
            PreparedStatement st = con.prepareStatement("SELECT * FROM Tournament WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Création de l'objet Tournament à partir des résultats de la requête
                String name = rs.getString("name");
                String location = rs.getString("location");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                return new Tournament(id, name, location, startDate, endDate);
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
                    "INSERT INTO Tournament (name, location, start_date, end_date) VALUES (?, ?, ?, ?)");
            st.setString(1, tournament.getName());
            st.setString(2, tournament.getLocation());
            st.setDate(3, tournament.getBeginDate());
            st.setDate(4, tournament.getEndDate());
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
                    "UPDATE Tournament SET name = ?, location = ?, start_date = ?, end_date = ? WHERE id = ?");
            st.setString(1, tournament.getName());
            st.setString(2, tournament.getLocation());
            st.setDate(3, tournament.getBeginDate());
            st.setDate(4, tournament.getEndDate());
            st.setInt(5, tournament.getId());
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
            PreparedStatement st = con.prepareStatement("DELETE FROM Tournament WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
