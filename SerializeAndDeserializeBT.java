import java.util.LinkedList;

public class SerializeAndDeserializeBT {
    // Serialize and Deserialize Binary Tree:
    // PreOrder, InOrder, PostOrder, LevelOrder, InOrder can't deserialize
    // How can I do it?
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public String NULL = "#";
    public String SEP = ",";

    // PreOrder
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL);
            return;
        }

        sb.append(root.val).append(SEP);

        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        LinkedList<String> list = new LinkedList<>();
        for (String s : data.split(SEP)) {
            list.addLast(s);
        }
        return deserialize(list);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty())
            return null;

        String first = nodes.removeFirst();
        if (first.equals(NULL))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);

        return root;
    }

}
