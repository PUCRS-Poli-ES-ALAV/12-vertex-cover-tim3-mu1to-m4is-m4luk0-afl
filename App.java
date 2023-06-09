import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class App {

    private static List<Node> nodes;
    private static List<Edge> edges;
    private static Random gerador = new Random();

    public static void reader(String fName) throws FileNotFoundException {
        File file = new File(fName);
        Scanner s = new Scanner(file);
        String line = s.nextLine();

        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();

        while (s.hasNextLine()) {
            nodes.add(new Node(line, 0));
            line = s.nextLine();
        }

        nodes.add(new Node(line, 0));

        for(int i = 0; i < nodes.size(); i++){
            for(int j = i+1; j < nodes.size(); j++){
                edges.add(new Edge(nodes.get(i), nodes.get(j), 0));
            }
        }
        s.close();
    }

    public static List<Node> caixeiroViajante(Grafo g){
        Node raiz = g.getNodes().get(gerador.nextInt(g.getNodes().size()-1));
        List<Node> T = new ArrayList<Node>();
        List<Node> H = new ArrayList<Node>();
        T = MSTPrim(g, raiz);
        for(Node n: T){
            if(!H.contains(n)){
                H.add(n);
            }
        }

        return H;
    }

    public static List<Node> MSTPrim(Grafo g, Node r){
        List<Node> l = g.getNodes();
        l.remove(r);
        List<Node> prim = new ArrayList<Node>();
        List<Edge> ed = g.getEdges();
        prim.add(r);
        while(!l.isEmpty()){
            float menor = Float.MAX_VALUE;
            Edge menorEdge = null;
            for(Node n: prim){
                for(Edge e: ed){
                    if((e.getNodeInit() == n || e.getNodeEnd() == n) && e.getValue() < menor){
                        menor = e.getValue();
                        menorEdge = e;
                    }
                }
            }

            ed.remove(menorEdge);

            for(int i = prim.size()-1; i >= 0; i--){
                if (prim.get(i) == menorEdge.getNodeInit() || prim.get(i) == menorEdge.getNodeEnd()){
                    break;
                }
                prim.add(prim.get(i-1));
            }
            if(!prim.contains(menorEdge.getNodeInit())){
                prim.add(menorEdge.getNodeInit());
                l.remove(menorEdge.getNodeInit());
            }
            else if(!prim.contains(menorEdge.getNodeEnd())){
                prim.add(menorEdge.getNodeEnd());
                l.remove(menorEdge.getNodeEnd());
            }
        }
        System.out.println("MSTPrim");
        for(Node n: prim){
            System.out.print(n.getId()+", ");
        }
        return prim;
    }

    public static void main(String args[]){
        try {
            reader(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Grafo g = new Grafo(nodes, edges);
        //System.out.println(g.getEdges().size());
        // for (Edge n : verticeCover(g)) {
        //     System.out.println(n.toString());
        // }
        // int n = g.getEdges().size();
        // int result = (n*(1+n*(3+2*n)));
        // System.out.println(result);
        //System.out.println(verticeCoverErrado(g).size());

        List<Node> l = caixeiroViajante(g);
        System.out.println("\nCaixeiro Viajante");
        for (Node n : l) {
            System.out.println(n.toString());
        }
    }
}


//    public static void reader(String fName) throws FileNotFoundException {
//        File file = new File(fName);
//        Scanner s = new Scanner(file);
//        String line = s.nextLine();
//
//        nodes = new ArrayList<Node>();
//        edges = new ArrayList<Edge>();
//
//        while (s.hasNextLine()) {
//            String[] listaEntrada = line.split(" ");
//            if (listaEntrada[0].equals("n")){
//                nodes.add(new Node(listaEntrada[1], 0));
//            }
//            else{
//                Node n1 = null;
//                Node n2 = null;
//                for (Node n : nodes) {
//                    if(n.getId().equals(listaEntrada[0])){
//                        n1 = n;
//                    }
//                    else if (n.getId().equals(listaEntrada[1])){
//                        n2 = n;
//                    }
//                }
//
//                edges.add( new Edge(n1, n2, 0));
//            }
//
//            line = s.nextLine();
//        }
//
//        String[] listaEntrada = line.split(" ");
//            if (listaEntrada[0].equals("n")){
//                nodes.add(new Node(listaEntrada[1], 0));
//            }
//            else{
//                Node n1 = null;
//                Node n2 = null;
//                for (Node n : nodes) {
//                    if(n.getId().equals(listaEntrada[0])){
//                        n1 = n;
//                    }
//                    else if (n.getId().equals(listaEntrada[1])){
//                        n2 = n;
//                    }
//                }
//
//                edges.add( new Edge(n1, n2, 0));
//            }
//        s.close();
//    }
//
//    public static List<Edge> verticeCover(Grafo g){
//        List<Edge> cover = new ArrayList<Edge>();
//        List<Edge> elementsI = g.getEdges();
//        List<Edge> elements = new ArrayList<Edge>();
//        int inter = 0;
//        for(Edge n: elementsI) {
//            Edge p = n;
//            for (Edge edge : elementsI) {
//                elements.add(edge);
//            }
//            List<Edge> coverAux = new ArrayList<Edge>();
//            boolean aux = true;
//            while (aux){
//                coverAux.add(p);
//                elements.remove(p);
//                List<Edge> remove = new ArrayList<Edge>();
//                for (Edge nodeT : elements) {
//                    inter++;
//                    if(nodeT.getNodeInit() == p.getNodeInit() || nodeT.getNodeInit() == p.getNodeEnd() || nodeT.getNodeEnd() == p.getNodeInit() || nodeT.getNodeEnd() == p.getNodeEnd()){
//                        remove.add(nodeT);
//                    }
//                }
//                for (Edge edge : remove) {
//                    inter++;
//                    elements.remove(edge);
//                }
//                if(elements.size() > 0){
//                    p = elements.get(0);
//                }
//                else{
//                    aux = false;
//                }
//            }
//            if(cover.size() == 0 || coverAux.size() < cover.size()){
//                cover = coverAux;
//            }
//        }
//        System.out.println(inter);
//        return cover;
//    }
//
//    public static List<Edge> verticeCoverErrado(Grafo g){
//        
//        List<Edge> cover = new ArrayList<Edge>();
//        List<Edge> elements = g.getEdges();
//        int inter = 0;
//        Edge p = elements.get(gerador.nextInt(elements.size()-1));
//        List<Edge> coverAux = new ArrayList<Edge>();
//        boolean aux = true;
//        while (aux){
//            coverAux.add(p);
//            elements.remove(p);
//            List<Edge> remove = new ArrayList<Edge>();
//            for (Edge nodeT : elements) {
//                inter++;
//                if(nodeT.getNodeInit() == p.getNodeInit() || nodeT.getNodeInit() == p.getNodeEnd() || nodeT.getNodeEnd() == p.getNodeInit() || nodeT.getNodeEnd() == p.getNodeEnd()){
//                    remove.add(nodeT);
//                }
//            }
//            for (Edge edge : remove) {
//                inter++;
//                elements.remove(edge);
//            }
//            if(elements.size() > 0){
//                p = elements.get(0);
//            }
//            else{
//                aux = false;
//            }
//        }
//        if(cover.size() == 0 || coverAux.size() < cover.size()){
//            cover = coverAux;
//        }
//    
//    System.out.println(inter);
//    return cover;
//    }
