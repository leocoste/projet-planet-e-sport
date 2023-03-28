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
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                users.add(new User(id, username, password, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int id) {
        try {
            // Exécution d'une requête SQL pour récupérer l'utilisateur
            PreparedStatement st = con.prepareStatement("SELECT * FROM User WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Création de l'objet User à partir des résultats de la requête
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                return new User(id, username, password, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
        try {
            // Exécution d'une requête SQL pour récupérer l'utilisateur
            PreparedStatement st = con.prepareStatement("SELECT * FROM User WHERE username = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Création de l'objet User à partir des résultats de la requête
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String email = rs.getString("email");
                return new User(id, username, password, email);
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
                    .prepareStatement("INSERT INTO User (username, password, email) VALUES (?, ?, ?)");
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
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
                    .prepareStatement("UPDATE User SET username = ?, password = ?, email = ? WHERE id = ?");
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        try {
            // Exécution d'une requête SQL pour supprimer l'utilisateur
            PreparedStatement st = con.prepareStatement("DELETE FROM User WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
