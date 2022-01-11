public class LeetCode36 {
    // Question: Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            boolean[] taken = new boolean[9]; // you could also use map[128] to solve this problem.
            for (int j = 0; j < 9; j++) {
                char c = board[row][j];
                if (c != '.') {
                    if (taken[c - '1']) {
                        return false;
                    } else {
                        taken[c - '1'] = true;
                    }
                }
            }
        }

        for (int col = 0; col < 9; col++) {
            boolean[] taken = new boolean[9];
            for (int i = 0; i < 9; i++) {
                char c = board[i][col];
                if (c != '.') {
                    if (taken[c - '1']) {
                        return false;
                    } else {
                        taken[c - '1'] = true;
                    }
                }
            }
        }

        for (int box = 0; box < 9; box++) {
            boolean[] taken = new boolean[9];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    char c = board[row + 3 * (box / 3)][col + 3 * (box % 3)];
                    if (c != '.') {
                        if (taken[c - '1']) {
                            return false;
                        } else {
                            taken[c - '1'] = true;
                        }
                    }
                }
            }
        }

        return true;
    }
}
