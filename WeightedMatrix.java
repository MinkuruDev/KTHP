package KTHP;

import java.util.HashSet;
import java.util.Set;

public class WeightedMatrix implements EdgeManager{
    private int[][] matrix;

    /**
     * Construct Adjancency matrix to store graph
     * @param n - number of vertex 
     */
    public WeightedMatrix(int n){
        matrix = new int[n][n];
    }

    public WeightedMatrix(int[][] matrix){
        this.matrix = matrix;
    }

    @Override
    public int getEdge(int u, int v) {
        return matrix[u][v];
    }

    @Override
    public Set<Integer> getNearbyVertex(int v) {
        Set<Integer> set = new HashSet<>(matrix.length);
        for(int i = 0; i<matrix.length; i++){
            if(matrix[v][i] != 0)
                set.add(i);
        }
        return set;
    }

    @Override
    public void addEdge(int u, int v) {
        matrix[u][v] = 1;
        matrix[v][u] = 1;
    }

    @Override
    public void addDirectedEdge(int u, int v) {
        matrix[u][v] = 1;
    }

    @Override
    public void addWeightedEdge(int u, int v, int w) {
        matrix[u][v] = w;
        matrix[v][u] = w;
    }

    @Override
    public void addDirectedWeightedEdge(int u, int v, int w) {
        matrix[u][v] = w;
    }

    @Override
    public void removeEdge(int u, int v) {
        matrix[u][v] = 0;
        matrix[v][u] = 0;
    }

    @Override
    public void removeDirectedEdge(int u, int v) {
        matrix[u][v] = 0;
    }
    
}
