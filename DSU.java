package KTHP;

public class DSU {
    int[] parent;

    public DSU(int n){
        parent = new int[n];

        for(int i = 0; i<n; i++){
            parent[i] = i;
        }
    }

    public int find(int i){
        if(i == parent[i]) return i;

        int j = find(parent[i]);
        parent[i] = j;
        return j;
    }

    public boolean union(int i, int j){
        i = find(i);
        j = find(j);
        if(i == j) return false;

        parent[i] = j;
        return true;
    }

}
