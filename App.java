import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class App {

    private static List<Node> nodes;
    private static List<Edge> edges;

    public static void reader(String fName) throws FileNotFoundException {
        File file = new File(fName);
        Scanner s = new Scanner(file);
        String line = s.nextLine();

        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();

        while (s.hasNextLine()) {
            String[] listaEntrada = line.split(" ");
            if (listaEntrada[0].equals("n")){
                nodes.add(new Node(listaEntrada[1], 0));
            }
            else{
                edges.add(new Edge(new Node(listaEntrada[0], 0), new Node(listaEntrada[1], 0), (float) 0));
            }

            line = s.nextLine();
        }

        String[] listaEntrada = line.split(" ");
            if (listaEntrada[0].equals("n")){
                nodes.add(new Node(listaEntrada[1], 0));
            }
            else{
                edges.add(new Edge(new Node(listaEntrada[0], 0), new Node(listaEntrada[1], 0), (float) 0));
            }
        s.close();
    }

    public static List<Edge> verticeCover(Grafo g){
        List<Edge> cover = new ArrayList<Edge>();
        List<Edge> elementsI = g.getEdges();

        for(Edge n: g.getEdges()) {
            Edge p = n;
            List<Edge> coverAux = new ArrayList<Edge>();
            List<Edge> elements = new ArrayList<Edge>();
            elements = elementsI;
            while (!elements.isEmpty()){
                coverAux.add(p);
                elements.remove(p);
                for (Edge nodeT : elements) {
                    if(nodeT.getNodeInit() == p.getNodeInit() || nodeT.getNodeInit() == p.getNodeEnd() || nodeT.getNodeEnd() == p.getNodeInit() || nodeT.getNodeEnd() == p.getNodeEnd()){
                        elements.remove(nodeT);
                    }
                }
                if(elements.size() > 0){
                    p = elements.get(0);
                }
            }
            if(cover.size() == 0 || coverAux.size() < cover.size()){
                //System.out.println("mudei no cover");
                cover = coverAux;
            }
        }
        return cover;
    }
    public static void main(String args[]){
        try {
            reader(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Grafo g = new Grafo(nodes, edges);
        for (Edge n : verticeCover(g)) {
            System.err.println(n.toString());
        }
    }
}
