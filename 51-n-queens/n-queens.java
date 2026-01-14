class Solution {

    // Stores all valid N-Queens board configurations
    List<List<String>> result = new ArrayList<>();

    // col[c] = true → column c already has a queen
    boolean[] col;

    // diag1[d] = true → main diagonal (row - col) has a queen
    boolean[] diag1;

    // diag2[d] = true → anti-diagonal (row + col) has a queen
    boolean[] diag2;

    public List<List<String>> solveNQueens(int n) {

        // Initialize helper arrays for columns and diagonals
        col = new boolean[n];          // n columns
        diag1 = new boolean[2 * n];    // main diagonals
        diag2 = new boolean[2 * n];    // anti-diagonals

        // Create an empty chessboard filled with '.'
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(board[i], '.');

        // Start placing queens from row 0
        backtrack(0, board, n);

        // Return all valid configurations
        return result;
    }

    // Backtracking function to place queens row by row
    private void backtrack(int row, char[][] board, int n) {

        // Base case: all rows filled → valid solution found
        if (row == n) {
            List<String> config = new ArrayList<>();

            // Convert current board state to List<String>
            for (char[] r : board)
                config.add(new String(r));

            // Add configuration to result list
            result.add(config);
            return;
        }

        // Try placing a queen in each column of the current row
        for (int c = 0; c < n; c++) {

            // Calculate indices for diagonals
            int d1 = row - c + n;  // main diagonal index
            int d2 = row + c;      // anti-diagonal index

            // If column or diagonals already have a queen → skip
            if (col[c] || diag1[d1] || diag2[d2]) continue;

            // Place queen at (row, c)
            col[c] = diag1[d1] = diag2[d2] = true;
            board[row][c] = 'Q';

            // Move to next row
            backtrack(row + 1, board, n);

            // Backtrack: remove queen and free column & diagonals
            board[row][c] = '.';
            col[c] = diag1[d1] = diag2[d2] = false;
        }
    }
}
