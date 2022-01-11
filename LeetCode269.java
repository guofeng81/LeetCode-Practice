import java.util.*;

public class LeetCode269 {
    // Alien Dictionary
    public String alienOrder(String[] words) {

        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();
        buildGraph(graph, words, indegree);
        return bfs(graph, indegree);
    }

    private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();

        int size = graph.size();
        for (char c : graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                sb.append(c);
                q.offer(c);
            }
        }

        while (!q.isEmpty()) {
            char out = q.poll();
            if (graph.get(out) == null || graph.get(out).size() == 0) {
                continue;
            }

            for (char c : graph.get(out)) {
                indegree[c - 'a']--;
                if (indegree[c - 'a'] == 0) {
                    sb.append(c);
                    q.offer(c);
                }
            }
        }

        return size == sb.length() ? sb.toString() : ""; // understand why here is very important to understand Topology
    }

    private void buildGraph(Map<Character, Set<Character>> graph, String[] words, int[] indegree) {
        int n = words.length;

        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        for (int i = 1; i < n; i++) {
            String first = words[i - 1];
            String second = words[i]; // understand why only compares words 0 and 1, 1 and 2, 2 and 3 words is also
                                      // important

            int min = Math.min(first.length(), second.length());
            for (int j = 0; j < min; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    char out = first.charAt(j);
                    char in = second.charAt(j);
                    if (!graph.get(out).contains(in)) {
                        graph.get(out).add(in);
                        indegree[in - 'a']++;
                    }

                    break; // why break here, try to understand here is very important
                }
            }
        }
    }

    // LeetCode 953 Verifying an Alien Dictionary
    public boolean isAlienSorted(String[] words, String order) {
        // based on the order string to create
        int[] map = new int[26];
        // map stores the number decides the order of each character
        for (int i = 0; i < order.length(); i++) {
            map[order.charAt(i) - 'a'] = i;
        }

        // compare each word
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];

            int min = Math.min(first.length(), second.length());
            for (int j = 0; j < min; j++) {
                if (map[first.charAt(j) - 'a'] > map[second.charAt(j) - 'a']) {
                    return false;
                } else if (map[first.charAt(j) - 'a'] < map[second.charAt(j) - 'a']) {
                    break;
                } else if (j == min - 1 && first.length() > second.length()) {
                    return false;
                }
            }
        }

        return true;
    }

}
