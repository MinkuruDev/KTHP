package KTHP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class WeightedList implements EdgeManager {
    private ArrayList<HashMap<Integer, Integer>> list;

    /**
     * Construct Adjiancency list to store graph
     * @param n - number of vertex
     */
    public WeightedList(int n){
        list = new ArrayList<>(n);
        for(int i = 0; i<n; i++){
            list.add(new HashMap<>());
        }
    }

    @Override
    public int getEdge(int u, int v) {
        return list.get(u).get(v);
    }

    @Override
    public Set<Integer> getNearbyVertex(int v) {
        return list.get(v).keySet();
    }

    @Override
    public void addEdge(int u, int v) {
        list.get(u).put(v, 1);
        list.get(v).put(u, 1);
    }

    @Override
    public void addDirectedEdge(int u, int v) {
        list.get(u).put(v, 1);
    }

    @Override
    public void addWeightedEdge(int u, int v, int w) {
        list.get(u).put(v, w);
        list.get(v).put(u, w);
    }

    @Override
    public void addDirectedWeightedEdge(int u, int v, int w) {
        list.get(u).put(v, w);
    }

    @Override
    public void removeEdge(int u, int v) {
        list.get(u).remove(v);
        list.get(v).remove(u);
    }

    @Override
    public void removeDirectedEdge(int u, int v) {
        list.get(u).remove(v);
    }
    
}
