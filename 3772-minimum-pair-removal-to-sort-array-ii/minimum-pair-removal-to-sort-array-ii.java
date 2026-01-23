import java.util.*;

class Solution {
    // Moved inner classes outside or kept static for clarity
    static class Pair implements Comparable<Pair> {
        long sum;
        Node left;

        Pair(long sum, Node left) {
            this.sum = sum;
            this.left = left;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.sum != other.sum) return Long.compare(this.sum, other.sum);
            // Tie-break: leftmost pair first (based on original ID)
            return Integer.compare(this.left.id, other.left.id);
        }
    }

    static class Node {
        long val;
        int id;
        Node prev, next;
        boolean removed = false;

        Node(long val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        Node[] nodes = new Node[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        // Track how many "violations" (nums[i] > nums[i+1]) exist
        int violations = 0;

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
            if (i > 0) {
                nodes[i].prev = nodes[i - 1];
                nodes[i - 1].next = nodes[i];
                pq.add(new Pair((long)nums[i-1] + nums[i], nodes[i-1]));
                if (nums[i-1] > nums[i]) violations++;
            }
        }

        int ops = 0;
        // If violations == 0, the array is already non-decreasing
        while (violations > 0 && !pq.isEmpty()) {
            Pair p = pq.poll();
            Node left = p.left;
            Node right = left.next;

            // Validation: skip if nodes were already merged
            if (left.removed || right == null || right.removed || (left.val + right.val != p.sum)) {
                continue;
            }

            // Before merging, check if the pairs being removed were violations
            if (left.prev != null && left.prev.val > left.val) violations--;
            if (left.val > right.val) violations--;
            if (right.next != null && right.val > right.next.val) violations--;

            // Perform Merge
            left.val = p.sum;
            right.removed = true;
            left.next = right.next;
            if (right.next != null) {
                right.next.prev = left;
            }

            // After merging, check for new violations created by the new value
            if (left.prev != null && left.prev.val > left.val) violations++;
            if (left.next != null && left.val > left.next.val) violations++;

            // Add new possible pairs to PQ
            if (left.next != null) pq.add(new Pair(left.val + left.next.val, left));
            if (left.prev != null) pq.add(new Pair(left.prev.val + left.val, left.prev));

            ops++;
        }

        return ops;
    }
}