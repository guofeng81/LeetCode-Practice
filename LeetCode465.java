import java.util.*;

public class LeetCode465 {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) + t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) - t[2]);
        }
        List<Integer> list = new ArrayList<>();
        for (int v : map.values()) {
            if (v != 0) {
                list.add(v);
            }
        }
        return dfs(0, list);
    }

    private int dfs(int k, List<Integer> list) {
        if (k == list.size()) {
            return 0;
        }

        int cur = list.get(k);
        if (cur == 0) {
            return dfs(k + 1, list);
        }

        int min = Integer.MAX_VALUE;

        for (int i = k + 1; i < list.size(); i++) {
            int next = list.get(i);
            if (cur * next < 0) {
                list.set(i, cur + next);
                min = Math.min(min, 1 + dfs(k + 1, list)); // what does it mean?
                list.set(i, next);

                if (cur + next == 0)
                    break;
            }
        }
        return min;
    }
}
