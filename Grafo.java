import java.util.ArrayList;
import java.util.List;

public class Grafo{
    private List<Node> nodes;
    private List<Edge> edges;

    public Grafo(List<Node> nodes, List<Edge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    private boolean nodeExist(Node n){
        boolean exist = false;
        for(Node node: nodes){
            if (n == node){
                exist = true;
                break;
            }
        }
        return exist;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addNode(Node n){
        nodes.add(n);
    }

    public void addEdges(Node nInit, Node nEnd, float value){
        if(nodeExist(nEnd) && nodeExist(nInit)){
            edges.add(new Edge(nInit, nEnd, value));
        }
    }

    //refazer
    public List<NodeEdge> getNodeEdges(Node nInit){
        List<NodeEdge> goList = new ArrayList<NodeEdge>();
        if(nodeExist(nInit)){
            for (Edge t: edges){
                if(t.getNodeInit() == nInit){
                    goList.add(new NodeEdge(t.getNodeEnd(), t.getValue()));
                }
            }
        }
        return goList;
    }
}