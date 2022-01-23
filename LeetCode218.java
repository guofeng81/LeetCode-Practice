import java.util.*;

public class LeetCode218 {
    // Skyline Problem

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // preprocessing left point height will be negative
        // prioirityqueue will be the max heap to store
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> buildLines = new ArrayList<>();
        for (int[] building : buildings) {
            int[] temp = new int[] { building[0], -building[2] };
            int[] temp1 = new int[] { building[1], building[2] };
            buildLines.add(temp);
            buildLines.add(temp1);
        }

        Collections.sort(buildLines, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        }); // here you need to understand used two examples to see why is like that

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        maxHeap.add(0);
        int preHeight = 0;
        for (int[] points : buildLines) {
            if (points[1] < 0) {
                // it is left point
                maxHeap.add(-points[1]);
            } else {
                // it is right point
                maxHeap.remove(points[1]);
            }

            int curHeight = maxHeap.peek();
            if (curHeight != preHeight) {
                result.add(Arrays.asList(points[0], curHeight));
                preHeight = curHeight;
            }
        }

        return result;
    }
}
