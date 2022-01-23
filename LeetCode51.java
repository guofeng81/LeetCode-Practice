import java.util.*;

public class LeetCode51 {
    public List<List<String>> solveNQueens(int n) {
        // row col
        char[][] board = new char[n][n];
        List<List<String>> result = new ArrayList<>();

        init(n, board);
        backtrack(0, board, result, n);
        return result;
    }

    private void init(int n, char[][] board) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
    }

    // how to convert 2 d array into List<String>
    private void backtrack(int rowIndex, char[][] board, List<List<String>> result, int n) {
        if (rowIndex == n) {
            result.add(new ArrayList<>(convertToString(board)));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(rowIndex, i, board)) {
                board[rowIndex][i] = 'Q';
                backtrack(rowIndex + 1, board, result, n);
                board[rowIndex][i] = '.';
            }
        }
    }

    private List<String> convertToString(char[][] board) {
        int n = board.length;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
            res.add(sb.toString());
        }
        return res;
    }

    private boolean isValid(int row, int col, char[][] board) {
        int n = board.length;

        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
