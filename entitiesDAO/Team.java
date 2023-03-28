import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private Connection con;

    public TeamDAO() {
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

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        try {
            // Exécution d'une requête SQL pour récupérer toutes les équipes
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Team");

            // Traitement des résultats
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                teams.add(new Team(id, name, location));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public Team getTeamById(int id) {
        try {
            // Exécution d'une requête SQL pour récupérer l'équipe
            PreparedStatement st = con.prepareStatement("SELECT * FROM teams WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Création de l'objet Team à partir des résultats de la requête
                String name = rs.getString("name");
                String location = rs.getString("location");
                return new Team(id, name, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addTeam(Team team) {
        try {
            // Exécution d'une requête SQL pour ajouter l'équipe
            PreparedStatement st = con.prepareStatement("INSERT INTO Team (name, location) VALUES (?, ?)");
            st.setString(1, team.getName());
            st.setString(2, team.getLocation());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTeam(Team team) {
        try {
            // Exécution d'une requête SQL pour mettre à jour l'équipe
            PreparedStatement st = con.prepareStatement("UPDATE Team SET name = ?, location = ? WHERE id = ?");
            st.setString(1, team.getName());
            st.setString(2, team.getLocation());
            st.setInt(3, team.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTeam(int id) {
        try {
            // Exécution d'une requête SQL pour supprimer l'équipe
            PreparedStatement st = con.prepareStatement("DELETE FROM Team WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
