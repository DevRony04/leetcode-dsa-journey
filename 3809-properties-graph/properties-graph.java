class Solution {
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;

        // Step 1: Convert each property list to a Set
        List<Set<Integer>> sets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Set<Integer> s = new HashSet<>();
            for (int x : properties[i]) {
                s.add(x);
            }
            sets.add(s);
        }

        // Step 2: Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int common = 0;
                for (int val : sets.get(i)) {
                    if (sets.get(j).contains(val)) {
                        common++;
                        if (common >= k) {
                            graph.get(i).add(j);
                            graph.get(j).add(i);
                            break;
                        }
                    }
                }
            }
        }

        // Step 3: Count connected components
        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited);
                components++;
            }
        }

        return components;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                dfs(nei, graph, visited);
            }
        }
    }
}