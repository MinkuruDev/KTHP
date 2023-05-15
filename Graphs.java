package KTHP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs {
    private EdgeManager manager;
    private int vertex;
    private boolean[] visited;

    public Graphs(int size){
        this.vertex = size;
        if(size < 100){
            manager = new WeightedMatrix(size);
        }else{
            manager = new WeightedList(size);
        }
    }

    public Graphs(int size, EdgeManager manager){
        this.vertex = size;
        this.manager = manager;
    }

    public EdgeManager getManager() {
        return manager;
    }

    public int getVertex() {
        return vertex;
    }

    public void setMatrix(EdgeManager edges) {
        this.manager = edges;
    }

    public void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[vertex];
        queue.add(start);
        visited[start] = true;
        
        while(! queue.isEmpty()){
            int p = queue.poll();
            System.out.println("Visited " + p);
            for(int i : manager.getNearbyVertex(p)){
                if(visited[i]) continue;
                visited[i] = true;
                queue.add(i);
            }
        }

    }

    private void DFSHelper(int current){
        if(visited[current]) return;
        visited[current] = true;
        System.out.println("Visited " + current);
        for(int i: manager.getNearbyVertex(current))
            DFSHelper(i);
    }

    public void DFS(int start){
        visited = new boolean[vertex];
        DFSHelper(start);
    }

    public void spawningTree(int start){
        System.out.println("A Spawning tree: ");
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[vertex];
        queue.add(start);
        visited[start] = true;

        while(! queue.isEmpty()){
            int p = queue.poll();
            for(int i : manager.getNearbyVertex(p)){
                if(visited[i]) continue;
                visited[i] = true;
                System.out.printf("Edge %d %d\n", p, i);
                queue.add(i);
            }
        }
    }

    public void kruskal(){
        ArrayList<Pair<Integer, Integer>> edgeList = new ArrayList<>();
        for(int i = 0; i<vertex; i++){
            for(int j: manager.getNearbyVertex(i))
                if(j > i)
                    edgeList.add(new Pair<Integer,Integer>(i, j));
        }

        edgeList.sort(new Comparator<Pair<Integer,Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return manager.getEdge(o1.first(), o1.second())
                    -  manager.getEdge(o2.first(), o2.second());
            }
        });

        int sum = 0;
        DSU dsu = new DSU(vertex);
        for(Pair<Integer, Integer> edge : edgeList){
            if(dsu.union(edge.first(), edge.second())){
                sum += manager.getEdge(edge.first(), edge.second());
                System.out.println(edge.first() + " " + edge.second());
            }
        }
        System.out.println("Tong trong so la: " + sum);
    }

    /**
     * Tìm đường đi ngắn nhất từ đỉnh {@code start} đến tất cả các đỉnh còn lại
     * @param start - đỉnh bắt đầu
     * @param trace - mảng để truy vết 
     * @return - trả về mảng {@code distance} là đường đi ngắn nhất từ đỉnh {@code start} đến tất cả các đỉnh còn lại 
     */
    public int[] Dijkstra(int start, int[] trace){
        // khởi tạo
        int[] distance = new int[vertex]; // mảng kết quả
        boolean[] visited = new boolean[vertex]; // mảng đánh dấu
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertex, 
            (Pair<Integer, Integer> pii1, Pair<Integer, Integer> pii2) -> pii1.first() - pii2.first());

        Arrays.fill(distance, Integer.MAX_VALUE); // khởi tạo khoảng cách đến tất cả các đỉnh là vô cực
        distance[start] = 0; // khoảng cách từ đỉnh bắt đầu đến chính nó bằng 0
        Arrays.fill(trace, -1); // khởi tạo mảng truy vết chưa được truy vết
        trace[start] = start; // truy vết start là chính nó
        pq.add(new Pair<Integer,Integer>(0, 1));
        
        // duyệt các đỉnh
        while(! pq.isEmpty()){
            // tìm đỉnh tốt nhất chưa được sử dụng
            int uBest = pq.poll().second();
            if(visited[uBest]) continue;
            // int minDistance = Integer.MAX_VALUE; // Khoảng cách nhỏ nhất ban đầu là vô cực
            // for(int j = 1; j<vertex; j++){ // duyệt qua tất cả các đỉnh
            //     // nếu khoảng cách đến đỉnh đó nhỏ hơn khoảng cách nhỏ nhất tìm được và đỉnh chưa được đánh dấu là đã duyệt
            //     if(distance[j] < minDistance && !visited[j]){ 
            //         uBest = j; // gán đỉnh tốt nhất
            //         minDistance = distance[j]; // đặt lại khoảng cách nhỏ nhất
            //     }
            // }

            // cập nhật tổng trọng số
            visited[uBest] = true; // đánh dấu đỉnh đang xét là đã thăm
            for(int j : manager.getNearbyVertex(uBest)){ // duyệt tất cả các đỉnh kề đỉnh đang xét
                // nếu đỉnh chưa được đánh dấu và khoảng cách mới nhỏ hơn khoảng cách hiện tại
                if(!visited[j] && distance[uBest] + manager.getEdge(uBest, j) < distance[j]){
                    distance[j] = distance[uBest] + manager.getEdge(uBest, j); // đặt lại khoảng cách là khoảng cách mới nhỏ hơn
                    trace[j] = uBest; // truy vết đỉnh
                    pq.add(new Pair<Integer,Integer>(distance[j], j));
                }
            }
        }

        return distance;
    }

    /**
     * Tìm đường đi ngắn nhất từ đỉnh {@code start} đến đỉnh {@code target}
     * @param start - đỉnh bắt đầu
     * @param target - đỉnh kết thúc
     * @param trace - mảng truy vết
     * @return - khoảng cách ngắn nhất từ đỉnh {@code start} đến một số đỉnh có khoảng cách đến đỉnh đó
     * nhỏ hơn hoặc bằng khoảng cách đến đỉnh {@code target} <br>
     * Đỉnh chưa được tính khoảng cách sẽ có khoảng cách đén nó là vô cùng ({@code Integer.MAX_VALUE})
     */
    public int[] Dijkstra(int start, int target, int[] trace){
        // khởi tạo
        int[] distance = new int[vertex]; // mảng kết quả
        boolean[] visited = new boolean[vertex]; // mảng đánh dấu
        // khởi tạo hàng đợi ưu tiên, với phần tử đầu hàng đợi luôn có khoảng cách đến nó là ngắn nhất trong các khoảng cách còn lại
        // mỗi phần tử trong hàng đợi là 1 cặp giá trị bao gồm một đỉnh và khoảng cách đến đỉnh đó
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertex, 
            (Pair<Integer, Integer> pii1, Pair<Integer, Integer> pii2) -> pii1.second() - pii2.second());

        Arrays.fill(distance, Integer.MAX_VALUE); // khởi tạo khoảng cách đến tất cả các đỉnh là vô cực
        distance[start] = 0; // khoảng cách từ đỉnh bắt đầu đến chính nó bằng 0
        Arrays.fill(trace, -1); // khởi tạo mảng truy vết chưa được truy vết
        trace[start] = start; // truy vết start là chính nó
        // thêm đỉnh bắt đầu vào hàng đợi ưu tiên với khoảng cách đến nó là 0
        pq.add(new Pair<Integer,Integer>(start, 0));
        
        // duyệt các đỉnh
        while(! pq.isEmpty()){
            // tìm đỉnh tốt nhất chưa được sử dụng
            // vì đang sử dụng hàng đợi ưu tiên nên đỉnh có khoảng cách đến nó 
            // ngắn nhất trong số các đỉnh còn lại luôn đứng đầu hàng đợi
            int uBest = pq.poll().first();
            // nếu đỉnh đã được thăm, ta bỏ qua
            // trường hợp xảy ra khi ở 2 lần duyệt đỉnh khác nhau
            // ta có thể thêm 1 đỉnh vào 2 lần với khoảng cách khác nhau
            if(visited[uBest]) continue;
            // đánh dấu đỉnh đang xét là đã thăm
            visited[uBest] = true;

           
            // đỉnh cần tìm đã được đánh dấu đã thăm, tức là đã tìm được khoảng cách đến nó là ngắn nhất
            // không cần tìm đường đến các đỉnh còn lại vì không phải là mục tiêu ta tìm kiếm
            
            if(uBest == target) break;
            
            // cập nhật tổng trọng số
            for(int j : manager.getNearbyVertex(uBest)){ // duyệt tất cả các đỉnh kề đỉnh đang xét
                // nếu đỉnh chưa được đánh dấu và khoảng cách mới nhỏ hơn khoảng cách hiện tại đến đỉnh đó
                if(!visited[j] && distance[uBest] + manager.getEdge(uBest, j) < distance[j]){
                    // đặt lại khoảng cách là khoảng cách mới nhỏ hơn
                    distance[j] = distance[uBest] + manager.getEdge(uBest, j); 
                    trace[j] = uBest; // truy vết đỉnh
                    // thêm cặp đỉnh, khoảng cách đến nó vào hàng đợi ưu tiên
                    pq.add(new Pair<Integer,Integer>(j, distance[j])); 
                }
            }
        }

        return distance;
    }

}
