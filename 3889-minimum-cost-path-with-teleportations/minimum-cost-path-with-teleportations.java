class Solution {
    public int minCost(int[][] grid, int k) {
           int m = grid.length, n = grid[0].length;

        // Collect all cells
        List<int[]> cells = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                cells.add(new int[]{r, c});
            }
        }

        // Sort cells by grid value
        cells.sort(Comparator.comparingInt(cell -> grid[cell[0]][cell[1]]));

        // DP cost array
        int[][] cost = new int[m][n];
        for (int[] row : cost) Arrays.fill(row, Integer.MAX_VALUE);

        // Perform k relaxations
        for (int step = 0; step <= k; step++) {

            // Relax costs for cells with equal values
            int prefixMin = Integer.MAX_VALUE;
            int start = 0;

            for (int i = 0; i < cells.size(); i++) {
                int r = cells.get(i)[0];
                int c = cells.get(i)[1];
                prefixMin = Math.min(prefixMin, cost[r][c]);

                boolean isLastInGroup =
                    i + 1 == cells.size() ||
                    grid[r][c] != grid[cells.get(i + 1)[0]][cells.get(i + 1)[1]];

                if (isLastInGroup) {
                    for (int j = start; j <= i; j++) {
                        int x = cells.get(j)[0];
                        int y = cells.get(j)[1];
                        cost[x][y] = prefixMin;
                    }
                    start = i + 1;
                }
            }

            // Standard DP from bottom-right
            for (int r = m - 1; r >= 0; r--) {
                for (int c = n - 1; c >= 0; c--) {

                    if (r == m - 1 && c == n - 1) {
                        cost[r][c] = 0;
                        continue;
                    }

                    if (r + 1 < m) {
                        cost[r][c] = Math.min(
                            cost[r][c],
                            cost[r + 1][c] + grid[r + 1][c]
                        );
                    }

                    if (c + 1 < n) {
                        cost[r][c] = Math.min(
                            cost[r][c],
                            cost[r][c + 1] + grid[r][c + 1]
                        );
                    }
                }
            }
        }

        return cost[0][0];
    }
}