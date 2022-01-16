public class LeetCode132 {
    // Palindrome Partitioning II
    // special solution with O(N^2) Time

    // explanation:
    // a b c | d d
    // j-1 j i 只要 isPalin[j][i] 是回文，那么我们就可以在j-1的位置上切一刀，之后递归找到最小的值， 那么比较 dp[i] 与切一刀情况
    // dp[j-1] + 1, 取小的值
    // i 从 0 到 n - 1 表示从字母 a 到字母 d, j 从 0 到 i

    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] isPalin = createPalindrome(s);
        // 这个题目难在哪？
        // 难在如何定义 dp 的范围, 是 定义 dp[n+1] 还是dp[n] 呢？
        // 其实都可以，但是看你怎么去理解了。
        // 举个例子，如果定义的是dp[n+1]那么这时候dp[i]是什么含义？dp[0] 又是什么含义？
        // 这时候i代表的是前i个字母能划分成几段palindrome。dp[0] = 0.
        // 那么i 从 1 开始到 n, j这把刀就切在第个字母的后面。 if(isPalin[j][i-1]){dp[i] = Math.min(dp[i],
        // dp[j] + 1);} 意思是将这i个字母分成几段，最后dp[n] - 1才是最少切割的个数
        // 好绕有没有。
        // 看一下下面这种解法， 就定义dp[n] 长度， i 从 0 到 n-1
        // a a b | d d
        // j j+1 i
        // j 从 0 到 i - 1

        for (int i = 0; i < n; i++) {
            if (isPalin[0][i]) {
                dp[i] = 0;
                continue;
            }

            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (isPalin[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        // for (int i = 0; i < n; i++) {
        // int min = i; // i 为当下切割最多的刀数
        // for (int j = 0; j <= i; j++) {
        // if (s.charAt(i) == s.charAt(j) && (j + 1 > i + 1 || isPalin[j + 1][i - 1])) {
        // isPalin[i][j] = true;
        // min = j == 0 ? 0 : Math.min(min, minCut[j - 1] + 1); // i
        // 代表的是切几个字母，而j表示的是在i个字母中切割的点
        // // | a b | c d d
        // // i = 1 代表的是有i + 1个字母， 两个字母 ab
        // // j = 0 并且 保证了a b 是palindrome才进到里面来，虽然这时候的ab其实不是回文，所以这种情况不用切。所以这是 min = 0,
        // // j = 1 刀切在 a | b 这里
        // }
        // }
        // minCut[i] = min;
        // }
        return dp[n - 1];
    }

    private boolean[][] createPalindrome(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];

        for (int mid = 0; mid < n; mid++) {
            int i = mid;
            int j = mid;
            while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
                isPalin[i][j] = true;
                i--;
                j++;
            }
        }

        for (int mid = 0; mid < n; mid++) {
            int i = mid;
            int j = mid + 1;
            while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
                isPalin[i][j] = true;
                i--;
                j++;
            }
        }

        return isPalin;
    }
    // *******************************************************************************************************************************************
    // */
    // Backtracking solution, but Time is too large O(N * 2^N)
    /*
     * public int minCut(String s) {
     * return helper(0, s.length() - 1, s, s.length() - 1);
     * }
     * 
     * private int helper(int lo, int hi, String s, int minCuts) {
     * if (lo == hi || isPalindrome(s, lo, hi))
     * return 0;
     * 
     * for (int currentIndex = lo; currentIndex <= hi; currentIndex++) {
     * if (isPalindrome(s, lo, currentIndex)) {
     * minCuts = Math.min(minCuts, 1 + helper(currentIndex + 1, hi, s, minCuts));
     * }
     * }
     * 
     * return minCuts;
     * }
     * 
     * private boolean isPalindrome(String s, int left, int right) {
     * while (left < right) {
     * if (s.charAt(left) != s.charAt(right)) {
     * return false;
     * }
     * 
     * left++;
     * right--;
     * }
     * 
     * return true;
     * }
     */

    // *******************************************************************************************************************************************
    // */
    // Solution II add a memo to prevent recalculating the same position lo and hi
    // again Time: O(N^3), better, but not the optimized solution
    /*
     * public int minCut(String s) {
     * int n = s.length();
     * int[][] memo = new int[n][n];
     * Boolean[][] memoPalindrome = new Boolean[n][n];
     * return helper(0, s.length() - 1, s, s.length() - 1, memo, memoPalindrome);
     * }
     * 
     * private int helper(int lo, int hi, String s, int minCuts, int[][] memo,
     * Boolean[][] memoPalindrome) {
     * if (lo == hi || isPalindrome(s, lo, hi, memoPalindrome))
     * return 0;
     * 
     * if (memo[lo][hi] != 0)
     * return memo[lo][hi];
     * 
     * for (int currentIndex = lo; currentIndex <= hi; currentIndex++) {
     * if (isPalindrome(s, lo, currentIndex, memoPalindrome)) {
     * minCuts = Math.min(minCuts, 1 + helper(currentIndex + 1, hi, s, minCuts,
     * memo, memoPalindrome));
     * }
     * }
     * 
     * return memo[lo][hi] = minCuts;
     * }
     * 
     * private boolean isPalindrome(String s, int left, int right, Boolean[][]
     * memoPalindrome) {
     * if (memoPalindrome[left][right] != null)
     * return memoPalindrome[left][right];
     * 
     * while (left < right) {
     * if (s.charAt(left) != s.charAt(right)) {
     * return false;
     * }
     * 
     * left++;
     * right--;
     * }
     * 
     * return true;
     * }
     */
    // *******************************************************************************************************************************************
    // */
}
