import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private Connection con;

    public RoomDAO() {
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

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();

        try {
            // Exécution d'une requête SQL pour récupérer toutes les salles
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Room");

            // Traitement des résultats
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int capacity = rs.getInt("capacity");
                rooms.add(new Room(id, name, capacity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoomById(int id) {
        try {
            // Exécution d'une requête SQL pour récupérer la salle
            PreparedStatement st = con.prepareStatement("SELECT * FROM Room WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Création de l'objet Room à partir des résultats de la requête
                String name = rs.getString("name");
                int capacity = rs.getInt("capacity");
                return new Room(id, name, capacity);
           
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public boolean addRoom(Room room) {
        try {
            // Exécution d'une requête SQL pour ajouter la salle
            PreparedStatement st = con.prepareStatement("INSERT INTO Room (name, capacity) VALUES (?, ?)");
            st.setString(1, room.getName());
            st.setInt(2, room.getCapacity());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRoom(Room room) {
        try {
            // Exécution d'une requête SQL pour mettre à jour la salle
            PreparedStatement st = con.prepareStatement("UPDATE Room SET name = ?, capacity = ? WHERE id = ?");
            st.setString(1, room.getName());
            st.setInt(2, room.getCapacity());
            st.setInt(3, room.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoom(int id) {
        try {
            // Exécution d'une requête SQL pour supprimer la salle
            PreparedStatement st = con.prepareStatement("DELETE FROM Room WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
