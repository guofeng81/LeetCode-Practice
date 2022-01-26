import java.util.*;

public class LeetCode1087 {
    public String[] expand(String s) {
        List<String> list = new ArrayList<>();
        int n = s.length();
        // what does the step do?
        // "{a,b}c{d,e}f" => list will be [[ab], c, [de]] after the following steps and
        // then dfs through the list and append to string builder.
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '{') {
                int j = i + 1;
                StringBuilder sb = new StringBuilder();
                while (j < n && s.charAt(j) != '}') {
                    if (s.charAt(j) == ',') {
                        j++;
                        continue;
                    } else {
                        sb.append(s.charAt(j));
                    }
                    j++;
                }

                i = j;
                list.add(sb.toString());
            } else {
                list.add(s.charAt(i) + "");
            }
        }

        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        dfs(list, sb, 0, res);
        int size = res.size();
        String[] result = new String[size];

        for (int i = 0; i < size; i++) {
            result[i] = res.get(i);
        }

        Arrays.sort(result);
        return result;
    }

    private void dfs(List<String> list, StringBuilder sb, int pos, List<String> res) {
        if (sb.length() == list.size()) {
            res.add(sb.toString());
            return;
        }

        for (char c : list.get(pos).toCharArray()) {
            sb.append(c);
            dfs(list, sb, pos + 1, res);
            sb.setLength(sb.length() - 1);
        }
    }
}
