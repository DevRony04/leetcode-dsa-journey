class Solution {
 public int minCost(int n, int[][] edges) {
       // Build graph:
        // - original edge u -> v with cost w
        // - reversed edge v -> u with cost 2*w
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to   = edge[1];
            int cost = edge[2];
            graph[from].add(new int[]{to, cost});
            graph[to].add(new int[]{from, 2 * cost});
        }
        // Dijkstra setup
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        // Min-heap: [currentDistance, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>( Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentDist = curr[0];
            int node = curr[1];
            // If we reached destination, this is the minimum cost
            if (node == n - 1) {
                return currentDist;
            }
            if (visited[node]) continue;
            visited[node] = true;
            for (int[] next : graph[node]) {
                int neighbor = next[0];
                int edgeCost = next[1];
                if (currentDist + edgeCost < dist[neighbor]) {
                    dist[neighbor] = currentDist + edgeCost;
                    pq.offer(new int[]{dist[neighbor], neighbor});
                }
            }
        }
        return -1;
    }
}