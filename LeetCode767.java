import java.util.*;

class LeetCode767 {
    // LeetCode 767
    public String reorganizeString(String s) {

        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            maxHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        StringBuilder result = new StringBuilder();
        while (maxHeap.size() > 1) {
            Node cur = maxHeap.remove();
            Node next = maxHeap.remove();

            result.append(cur.c);
            cur.freq--;
            result.append(next.c);
            next.freq--;

            if (cur.freq > 0) {
                maxHeap.add(cur);
            }

            if (next.freq > 0) {
                maxHeap.add(next);
            }
        }

        if (!maxHeap.isEmpty()) {
            Node last = maxHeap.remove();
            if (last.freq > 1) {
                return "";
            }
            result.append(last.c);
        }

        return result.toString();

        /*
         * _________________________________________________________________________________________________________
         */
        /*
         * Solution 1:
         * 
         * Map<Character, Integer> counts = new HashMap<>();
         * for (char c : s.toCharArray()) {
         * counts.put(c, counts.getOrDefault(c, 0) + 1);
         * }
         * 
         * PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) ->
         * counts.get(b) - counts.get(a));
         * maxHeap.addAll(counts.keySet());
         * 
         * StringBuilder result = new StringBuilder();
         * 
         * while (maxHeap.size() > 1) {
         * char current = maxHeap.remove();
         * char next = maxHeap.remove();
         * result.append(current);
         * result.append(next);
         * counts.put(current, counts.get(current) - 1);
         * counts.put(next, counts.get(next) - 1);
         * if (counts.get(current) > 0) {
         * maxHeap.add(current);
         * }
         * 
         * if (counts.get(next) > 0) {
         * maxHeap.add(next);
         * }
         * }
         * 
         * if (!maxHeap.isEmpty()) {
         * char last = maxHeap.remove();
         * if (counts.get(last) > 1) {
         * return "";
         * }
         * result.append(last);
         * }
         * 
         * return result.toString();
         */
        /*
         * _________________________________________________________________________________________________________
         */
    }

    class Node {
        char c;
        int freq;

        public Node(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

}