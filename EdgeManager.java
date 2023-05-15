package KTHP;

import java.util.Set;

public interface EdgeManager{
    /**
     * Check if vertex {@code u} and vertex {@code v} is connected
     * @param u
     * @param v
     * @return - {@code 0} if not connected
     *         - {@code 1} if connected in unweited graph
     *         - {@code w != 0} weight of the edge
     */
    public int getEdge(int u, int v);

    /**
     * Get all the vertex that {@code v} connected with
     * @param v
     * @return
     */
    public Set<Integer> getNearbyVertex(int v);

    /**
     * Add an undirected, unweighted edge between vertex {@code u} and vertex {@code v}
     * @param u
     * @param v
     */
    public void addEdge(int u, int v);

    /**
     * Add a directed, unweighted edge between vertex {@code u} and vertex {@code v}
     * @param u
     * @param v
     */
    public void addDirectedEdge(int u, int v);

    /**
     * Add an undirected, weighted edge between vertex {@code u} and vertex {@code v}
     * @param u
     * @param v
     * @param w - weight of connection
     */
    public void addWeightedEdge(int u, int v, int w);

    /**
     * Add a directed, weighted edge between vertex {@code u} and vertex {@code v}
     * @param u
     * @param v
     * @param val - weight of connection
     */
    public void addDirectedWeightedEdge(int u, int v, int val);

    /**
     * Remove undirected edge between vertex {@code u} and vertex {@code v}
     * @param u
     * @param v
     */
    public void removeEdge(int u, int v);

    /**
     * Remove directed edge between vertex {@code u} and vertex {@code v}
     * @param u
     * @param v
     */
    public void removeDirectedEdge(int u, int v);
}

