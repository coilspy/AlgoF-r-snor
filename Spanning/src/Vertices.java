import java.util.ArrayList;
import java.util.List;

public class Vertices {
    String name;
    List<Edge> connectedEdges;

    public Vertices(String name)
    {
        this.name = name;
        this.connectedEdges = new ArrayList<>();
    }

    public String getName()
    {
        return this.name;
    }

    public void addEdge(Edge edge)
    {
        connectedEdges.add(edge);
    }

    public Edge getEdge(int index)
    {
        return connectedEdges.get(index);
    }
}
