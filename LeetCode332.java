import java.util.*;

public class LeetCode332 {

    // 这个题目去理解为什么用PriorityQueue<String>，而非List<String>. 这里是可以用List<String>
    // 如果题目没有要求字母顺序小的先走。为什么用LinkedList<> 是因为你每次都要加到头上 addFirst(),
    // 如果不想用LinkedList，你需要一个stack
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = buildGraph(tickets);
        List<String> result = new ArrayList<>();
        dfs("JFK", result, graph);
        return result;
    }

    private void dfs(String dest, List<String> result, Map<String, PriorityQueue<String>> graph) {
        PriorityQueue<String> neis = graph.get(dest);
        while (neis != null && neis.size() > 0) {
            dfs(neis.poll(), result, graph);
        }

        // or replace to stack.push(dest);
        result.add(0, dest);
    }

    private Map<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<String>());
            }

            graph.get(from).add(to);
        }

        return graph;
    }

    /*
     * 不想用PriorityQueue<> 就得在拿出元素的时候重新排序一下，拿出小的那个。
     * public List<String> findItinerary(List<List<String>> tickets) {
     * // use the ticket once and only once
     * // build a graph based on the tickets
     * int n = tickets.size();
     * 
     * Map<String, List<String>> graph = buildGraph(tickets);
     * LinkedList<String> result = new LinkedList<>();
     * dfs("JFK", result, graph);
     * return result;
     * }
     * 
     * private void dfs(String start, LinkedList<String> result, Map<String,
     * List<String>> graph){
     * 
     * List<String> neis = graph.get(start);
     * while(neis != null && neis.size() > 0){
     * Collections.sort(neis, Collections.reverseOrder());
     * String to = neis.remove(neis.size()-1);
     * dfs(to, result, graph);
     * }
     * 
     * result.addFirst(start);
     * }
     * 
     * private Map<String, List<String>> buildGraph(List<List<String>> tickets){
     * Map<String, List<String>> graph = new HashMap<>();
     * for(List<String> ticket : tickets){
     * String from = ticket.get(0);
     * String to = ticket.get(1);
     * 
     * if(!graph.containsKey(from)){
     * graph.put(from, new ArrayList<String>());
     * }
     * 
     * graph.get(from).add(to);
     * }
     * 
     * return graph;
     * }
     */

}
