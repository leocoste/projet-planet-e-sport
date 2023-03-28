package entities.DAO;

import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection con;

    public UserDAO() {
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

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            // Exécution d'une requête SQL pour récupérer tous les utilisateurs
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM User");

            // Traitement des résultats
            while (rs.next()) {
                String login = rs.getString("login");
                String pwd = rs.getString("pwd");
                users.add(new User(login,pwd));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int userId) {
        try {
            // Exécution d'une requête SQL pour récupérer l'utilisateur
            PreparedStatement st = con.prepareStatement("SELECT * FROM User WHERE userId = ?");
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Création de l'objet User à partir des résultats de la requête
                String login = rs.getString("login");
                String pwd = rs.getString("pwd");
                return new User(login,pwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user) {
        try {
            // Exécution d'une requête SQL pour ajouter l'utilisateur
            PreparedStatement st = con
                    .prepareStatement("INSERT INTO User (userLastName, userPWD) VALUES (?, ?)");
            st.setString(1, user.getLogin());
            st.setString(2, user.getPwd());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        try {
            // Exécution d'une requête SQL pour mettre à jour l'utilisateur
            PreparedStatement st = con
                    .prepareStatement("UPDATE User SET userLastName = ?, userPWD = ? WHERE userID = ?");
            st.setString(1, user.getLogin());
            st.setString(2, user.getPwd());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        try {
            // Exécution d'une requête SQL pour supprimer l'utilisateur
            PreparedStatement st = con.prepareStatement("DELETE FROM User WHERE userId = ?");
            st.setInt(1, userId);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
