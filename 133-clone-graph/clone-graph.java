/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // Since Node.val is in range [1,100]
    private Node[] visited = new Node[101];

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // If already cloned, return it
        if (visited[node.val] != null) {
            return visited[node.val];
        }

        // Create clone
        Node clone = new Node(node.val);
        visited[node.val] = clone;

        // Clone neighbors
        for (Node nei : node.neighbors) {
            clone.neighbors.add(cloneGraph(nei));
        }

        return clone;
    }
}