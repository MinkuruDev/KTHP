package KTHP;

public class Bai4 {
    public static void main(String[] args) {
        Graphs graphs = new Graphs(6, new WeightedList(6));
        graphs.getManager().addWeightedEdge(0, 1, 4);
        graphs.getManager().addWeightedEdge(1, 2, 8);
        graphs.getManager().addWeightedEdge(2, 3, 7);
        graphs.getManager().addWeightedEdge(2, 5, 4);
        graphs.getManager().addWeightedEdge(3, 4, 9);
        graphs.getManager().addWeightedEdge(3, 5, 13);
        graphs.getManager().addWeightedEdge(4, 5, 10);

        graphs.kruskal();
    }
}
