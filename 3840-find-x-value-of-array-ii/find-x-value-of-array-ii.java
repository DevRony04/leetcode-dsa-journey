class Solution {
     static class Node {
        int product;
        int[] prefix;

        Node(int k) {
            prefix = new int[k];
        }
    }

    int n;
    int k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
       
        this.n = nums.length;
        this.k = k;
        this.nums = nums;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query [start, n - 1]
            Node result = query(1, 0, n - 1, start, n - 1);

            answer[i] = result.prefix[x];
        }

        return answer;
    }

    // Build segment tree
    void build(int node, int left, int right) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].product = rem;
            tree[node].prefix[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Point update
    void update(int node, int left, int right, int index, int value) {

        if (left == right) {

            int rem = value % k;

            tree[node] = new Node(k);
            tree[node].product = rem;
            tree[node].prefix[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Query range
    Node query(int node, int left, int right, int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node leftNode =
            query(node * 2, left, mid, ql, qr);

        Node rightNode =
            query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(leftNode, rightNode);
    }

    // Merge two segments
    Node merge(Node left, Node right) {

        Node result = new Node(k);

        // Product of complete segment
        result.product =
            (left.product * right.product) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            result.prefix[r] += left.prefix[r];
        }

        // Prefixes that contain all of left
        // and some prefix of right
        for (int oldRemainder = 0; oldRemainder < k; oldRemainder++) {

            int count = right.prefix[oldRemainder];

            int newRemainder =
                (left.product * oldRemainder) % k;

            result.prefix[newRemainder] += count;
        }

        return result;
    }
}