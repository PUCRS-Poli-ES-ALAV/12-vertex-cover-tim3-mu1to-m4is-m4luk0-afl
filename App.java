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
                Node n1 = null;
                Node n2 = null;
                for (Node n : nodes) {
                    if(n.getId().equals(listaEntrada[0])){
                        n1 = n;
                    }
                    else if (n.getId().equals(listaEntrada[1])){
                        n2 = n;
                    }
                }

                edges.add( new Edge(n1, n2, 0));
            }

            line = s.nextLine();
        }

        String[] listaEntrada = line.split(" ");
            if (listaEntrada[0].equals("n")){
                nodes.add(new Node(listaEntrada[1], 0));
            }
            else{
                Node n1 = null;
                Node n2 = null;
                for (Node n : nodes) {
                    if(n.getId().equals(listaEntrada[0])){
                        n1 = n;
                    }
                    else if (n.getId().equals(listaEntrada[1])){
                        n2 = n;
                    }
                }

                edges.add( new Edge(n1, n2, 0));
            }
        s.close();
    }

    public static List<Edge> verticeCover(Grafo g){
        List<Edge> cover = new ArrayList<Edge>();
        List<Edge> elementsI = g.getEdges();

        for(Edge n: elementsI) {
            Edge p = n;
            List<Edge> coverAux = new ArrayList<Edge>();
            List<Edge> elements = new ArrayList<Edge>();
            for (Edge edge : elementsI) {
                elements.add(edge);
            }

            boolean aux = true;
            while (aux){
                coverAux.add(p);
                elements.remove(p);
                List<Edge> remove = new ArrayList<Edge>();
                for (Edge nodeT : elements) {
                    if(nodeT.getNodeInit() == p.getNodeInit() || nodeT.getNodeInit() == p.getNodeEnd() || nodeT.getNodeEnd() == p.getNodeInit() || nodeT.getNodeEnd() == p.getNodeEnd()){
                        remove.add(nodeT);
                    }
                }
                for (Edge edge : remove) {
                    elements.remove(edge);
                }
                if(elements.size() > 0){
                    p = elements.get(0);
                }
                else{
                    aux = false;
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
            System.out.println(n.toString());
        }
    }
}
