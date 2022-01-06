import java.util.*;

public class LeetCode149 {
    public int maxPoints(int[][] points) {
        if (points.length < 2)
            return points.length;

        Set<String> set = new HashSet<>();
        int n = points.length;
        int max = 1;

        for (int i = 0; i < n && !set.contains(points[i][0] + "-" + points[i][1]); i++) {
            int[] a = points[i];
            Map<Double, Integer> map = new HashMap<>();

            int same = 0;
            int localMax = 1;
            for (int j = i + 1; j < n; j++) {
                // check to see if the point is duplicated
                if (isSame(a, points[j])) {
                    same++;
                    continue; // points[j] and a is the same points
                }

                double slope = getSlope(a, points[j]);
                map.put(slope, map.getOrDefault(slope, 1) + 1);
                localMax = Math.max(localMax, map.get(slope));
            }

            set.add(a[0] + "-" + a[1]);
            max = Math.max(localMax + same, max);
        }

        return max;
    }

    private boolean isSame(int[] a, int[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }

    private double getSlope(int[] a, int[] b) {
        if (a[0] == b[0])
            return Double.MAX_VALUE;
        if (a[1] == b[1])
            return 0;
        return (double) (a[1] - b[1]) / (double) (a[0] - b[0]);
    }
}
