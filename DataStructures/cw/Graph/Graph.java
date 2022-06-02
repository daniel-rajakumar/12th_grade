import java.util.HashSet;

public class Graph {
    private final HashSet<Artist> artists;
    private final HashSet<Edge> edges;

    public Graph() {
        artists = new HashSet<Artist>();
        edges = new HashSet<Edge>();
    }

    public HashSet<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Artist source,Artist destination) {
        edges.add(new Edge(source,destination));
    }
}