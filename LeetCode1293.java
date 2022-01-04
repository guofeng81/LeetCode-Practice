import java.util.*;

class LeetCode1293 {
    public int shortestPath(int[][] grid, int k) {
        // declare a int[] to represents [row, col, obstables]
        // declare a seen[i][j] matrix to check whether at the current position is with
        // the min obstacles, if yes, then means at the current position, we don't need
        // to update until we have a smaller obstacles at the current position to
        // update, then we need to update the seen[i][j]. We initialize the seen[][]
        // matrix as Integer.MAX_VALUE at the beginning. (This is the most tricky part
        // in this question)
        /*
         * code{
         * // inside the DIRS
         * int o = cur[2] + grid[newRow][newCol];
         * // check out of bound
         * if(o >= seen[i][j] (means the current position hasn't been visited or don't
         * find a better route)
         * || o > k (exceed the k means there are no more chances to remove the
         * obstacles)) continue;
         * // update the seen[][] and then use new o to add it to the queue for next
         * step
         * }
         */

        if (grid == null)
            return -1;

        int m = grid.length; // rows
        int n = grid[0].length; // cols

        int[][] seen = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(seen[i], Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { 0, 0, 0 });
        seen[0][0] = 0;
        int shortestPath = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == m - 1 && cur[1] == n - 1)
                    return shortestPath;
                for (int[] dir : DIRS) {
                    int newRow = cur[0] + dir[0];
                    int newCol = cur[1] + dir[1];
                    if (outOfBound(newRow, newCol, grid))
                        continue;
                    int o = cur[2] + grid[newRow][newCol];
                    if (seen[newRow][newCol] <= o || o > k)
                        continue;
                    seen[newRow][newCol] = o;
                    queue.add(new int[] { newRow, newCol, o });
                }
            }

            shortestPath++;
        }

        return -1;
    }

    private boolean outOfBound(int row, int col, int[][] grid) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length)
            return false;
        return true;
    }

    public static int[][] DIRS = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
}