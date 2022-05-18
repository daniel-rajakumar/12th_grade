import java.util.Objects;

public class Artist {

    private String name;
    private int uniqueID;

    public Artist(String name) {
        this.name = name;
        this.uniqueID = name.hashCode();
    }


    public int getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return uniqueID == artist.uniqueID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uniqueID);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", uniqueID=" + uniqueID +
                '}';
    }
}
