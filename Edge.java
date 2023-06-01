public class Edge {
    private Node nodeInit;
    private Node nodeEnd;
    private float value;

    public Edge(Node nodeInit, Node nodeEnd, float value){
        this.nodeInit = nodeInit;
        this.nodeEnd = nodeEnd;
        this.value = value;
    }

    public Node getNodeInit() {
        return nodeInit;
    }

    public Node getNodeEnd() {
        return nodeEnd;
    }

    public float getValue(){
        return value;
    }

}
