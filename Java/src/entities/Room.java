package entities;

public record Room(int id, String name,int capacity) {

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\''+
                '}';
    }
}
